package com.jjsd.options.entity;

/**
 * Created by ${zrz} on 2017/8/20.
 * 一个单位的合约信息，如一个代码对应的认购或认沽信息
 */
public class TransUnitPo {
    // 行权价，如："2.450"
    private String upExercisePrice;

    // 合约交易码，如："510050C1708M02450"
    private String  TradingCode;
    // 当前价，如："0.2017"
    private String  CurrentPrice;
    // 涨跌幅，如："6.16%"
    private String  Fluctuation;
    // 前结价，如："0.1900"
    private String  PreClosingPrice;
    //行情时间
    private String time;

    public TransUnitPo(String upExercisePrice, String tradingCode, String currentPrice, String fluctuation, String preClosingPrice, String time) {
        this.upExercisePrice = upExercisePrice;
        TradingCode = tradingCode;
        CurrentPrice = currentPrice;
        Fluctuation = fluctuation;
        PreClosingPrice = preClosingPrice;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUpExercisePrice() {
        return upExercisePrice;
    }

    public void setUpExercisePrice(String upExercisePrice) {
        this.upExercisePrice = upExercisePrice;
    }

    public String getTradingCode() {
        return TradingCode;
    }

    public void setTradingCode(String tradingCode) {
        TradingCode = tradingCode;
    }

    public String getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getFluctuation() {
        return Fluctuation;
    }

    public void setFluctuation(String fluctuation) {
        Fluctuation = fluctuation;
    }

    public String getPreClosingPrice() {
        return PreClosingPrice;
    }

    public void setPreClosingPrice(String preClosingPrice) {
        PreClosingPrice = preClosingPrice;
    }
}
