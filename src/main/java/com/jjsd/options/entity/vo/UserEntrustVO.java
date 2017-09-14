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

    public UserEntrustVO(String email, String code, String optionName, boolean isBuy, String optionNum, String price) {
        this.email = email;
        this.code = code;
        this.optionName = optionName;
        this.isBuy = isBuy;
        this.optionNum = optionNum;
        this.price = price;
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

    public boolean isBuy() {
        return isBuy;
    }

    public String getOptionNum() {
        return optionNum;
    }

    public String getPrice() {
        return price;
    }
}
