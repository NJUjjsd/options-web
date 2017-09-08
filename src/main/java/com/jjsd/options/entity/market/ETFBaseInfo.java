package com.jjsd.options.entity.market;

import java.time.LocalDate;

/**
 * Created by ${zrz} on 2017/8/12.
 * ETF基金的基本信息，如公司成立时间等
 */
public class ETFBaseInfo {
    //成立时间
    private String formDate;

    //最新规模,单位亿元
    private double latestScale;

    //管理人
    private String manager;

    //累计单位净值
    private double unitValue;

    //累计分红
    private double drawsBous;

    //基金经理
    private String fundManager;

    public ETFBaseInfo(String formDate, double latestScale, String manager, double unitValue, double drawsBous, String fundManager) {
        this.formDate = formDate;
        this.latestScale = latestScale;
        this.manager = manager;
        this.unitValue = unitValue;
        this.drawsBous = drawsBous;
        this.fundManager = fundManager;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public double getLatestScale() {
        return latestScale;
    }

    public void setLatestScale(double latestScale) {
        this.latestScale = latestScale;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public double getDrawsBous() {
        return drawsBous;
    }

    public void setDrawsBous(double drawsBous) {
        this.drawsBous = drawsBous;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }
}
