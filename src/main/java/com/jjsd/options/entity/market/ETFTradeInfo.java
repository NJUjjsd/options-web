package com.jjsd.options.entity.market;

import sun.jvm.hotspot.jdi.ArrayReferenceImpl;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/9/9.
 */
public class ETFTradeInfo {
    private String id;
    private String tradeCode;
    //申买量和申买价
    /*
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
    */
    private ArrayList<Double> buyPrice;
    private ArrayList<Double> buyVolume;
    //申卖量和申卖价
    /*
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
    */
    private ArrayList<Double> sellPrice;
    private ArrayList<Double> sellVolume;

    public ETFTradeInfo(String id, String tradeCode, ArrayList<Double> buyPrice, ArrayList<Double> buyVolume, ArrayList<Double> sellPrice, ArrayList<Double> sellVolume) {
        this.id = id;
        this.tradeCode = tradeCode;
        this.buyPrice = buyPrice;
        this.buyVolume = buyVolume;
        this.sellPrice = sellPrice;
        this.sellVolume = sellVolume;
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

    public ArrayList<Double> getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(ArrayList<Double> buyPrice) {
        this.buyPrice = buyPrice;
    }

    public ArrayList<Double> getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(ArrayList<Double> buyVolume) {
        this.buyVolume = buyVolume;
    }

    public ArrayList<Double> getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(ArrayList<Double> sellPrice) {
        this.sellPrice = sellPrice;
    }

    public ArrayList<Double> getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(ArrayList<Double> sellVolume) {
        this.sellVolume = sellVolume;
    }
}
