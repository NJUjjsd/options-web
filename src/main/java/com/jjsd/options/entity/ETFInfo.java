package com.jjsd.options.entity;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/8/12.
 * etf期权总体数据，包括k线图，基本信息
 */
public class ETFInfo {
    //单位净值
    private double unitValue;

    private double rise;

    //三月涨跌幅
    private double threeMRise;

    //一年涨跌幅
    private double oneYRise;

    //三年涨跌幅
    private double threeYRise;

    //三月涨跌幅排名
    private String threeMPlace;

    //涨跌幅排名
    private String risePlace;

    //一年涨跌幅排名
    private String oneYPlace;

    //三年涨跌幅排名
    private String threeYPlace;

    //基金基本信息
    private ETFBaseInfo baseInfo;

    //日k线图
    private ArrayList<KInfo> dayK;

    //周k线图
    private ArrayList<KInfo> weekK;

    //月k线图
    private ArrayList<KInfo> monthK;

    public void setThreeMPlace(String threeMPlace) {
        this.threeMPlace = threeMPlace;
    }

    public String getRisePlace() {
        return risePlace;
    }

    public void setRisePlace(String risePlace) {
        this.risePlace = risePlace;
    }

    public void setOneYPlace(String oneYPlace) {
        this.oneYPlace = oneYPlace;
    }

    public void setThreeYPlace(String threeYPlace) {
        this.threeYPlace = threeYPlace;
    }

    public ETFInfo(double unitValue, double rise, double threeMRise, double oneYRise, double threeYRise, String threeMPlace, String risePlace, String oneYPlace, String threeYPlace, ETFBaseInfo baseInfo) {
        this.unitValue = unitValue;
        this.rise = rise;
        this.threeMRise = threeMRise;
        this.oneYRise = oneYRise;
        this.threeYRise = threeYRise;
        this.threeMPlace = threeMPlace;
        this.risePlace = risePlace;
        this.oneYPlace = oneYPlace;
        this.threeYPlace = threeYPlace;
        this.baseInfo = baseInfo;
    }

    public ETFInfo(double unitValue, double rise, double threeMRise, double oneYRise, double threeYRise, String threeMPlace, String risePlace, String oneYPlace, String threeYPlace, ETFBaseInfo baseInfo, ArrayList<KInfo> dayK, ArrayList<KInfo> weekK, ArrayList<KInfo> monthK) {

        this.unitValue = unitValue;
        this.rise = rise;
        this.threeMRise = threeMRise;
        this.oneYRise = oneYRise;
        this.threeYRise = threeYRise;
        this.threeMPlace = threeMPlace;
        this.risePlace = risePlace;
        this.oneYPlace = oneYPlace;
        this.threeYPlace = threeYPlace;
        this.baseInfo = baseInfo;
        this.dayK = dayK;
        this.weekK = weekK;
        this.monthK = monthK;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public double getRise() {
        return rise;
    }

    public void setRise(double rise) {
        this.rise = rise;
    }

    public double getThreeMRise() {
        return threeMRise;
    }

    public void setThreeMRise(double threeMRise) {
        this.threeMRise = threeMRise;
    }

    public double getOneYRise() {
        return oneYRise;
    }

    public void setOneYRise(double oneYRise) {
        this.oneYRise = oneYRise;
    }

    public double getThreeYRise() {
        return threeYRise;
    }

    public void setThreeYRise(double threeYRise) {
        this.threeYRise = threeYRise;
    }


    public ETFBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(ETFBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public ArrayList<KInfo> getDayK() {
        return dayK;
    }

    public void setDayK(ArrayList<KInfo> dayK) {
        this.dayK = dayK;
    }

    public ArrayList<KInfo> getWeekK() {
        return weekK;
    }

    public void setWeekK(ArrayList<KInfo> weekK) {
        this.weekK = weekK;
    }

    public ArrayList<KInfo> getMonthK() {
        return monthK;
    }

    public void setMonthK(ArrayList<KInfo> monthK) {
        this.monthK = monthK;
    }
}
