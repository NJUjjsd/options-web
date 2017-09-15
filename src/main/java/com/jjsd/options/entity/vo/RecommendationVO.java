package com.jjsd.options.entity.vo;

/**
 * Created by zhujing on 2017/9/12.
 */
public class RecommendationVO {

    //是否仅买入、认购期权、认沽期权、认购期权是否买入(认购期权买入则认沽期权和etf卖出；认购期权卖出则认沽期权和eft买入;注意卖出则全部卖出，买入期权和etf按1：1买入)
    //收益值每份
    private boolean isOnlyBuy;

    private String callOptionCode;

    private String putOptionCode;

    private boolean isCallBuy;

    //涨
    private double x;

    //跌
    private double y;

    //etf
    private double z;

    private int a;

    private double profit;

    public boolean isOnlyBuy() {
        return isOnlyBuy;
    }

    public void setOnlyBuy(boolean onlyBuy) {
        isOnlyBuy = onlyBuy;
    }

    public String getCallOptionCode() {
        return callOptionCode;
    }

    public void setCallOptionCode(String callOptionCode) {
        this.callOptionCode = callOptionCode;
    }

    public String getPutOptionCode() {
        return putOptionCode;
    }

    public void setPutOptionCode(String putOptionCode) {
        this.putOptionCode = putOptionCode;
    }

    public boolean isCallBuy() {
        return isCallBuy;
    }

    public void setCallBuy(boolean callBuy) {
        isCallBuy = callBuy;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
