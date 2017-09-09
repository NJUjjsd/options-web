package com.jjsd.options.util;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/8/16.
 */
public class dateDealUtil {
    /**
     * 精度转换的方法
     * @param length 小数点后保留的位数
     * @return
     */
   public static double precisionTrans(double prime,int length){
        double multiNum = Math.pow(10,Double.valueOf(length));
        int tempNum = (int)(prime*multiNum);
        return (tempNum+0.0)/multiNum;

    }

    /**
     * 处理价格的方法
     * @param price 对应期权的买入／卖出价格列表
     * @param volume 与上条一一对应的数量
     * @param nowPrice 传入的价格
     * @param tag 买入还是卖出的标志符，买入为true，卖出为false
     * @return
     */
    public static double anaylsePrice(ArrayList<Double> price,ArrayList<Double> volume,double nowPrice,boolean tag){
        int index = 0;
        double result=0;
        for(index=0;index<price.size();index++){
            double tempPrice = price.get(index);
            if(tag){
                if(tempPrice<=nowPrice)
                    break;
            }
            else{
                if(tempPrice>=nowPrice)
                    break;
            }
        }
        if(tag){
            for(;index<price.size();index++){
                result+=volume.get(index);
            }
        }
        else{
            for(;index>=0;index--){
                result+=volume.get(index);
            }
        }
        return result;

    }
}
