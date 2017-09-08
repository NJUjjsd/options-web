package com.jjsd.options.service.impl.investModel;

import com.jjsd.options.entity.vo.ModelResultVO;
import com.jjsd.options.util.ResultState;

public class AdviceModel {
    private double Ct;
    private double Pt;
    private double St;
    private double T;
    private double L;
    private double r;
    private double t;

    private double C1;
    private double C2;
    private double C3;
    private double C4;
    private double C5;
    private double C6;

    private Integer remainderDays;
    private double paramA;
    private double paramB;

    public AdviceModel(double ct, double pt, double st, double t, double l, double r, double t1, double c1, double c2, double c3, double c4, double c5, double c6, Integer remainderDays) {
        Ct = ct;
        Pt = pt;
        St = st;
        T = t;
        L = l;
        this.r = r;
        this.t = t1;
        C1 = c1;
        C2 = c2;
        C3 = c3;
        C4 = c4;
        C5 = c5;
        C6 = c6;
        this.remainderDays = remainderDays;
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
        if (remainderDays == null) {
            return null;
        }
        return L * Math.exp(r * remainderDays / 365) - L - M1 - M2 - M3;
    }

    private void setParam() {
        paramA = Ct + L * Math.exp(-r * remainderDays);
        paramB = Pt + St;
    }

    private ModelResultVO makeDecision(double A, double B) {
        if (A == B) {
            return new ModelResultVO(ResultState.NONE.toString(), -1);
        } else if (A >= B) {
            Double profit = calculateProfit(L, r, C2, C4, C6);
            if (profit == null) {
                return new ModelResultVO(ResultState.NONE.toString(), -1);
            }
            return profit >= 0 ? new ModelResultVO(ResultState.BUY.toString(), profit) : new ModelResultVO(ResultState.NONE.toString(), -1);
        } else {
            Double profit = calculateProfit(L, r, C1, C3, C5);
            if (profit == null) {
                return new ModelResultVO(ResultState.NONE.toString(), -1);
            }
            return profit >= 0 ? new ModelResultVO(ResultState.SELL.toString(), profit) : new ModelResultVO(ResultState.NONE.toString(), -1);
        }

    }

    public ModelResultVO getDecision() {
        setParam();
        return makeDecision(paramA, paramB);
    }

}
