package com.jjsd.options.entity;

/**
 * Created by ${zrz} on 2017/9/8.
 * 存储Etf基本信息
 *
 */
public class ETFStoreBasic
{
    private String id;
    private String tradeCode;
    private String name;
    private String date;

    public ETFStoreBasic(String id, String tradeCode, String name) {
        this.id = id;
        this.tradeCode = tradeCode;
        this.name = name;
    }

    public ETFStoreBasic(String id, String tradeCode, String name, String date) {
        this.id = id;
        this.tradeCode = tradeCode;
        this.name = name;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
