package com.jjsd.options.util;

/**
 * Created by ${zrz} on 2017/8/16.
 */
public class dateTransUtil {
    /**
     * 精度转换的方法
     * @param length 小数点后保留的位数
     * @return
     */
    static double precisionTrans(double prime,int length){
        double multiNum = Math.pow(10,Double.valueOf(length));
        int tempNum = (int)(prime*multiNum);
        return (tempNum+0.0)/multiNum;

    }
}
