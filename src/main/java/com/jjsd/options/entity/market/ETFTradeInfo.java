package com.jjsd.options.entity.market;

/**
 * Created by ${zrz} on 2017/9/9.
 */
public class ETFTradeInfo {
    private String id;
    private String tradeCode;
    //申买量和申买价
    private double buyPrice1;
    private double buyVolume1;
    private double buyPrice2;
    private double buyVolume2;
    private double buyPrice3;
    private double buyVolume3;
    private double buyPrice4;
    private double buyVolume4;
    private double buyPrice5;
    private double buyVolume5;
    //申卖量和申卖价
    private double sellPrice1;
    private double sellVolume1;
    private double sellPrice2;
    private double sellVolume2;
    private double sellPrice3;
    private double sellVolume3;
    private double sellPrice4;
    private double sellVolume4;
    private double sellPrice5;
    private double sellVolume5;

    public ETFTradeInfo(String id, String tradeCode, double buyPrice1, double buyVolume1, double buyPrice2, double buyVolume2, double buyPrice3, double buyVolume3, double buyPrice4, double buyVolume4, double buyPrice5, double buyVolume5, double sellPrice1, double sellVolume1, double sellPrice2, double sellVolume2, double sellPrice3, double sellVolume3, double sellPrice4, double sellVolume4, double sellPrice5, double sellVolume5) {
        this.id = id;
        this.tradeCode = tradeCode;
        this.buyPrice1 = buyPrice1;
        this.buyVolume1 = buyVolume1;
        this.buyPrice2 = buyPrice2;
        this.buyVolume2 = buyVolume2;
        this.buyPrice3 = buyPrice3;
        this.buyVolume3 = buyVolume3;
        this.buyPrice4 = buyPrice4;
        this.buyVolume4 = buyVolume4;
        this.buyPrice5 = buyPrice5;
        this.buyVolume5 = buyVolume5;
        this.sellPrice1 = sellPrice1;
        this.sellVolume1 = sellVolume1;
        this.sellPrice2 = sellPrice2;
        this.sellVolume2 = sellVolume2;
        this.sellPrice3 = sellPrice3;
        this.sellVolume3 = sellVolume3;
        this.sellPrice4 = sellPrice4;
        this.sellVolume4 = sellVolume4;
        this.sellPrice5 = sellPrice5;
        this.sellVolume5 = sellVolume5;
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

    public double getBuyPrice1() {
        return buyPrice1;
    }

    public void setBuyPrice1(double buyPrice1) {
        this.buyPrice1 = buyPrice1;
    }

    public double getBuyVolume1() {
        return buyVolume1;
    }

    public void setBuyVolume1(double buyVolume1) {
        this.buyVolume1 = buyVolume1;
    }

    public double getBuyPrice2() {
        return buyPrice2;
    }

    public void setBuyPrice2(double buyPrice2) {
        this.buyPrice2 = buyPrice2;
    }

    public double getBuyVolume2() {
        return buyVolume2;
    }

    public void setBuyVolume2(double buyVolume2) {
        this.buyVolume2 = buyVolume2;
    }

    public double getBuyPrice3() {
        return buyPrice3;
    }

    public void setBuyPrice3(double buyPrice3) {
        this.buyPrice3 = buyPrice3;
    }

    public double getBuyVolume3() {
        return buyVolume3;
    }

    public void setBuyVolume3(double buyVolume3) {
        this.buyVolume3 = buyVolume3;
    }

    public double getBuyPrice4() {
        return buyPrice4;
    }

    public void setBuyPrice4(double buyPrice4) {
        this.buyPrice4 = buyPrice4;
    }

    public double getBuyVolume4() {
        return buyVolume4;
    }

    public void setBuyVolume4(double buyVolume4) {
        this.buyVolume4 = buyVolume4;
    }

    public double getBuyPrice5() {
        return buyPrice5;
    }

    public void setBuyPrice5(double buyPrice5) {
        this.buyPrice5 = buyPrice5;
    }

    public double getBuyVolume5() {
        return buyVolume5;
    }

    public void setBuyVolume5(double buyVolume5) {
        this.buyVolume5 = buyVolume5;
    }

    public double getSellPrice1() {
        return sellPrice1;
    }

    public void setSellPrice1(double sellPrice1) {
        this.sellPrice1 = sellPrice1;
    }

    public double getSellVolume1() {
        return sellVolume1;
    }

    public void setSellVolume1(double sellVolume1) {
        this.sellVolume1 = sellVolume1;
    }

    public double getSellPrice2() {
        return sellPrice2;
    }

    public void setSellPrice2(double sellPrice2) {
        this.sellPrice2 = sellPrice2;
    }

    public double getSellVolume2() {
        return sellVolume2;
    }

    public void setSellVolume2(double sellVolume2) {
        this.sellVolume2 = sellVolume2;
    }

    public double getSellPrice3() {
        return sellPrice3;
    }

    public void setSellPrice3(double sellPrice3) {
        this.sellPrice3 = sellPrice3;
    }

    public double getSellVolume3() {
        return sellVolume3;
    }

    public void setSellVolume3(double sellVolume3) {
        this.sellVolume3 = sellVolume3;
    }

    public double getSellPrice4() {
        return sellPrice4;
    }

    public void setSellPrice4(double sellPrice4) {
        this.sellPrice4 = sellPrice4;
    }

    public double getSellVolume4() {
        return sellVolume4;
    }

    public void setSellVolume4(double sellVolume4) {
        this.sellVolume4 = sellVolume4;
    }

    public double getSellPrice5() {
        return sellPrice5;
    }

    public void setSellPrice5(double sellPrice5) {
        this.sellPrice5 = sellPrice5;
    }

    public double getSellVolume5() {
        return sellVolume5;
    }

    public void setSellVolume5(double sellVolume5) {
        this.sellVolume5 = sellVolume5;
    }
}
