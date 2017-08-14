package com.jjsd.options.entity;

import java.time.LocalDateTime;

/**
 * Created by ${zrz} on 2017/8/14.
 * 分时图的数据
 */
public class TimeShareInfo {
    //时间
    private LocalDateTime dateTime;
    //价格
    private double price;
    //均价
    private double avePrice;
    //涨跌
    private double rise;
    //成交
    private double turnOver;

    public TimeShareInfo(LocalDateTime dateTime, double price, double avePrice, double rise, double turnOver) {
        this.dateTime = dateTime;
        this.price = price;
        this.avePrice = avePrice;
        this.rise = rise;
        this.turnOver = turnOver;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(double avePrice) {
        this.avePrice = avePrice;
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
}
