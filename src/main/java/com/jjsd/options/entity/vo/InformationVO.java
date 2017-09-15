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
    // ETF交易类型
    private boolean isBuy;

    // 看涨期权代码
    private String upCode;
    // 看涨期权名称
    private String upOptionName;
    // 看涨期权价格（元／份）
    private String upPrice;
    // 看涨期权交易类型
    private boolean upIsBuy;

    // 看跌期权代码
    private String downCode;
    // 看跌期权名称
    private String downOptionName;
    // 看跌期权价格（元／份）
    private String downPrice;
    // 看跌期权交易类型
    private boolean downIsBuy;

    // 组合的利润单价
    private String profitPerPiece;

    public InformationVO(String email, String code, String optionName, String price, boolean isBuy, String upCode, String upOptionName, String upPrice, boolean upIsBuy, String downCode, String downOptionName, String downPrice, boolean downIsBuy, String profitPerPiece) {
        this.email = email;
        this.code = code;
        this.optionName = optionName;
        this.price = price;
        this.isBuy = isBuy;
        this.upCode = upCode;
        this.upOptionName = upOptionName;
        this.upPrice = upPrice;
        this.upIsBuy = upIsBuy;
        this.downCode = downCode;
        this.downOptionName = downOptionName;
        this.downPrice = downPrice;
        this.downIsBuy = downIsBuy;
        this.profitPerPiece = profitPerPiece;
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

    public boolean isBuy() {
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

    public boolean isUpIsBuy() {
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

    public boolean isDownIsBuy() {
        return downIsBuy;
    }

    public String getProfitPerPiece() {
        return profitPerPiece;
    }
}
