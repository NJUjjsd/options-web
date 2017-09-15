package com.jjsd.options.util;

import com.jjsd.options.entity.user.Option;
import com.jjsd.options.entity.user.Property;
import com.jjsd.options.entity.vo.HoldingVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by a297 on 17/9/15.
 */
public class UserInvestUtil {
    public static Map<String, String > getContractCodeAndName(){
        return BasicInfoUtil.getAllCode();
    }

   public static String getBalance(Property property){
       return String.valueOf(property.getB());
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
           HoldingVO holdingVO = new HoldingVO(code, name, num, price, fluctuation, lossAndProfit);
           holding.add(holdingVO);
       }
       return holding;

//       ArrayList<HoldingVO> holding = new ArrayList<>(4);
//       HoldingVO vo0 = new HoldingVO("510050C1709M02250", "875928078@qq.com"+"名字1", "100", "2.45", "+0.34", "+10000");
//       HoldingVO vo1 = new HoldingVO("510050P1709M02250", "875928078"+"名字2",  "200", "2.45", "+0.34", "+10000");
//       HoldingVO vo2 = new HoldingVO("510050C1709M02300", "875928078"+"名字3", "300", "2.45", "+0.34", "+10000");
//       HoldingVO vo3 = new HoldingVO("510050", "上证50ETF", "400", "2.45", "+0.34", "+10000");
//       holding.add(vo0);
//       holding.add(vo1);
//       holding.add(vo2);
//       holding.add(vo3);
//       return holding;
   }
}
