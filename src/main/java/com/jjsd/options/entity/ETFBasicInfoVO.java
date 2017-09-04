package com.jjsd.options.entity;

/**
 * Created by a297 on 17/8/20.
 */
public class ETFBasicInfoVO {
    // 代码，如："510050"
    private String code;
    // 如："上证50ETF"
    private String name;
    // 当前价，如："2.645"
    private String curPrice;
    // 涨跌，如："－0.007"
    private String fluPrice;
    // 涨跌幅，如："-0.26%"
    private String fluPercent;
    // 振幅，如："0.26%"
    private String amplitude;
    // 成交量（手），如："258921"
    private String turnOver;
    // 成交额，如："6851.636"
    private String transaction;

    public ETFBasicInfoVO(String code, String name, String curPrice, String fluPrice, String fluPercent, String amplitude, String turnOver, String transaction) {
        this.code = code;
        this.name = name;
        this.curPrice = curPrice;
        this.fluPrice = fluPrice;
        this.fluPercent = fluPercent;
        this.amplitude = amplitude;
        this.turnOver = turnOver;
        this.transaction = transaction;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCurPrice() {
        return curPrice;
    }

    public String getFluPrice() {
        return fluPrice;
    }

    public String getFluPercent() {
        return fluPercent;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public String getTurnOver() {
        return turnOver;
    }

    public String getTransaction() {
        return transaction;
    }
}
