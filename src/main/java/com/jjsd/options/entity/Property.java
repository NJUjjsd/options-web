package com.jjsd.options.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by zhujing on 2017/9/6.
 */
@Embeddable
public class Property implements Serializable {
    //期权map（代码、数量）、上证50etf数量、最高无风险利率、本金


    public Property() {
    }

    private HashMap<String,Integer> options;

    private int numOfSETF;

    private double r;

    private double b;

    public HashMap<String, Integer> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }

    public int getNumOfSETF() {
        return numOfSETF;
    }

    public void setNumOfSETF(int numOfSETF) {
        this.numOfSETF = numOfSETF;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    /**
     * 按时计算资产
     * @param date
     * @return
     */
    public double calculate(Date date){
        return  0;
    }
}
