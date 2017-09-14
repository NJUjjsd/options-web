package com.jjsd.options.entity.vo;

import com.jjsd.options.entity.user.Cost;
import com.jjsd.options.entity.user.Property;

/**
 * Created by john on 2017/9/11.
 */
public class UserInfo {

    private String email;

    private String userName;

    /**

     * 注册时间
     */
//    private String registerTime;

    /**
     * 买入认沽成本
     */
    private double buyPut ;

    /**
     * 买入认购成本
     */
    private double buySubscribe;

    /**
     * 买入ETF成本
     */
    private double buyETF;

    /**
     * 卖出认沽成本
     */
    private double sellPut;

    /**
     * 卖出认购成本
     */
    private double sellSubscribe;

    /**
     * 卖出ETF成本
     */
    private double sellETF;

    /**
     * 最低风险利率
     */
    private double riskRate;

    /**
     * 本金
     */
    private double capital;

    /**
     * 总资产
     */
    private double total;

    //是否设置资产

    private boolean isSetProperty;

    //是否设置成本

    private boolean isSetCost;

    public boolean getIsSetProperty() {
        return isSetProperty;
    }

    public boolean getIsSetCost() {
        return isSetCost;
    }

    public UserInfo(){}

    public UserInfo(String email, String userName, double buyPut,
                    double buySubscribe, double buyETF,
                    double sellPut, double sellSubscribe,
                    double sellETF, double riskRate,
                    double capital, double total, boolean isSetProperty, boolean isSetCost) {
        this.email = email;
        this.userName = userName;
        this.buyPut = buyPut;
        this.buySubscribe = buySubscribe;
        this.buyETF = buyETF;
        this.sellPut = sellPut;
        this.sellSubscribe = sellSubscribe;
        this.sellETF = sellETF;
        this.riskRate = riskRate;
        this.capital = capital;
        this.total = total;
        this.isSetProperty = isSetProperty;
        this.isSetCost = isSetCost;
    }

    public UserInfo(String email, String userName, boolean isSetCost, boolean isSetProperty){
        this.email = email;
        this.userName = userName;
        this.isSetCost = isSetCost;
        this.isSetProperty = isSetProperty;
    }

    public void setProperty(Property property){
        this.riskRate = property.getR();
        this.capital = property.getB();
        this.total = property.getTotal();
    }

    public void setCost(Cost cost){
        this.buyPut = cost.getC1();
        this.buySubscribe = cost.getC2();
        this.buyETF = cost.getC3();
        this.sellPut = cost.getC4();
        this.sellSubscribe = cost.getC5();
        this.sellETF = cost.getC6();
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

//    public String getRegisterTime() {
//        return registerTime;
//    }

    public double getBuyPut() {
        return buyPut;
    }

    public double getBuySubscribe() {
        return buySubscribe;
    }

    public double getBuyETF() {
        return buyETF;
    }

    public double getSellPut() {
        return sellPut;
    }

    public double getSellSubscribe() {
        return sellSubscribe;
    }

    public double getSellETF() {
        return sellETF;
    }

    public double getRiskRate() {
        return riskRate;
    }

    public double getCapital() {
        return capital;
    }

    public double getTotal() {
        return total;
    }
}
