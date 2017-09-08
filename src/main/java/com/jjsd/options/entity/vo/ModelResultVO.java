package com.jjsd.options.entity.vo;

public class ModelResultVO {
    private String state;
    private double profit;

    public ModelResultVO(String state, double profit) {
        this.state = state;
        this.profit = profit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
