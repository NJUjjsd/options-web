package com.jjsd.options.entity.user;

import javax.persistence.*;

/**
 * Created by zhujing on 2017/9/11.
 */
@Entity
@Table(name = "Entrustment")
public class Entrustment{

    //订单id，用户email，代码，名称，买入true卖出false，数量，价格
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long entrustmentId;

    private String userEmail;

    private String code;

    private String optionName;

    private boolean isBuy;

    private int optionNum;

    private double price;

    public long getEntrustmentId() {
        return entrustmentId;
    }

    public void setEntrustmentId(long entrustmentId) {
        this.entrustmentId = entrustmentId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
