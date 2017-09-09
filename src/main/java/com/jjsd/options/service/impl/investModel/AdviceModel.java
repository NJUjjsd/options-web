package com.jjsd.options.service.impl.investModel;

import com.jjsd.options.entity.vo.ModelResultVO;
import com.jjsd.options.util.InvestMode;
import com.jjsd.options.util.ResultState;

public class AdviceModel {
    private double Ct;
    private double Pt;
    private double St;
    private double L;
    private double r;

    private double C1;
    private double C2;
    private double C3;
    private double C4;
    private double C5;
    private double C6;

    private Integer remainderDays;
    private double paramA;
    private double paramB;

    private InvestMode mode; //NORMAL是有买有卖，ONLY_BUY是只买入

    public AdviceModel(double ct, double pt, double st, double l, double r, double c1, double c2, double c3, double c4, double c5, double c6, Integer remainderDays, InvestMode mode) {
        Ct = ct;
        Pt = pt;
        St = st;
        L = l;
        this.r = r;
        C1 = c1;
        C2 = c2;
        C3 = c3;
        C4 = c4;
        C5 = c5;
        C6 = c6;
        this.remainderDays = remainderDays;
        this.mode = mode;
    }

    /**
     * 计算用于判断是否提示的收益值
     *
     * @param L  ETF期权的交割价格
     * @param r  最高无风险利率
     * @param M1 1st to be minused
     * @param M2 2nd to be minused
     * @param M3 3rd to be minused
     * @return param
     */
    private Double calculateProfit(double L, double r, double M1, double M2, double M3) {
        return L * Math.exp(r * remainderDays / 365) - L - M1 - M2 - M3;
    }

    private void setParam() {
        paramA = Ct + L * Math.exp(-r * remainderDays / 365);
        paramB = Pt + St;
    }

    private ModelResultVO makeDecision(double A, double B) {
        if (A == B) {
            return new ModelResultVO(ResultState.NONE.toString(), -1);
        } else if (A >= B) {
            Double profit = mode == InvestMode.NORMAL ? calculateProfit(L, r, C2, C4, C6) : calculateProfit(L, r, C2, 0, 0);
            if (profit == null) {
                return new ModelResultVO(ResultState.NONE.toString(), -1);
            }
            return profit >= 0 ? new ModelResultVO(ResultState.BUY.toString(), profit) : new ModelResultVO(ResultState.NONE.toString(), -1);
        } else {
            Double profit = mode == InvestMode.NORMAL ? calculateProfit(L, r, C1, C3, C5) : calculateProfit(L, r, C1, C3, 0);
            if (profit == null) {
                return new ModelResultVO(ResultState.NONE.toString(), -1);
            }
            return profit >= 0 ? new ModelResultVO(ResultState.SELL.toString(), profit) : new ModelResultVO(ResultState.NONE.toString(), -1);
        }

    }


    public ModelResultVO getDecision() {
        if (remainderDays == null) {
            return new ModelResultVO(ResultState.NONE.toString(), -1);
        }
        setParam();
        return makeDecision(paramA, paramB);
    }

}
