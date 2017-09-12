package com.jjsd.options.entity.vo;

/**
 * Created by john on 2017/9/11.
 */
public class UserInfo {

    private String email;

    private String userName;

    /**

     * 注册时间
     */
    private String registerTime;

    /**
     * 买入认沽成本
     */
    private double buyPut;

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

    public UserInfo(String email, String userName,
                    String registerTime, double buyPut,
                    double buySubscribe, double buyETF,
                    double sellPut, double sellSubscribe,
                    double sellETF, double riskRate,
                    double capital, double total) {
        this.email = email;
        this.userName = userName;
        this.registerTime = registerTime;
        this.buyPut = buyPut;
        this.buySubscribe = buySubscribe;
        this.buyETF = buyETF;
        this.sellPut = sellPut;
        this.sellSubscribe = sellSubscribe;
        this.sellETF = sellETF;
        this.riskRate = riskRate;
        this.capital = capital;
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

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
