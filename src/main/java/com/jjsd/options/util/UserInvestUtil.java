package com.jjsd.options.util;

import com.jjsd.options.entity.user.Option;
import com.jjsd.options.entity.user.Property;
import com.jjsd.options.entity.vo.HoldingVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a297 on 17/9/15.
 */
public class UserInvestUtil {
    public static Map<String, String > getContractCodeAndName(){
        // TODO 重写该方法, 用数据库真实数据
        // ETF 与 期权 都要
        Map<String, String > contractCodeAndName = new HashMap<>();
        contractCodeAndName.put("510050", "上证50ETF");
        contractCodeAndName.put("510050C1709M02250", "名字1");
        contractCodeAndName.put("510050P1709M02250", "名字2");
        contractCodeAndName.put("510050C1709M02300", "名字3");
        return contractCodeAndName;
    }

   public static String getBalance(Property property){
       // TODO 重写该方法，计算得出

       return "";
   }

   public static ArrayList<HoldingVO> getHolding(Property property){
       List<Option> options = property.getOptions();
       ArrayList<HoldingVO> holding = new ArrayList<>();
       for (Option eachOpt: options) {
           String code = eachOpt.getCode();
           String name = eachOpt.getName();
           String num = eachOpt.getAvailableNum()+"";
           String price = eachOpt.getNewestPrice()+"";
           String fluctuation = eachOpt.getPriceChangePercent()+"";
           String lossAndProfit = eachOpt.getPriceDifference()+"";
           // TODO Option加一个 "交易类型" 的属性:isBuy(boolean),然后get方法名是getIsBuy()，不是自动生成的那个。
//           boolean isBuy = eachOpt.getIsBuy();
           boolean isBuy = true;
           HoldingVO holdingVO = new HoldingVO(code, name, num, price, fluctuation, lossAndProfit, isBuy);
           holding.add(holdingVO);
       }
       return holding;
   }
}
