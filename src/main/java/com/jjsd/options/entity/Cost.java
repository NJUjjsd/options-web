package com.jjsd.options.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by zhujing on 2017/9/6.
 */
@Embeddable
public class Cost implements Serializable {

    //买入认沽成本c1、买入认购成本c2、买入etf成本c3、卖出认沽成本c4、卖出认购成本c5、卖出etf成本c6


    public Cost() {
    }

    private double c1;

    private double c2;

    private double c3;

    private double c4;

    private double c5;

    private double c6;

    public double getC1() {
        return c1;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public double getC2() {
        return c2;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public double getC3() {
        return c3;
    }

    public void setC3(double c3) {
        this.c3 = c3;
    }

    public double getC4() {
        return c4;
    }

    public void setC4(double c4) {
        this.c4 = c4;
    }

    public double getC5() {
        return c5;
    }

    public void setC5(double c5) {
        this.c5 = c5;
    }

    public double getC6() {
        return c6;
    }

    public void setC6(double c6) {
        this.c6 = c6;
    }
}
