package com.jjsd.options.entity.vo;

/**
 * Created by a297 on 17/9/12.
 */
public class InformationVO {
    private String email;
    // ETF代码
    private String code;
    // ETF名称
    private String optionName;
    // ETF价格（元／股）
    private String price;
    // ETF数量 (手，即100股)
    private String optionNum;
    // ETF交易类型
    private boolean isBuy;

    // 看涨期权代码
    private String upCode;
    // 看涨期权名称
    private String upOptionName;
    // 看涨期权价格（元／份）
    private String upPrice;
    // 看涨期权数量（张，即10000份）
    private String upOptionNum;
    // 看涨期权交易类型
    private boolean upIsBuy;

    // 看跌期权代码
    private String downCode;
    // 看跌期权名称
    private String downOptionName;
    // 看跌期权价格（元／份）
    private String downPrice;
    // 看跌期权数量（张，即10000份）
    private String downOptionNum;
    // 看跌期权交易类型
    private boolean downIsBuy;

    // 组合的总利润
    private String totalProfit;

    public InformationVO(String email, String code, String optionName, String price, String optionNum, boolean isBuy, String upCode, String upOptionName, String upPrice, String upOptionNum, boolean upIsBuy, String downCode, String downOptionName, String downPrice, String downOptionNum, boolean downIsBuy, String totalProfit) {
        this.email = email;
        this.code = code;
        this.optionName = optionName;
        this.price = price;
        this.optionNum = optionNum;
        this.isBuy = isBuy;
        this.upCode = upCode;
        this.upOptionName = upOptionName;
        this.upPrice = upPrice;
        this.upOptionNum = upOptionNum;
        this.upIsBuy = upIsBuy;
        this.downCode = downCode;
        this.downOptionName = downOptionName;
        this.downPrice = downPrice;
        this.downOptionNum = downOptionNum;
        this.downIsBuy = downIsBuy;
        this.totalProfit = totalProfit;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getPrice() {
        return price;
    }

    public String getOptionNum() {
        return optionNum;
    }

    public boolean getIsBuy() {
        return isBuy;
    }

    public String getUpCode() {
        return upCode;
    }

    public String getUpOptionName() {
        return upOptionName;
    }

    public String getUpPrice() {
        return upPrice;
    }

    public String getUpOptionNum() {
        return upOptionNum;
    }

    public boolean getUpIsBuy() {
        return upIsBuy;
    }

    public String getDownCode() {
        return downCode;
    }

    public String getDownOptionName() {
        return downOptionName;
    }

    public String getDownPrice() {
        return downPrice;
    }

    public String getDownOptionNum() {
        return downOptionNum;
    }

    public boolean getDownIsBuy() {
        return downIsBuy;
    }

    public String getTotalProfit() {
        return totalProfit;
    }
}
