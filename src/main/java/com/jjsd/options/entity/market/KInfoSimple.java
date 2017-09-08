package com.jjsd.options.entity.market;

/**
 * Created by a297 on 17/8/15.
 */
public class KInfoSimple {
    //日期 格式: "2017-08-15"
    private String date;

    private double openPrice;

    private double closePrice;

    private double lowPrice;

    private double highPrice;
    //成交量 单位：万手
    private double turnOver;

    public KInfoSimple(String date, double openPrice, double closePrice, double highPrice, double lowPrice, double turnOver) {
        this.date = date;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.turnOver = turnOver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(double turnOver) {
        this.turnOver = turnOver;
    }
}
