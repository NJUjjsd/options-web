package com.jjsd.options.service.impl;

import com.jjsd.options.entity.market.ETFTradeInfo;
import com.jjsd.options.entity.user.Entrustment;
import com.jjsd.options.util.DateDealUtil;
import com.jjsd.options.util.ETFInfoUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ${zrz} on 2017/9/12.
 */
public class TradeServiceImpl {

    /**
     * 处理单个订单的方法
     * @return 处理的金额，如果不可交易则为0
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
        return resultMoney;

    }
}