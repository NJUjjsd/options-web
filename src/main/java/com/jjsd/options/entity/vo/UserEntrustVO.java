package com.jjsd.options.entity.vo;

/**
 * Created by a297 on 17/9/12.
 */
public class UserEntrustVO {
    private String email;
    private String code;
    private String optionName;
    private boolean isBuy;
    private String optionNum;
    private String price;
    private String id;

    public UserEntrustVO(String email, String code, String optionName, boolean isBuy, String optionNum, String price, String id) {
        this.email = email;
        this.code = code;
        this.optionName = optionName;
        this.isBuy = isBuy;
        this.optionNum = optionNum;
        this.price = price;
        this.id = id;

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

    public boolean getIsBuy() {
        return isBuy;
    }

    public String getOptionNum() {
        return optionNum;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}
