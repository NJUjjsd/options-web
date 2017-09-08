package com.jjsd.options.entity.market;

import java.time.LocalDate;

/**
 * Created by ${zrz} on 2017/8/12.
 * k线图单日的数据
 */
public class KInfo {
    //日期
    private String date;
    //价格
    private double nowPrice;

    private double openPrice;

    private double closePrice;

    private double highPrice;

    private double lowPrice;

    //涨跌幅
    private double rise;

    //成交量
    private double turnOver;

    //振幅
    private double Amplitude;


    public KInfo(String date, double nowPrice, double openPrice, double closePrice, double highPrice, double lowPrice, double rise, double turnOver, double amplitude) {
        this.date = date;
        this.nowPrice = nowPrice;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.rise = rise;
        this.turnOver = turnOver;
        Amplitude = amplitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
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

    public double getRise() {
        return rise;
    }

    public void setRise(double rise) {
        this.rise = rise;
    }

    public double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(double turnOver) {
        this.turnOver = turnOver;
    }

    public double getAmplitude() {
        return Amplitude;
    }

    public void setAmplitude(double amplitude) {
        Amplitude = amplitude;
    }
}
