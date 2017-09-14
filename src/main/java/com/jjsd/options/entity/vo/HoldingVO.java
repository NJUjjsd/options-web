package com.jjsd.options.entity.vo;

/**
 * Created by a297 on 17/9/9.
 */
public class HoldingVO {
    private String code;
    private String name;
    private String numberToSell;
    private String currentPrice;
    private String fluctuation;
    private String profitAndLoss;

    public HoldingVO(String code, String name, String numberToSell, String currentPrice, String fluctuation, String profitAndLoss) {
        this.code = code;
        this.name = name;
        this.numberToSell = numberToSell;
        this.currentPrice = currentPrice;
        this.fluctuation = fluctuation;
        this.profitAndLoss = profitAndLoss;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getNumberToSell() {
        return numberToSell;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getFluctuation() {
        return fluctuation;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }
}
