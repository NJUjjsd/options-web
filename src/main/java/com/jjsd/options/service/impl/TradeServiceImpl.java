package com.jjsd.options.service.impl;

import com.jjsd.options.dao.EntrustmentRepository;
import com.jjsd.options.entity.market.ETFTradeInfo;
import com.jjsd.options.entity.user.Entrustment;
import com.jjsd.options.service.TradeService;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.DateDealUtil;
import com.jjsd.options.util.ETFInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ${zrz} on 2017/9/12.
 */
@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private UserService userService;

    @Autowired
    private EntrustmentRepository entrustmentRepository;

    /**
     * 处理单个订单的方法
     * @return 处理的金额，如果不可交易则为0,正代表增加，负代表减少
     */
    public double dealOneTrade(Entrustment entrustment){
        String id = entrustment.getCode();
        ETFTradeInfo tradeInfo = ETFInfoUtil.getTradeInfo(id);
        boolean isBuy = entrustment.isBuy();
        ArrayList<Double> price =null;
        ArrayList<Double> volume = null;
        if(isBuy){
            price = tradeInfo.getBuyPrice();
            volume = tradeInfo.getBuyVolume();
        }
        else{
            price = tradeInfo.getSellPrice();
            volume = tradeInfo.getSellVolume();
        }
        Map<Double,Double> canDeal = DateDealUtil.anaylsePrice(price,volume,entrustment.getPrice(),isBuy);
        double resultMoney = 0;
        double totalVolume = entrustment.getOptionNum();
        for(double money:canDeal.keySet()){
            double tempVolume = canDeal.get(money);
            if(tempVolume<totalVolume){
                //买入
                resultMoney+=money*tempVolume;
                totalVolume-=tempVolume;
            }
            else {
                resultMoney+=money*totalVolume;
                break;
            }
        }
        if(isBuy){
            resultMoney = -resultMoney;
        }
        return resultMoney;

    }

    /**
     * 处理全部委托
     */
    public void dealAllTrade(){
        if (entrustmentRepository.findAll().size()==0){
            return;
        }
        ArrayList<Entrustment> allTrade = (ArrayList<Entrustment>) entrustmentRepository.findAll();
        //待补全
        for(Entrustment entrustment:allTrade){
            double tradeMoney = dealOneTrade(entrustment);
            if(tradeMoney>0){
                userService.dealOrder(entrustment.getEntrustmentId(),tradeMoney);
            }
        }
    }
}
