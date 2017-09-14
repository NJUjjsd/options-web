package com.jjsd.options.entity.vo;

/**
 * Created by a297 on 17/9/12.
 */
public class InformationVO {
    private String email;
    private String code;
    private String optionName;
    private String price;
    private String optionNum;
    private boolean isBuy;

    private String upCode;
    private String upOptionName;
    private String upPrice;
    private String upOptionNum;
    private boolean upIsBuy;

    private String downCode;
    private String downOptionName;
    private String downPrice;
    private String downOptionNum;
    private boolean downIsBuy;

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
