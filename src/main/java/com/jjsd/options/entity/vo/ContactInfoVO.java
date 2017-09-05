package com.jjsd.options.entity.vo;

/**
 * Created by a297 on 17/8/20.
 */
public class ContactInfoVO {// 期权合约交易

    // 行权价，如："2.450"
    private String upExercisePrice;

    /**
     * 认购
     */
    // 合约交易码，如："510050C1708M02450"
    private String upTradingCode;
    // 当前价，如："0.2017"
    private String upCurrentPrice;
    // 涨跌幅，如："6.16%"
    private String upFluctuation;
    // 前结价，如："0.1900"
    private String upPreClosingPrice;

    /**
     * 认沽
     */
    // 合约交易码，如："510050P1708M02450"
    private String downTradingCode;
    // 当前价，如："0.0002"
    private String downCurrentPrice;
    // 涨跌幅，如："-33.33%"
    private String downFluctuation;
    // 前结价，如："0.0003"
    private String downPreClosingPrice;


    public ContactInfoVO(String upExercisePrice, String upTradingCode, String upCurrentPrice, String upFluctuation, String upPreClosingPrice, String downTradingCode, String downCurrentPrice, String downFluctuation, String downPreClosingPrice) {
        this.upExercisePrice = upExercisePrice;
        this.upTradingCode = upTradingCode;
        this.upCurrentPrice = upCurrentPrice;
        this.upFluctuation = upFluctuation;
        this.upPreClosingPrice = upPreClosingPrice;
        this.downTradingCode = downTradingCode;
        this.downCurrentPrice = downCurrentPrice;
        this.downFluctuation = downFluctuation;
        this.downPreClosingPrice = downPreClosingPrice;
    }

    public String getUpExercisePrice() {
        return upExercisePrice;
    }

    public String getUpTradingCode() {
        return upTradingCode;
    }

    public String getUpCurrentPrice() {
        return upCurrentPrice;
    }

    public String getUpFluctuation() {
        return upFluctuation;
    }

    public String getUpPreClosingPrice() {
        return upPreClosingPrice;
    }

    public String getDownTradingCode() {
        return downTradingCode;
    }

    public String getDownCurrentPrice() {
        return downCurrentPrice;
    }

    public String getDownFluctuation() {
        return downFluctuation;
    }

    public String getDownPreClosingPrice() {
        return downPreClosingPrice;
    }
}
