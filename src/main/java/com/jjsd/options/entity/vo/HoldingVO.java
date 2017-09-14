package com.jjsd.options.entity.vo;

/**
 * Created by a297 on 17/9/9.
 */
public class HoldingVO {
    // 产品代码
    private String code;
    // 产品名称
    private String name;
    // 数量
    private String numberToSell;
    // 当前价
    private String currentPrice;
    // 涨跌幅
    private String fluctuation;
    // 盈亏 (当前价－成本价)＊数量 （保留正负符号，最好两位小数）
    private String profitAndLoss;
    // 交易类型
    private boolean isBuy;

    public HoldingVO(String code, String name, String numberToSell, String currentPrice, String fluctuation, String profitAndLoss, boolean isBuy) {
        this.code = code;
        this.name = name;
        this.numberToSell = numberToSell;
        this.currentPrice = currentPrice;
        this.fluctuation = fluctuation;
        this.profitAndLoss = profitAndLoss;
        this.isBuy = isBuy;
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

    public boolean getIsBuy() {
        return isBuy;
    }
}
