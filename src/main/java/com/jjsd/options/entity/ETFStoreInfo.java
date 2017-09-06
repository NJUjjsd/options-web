package com.jjsd.options.entity;

/**
 * Created by ${zrz} on 2017/9/6.
 */
public class ETFStoreInfo {
    private String date;
    private String code;
    private double price;

    public ETFStoreInfo(String date, String code, double price) {
        this.date = date;
        this.code = code;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
