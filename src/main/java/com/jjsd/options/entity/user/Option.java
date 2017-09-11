package com.jjsd.options.entity.user;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by zhujing on 2017/9/7.
 */
@Embeddable
public class Option implements Serializable {

    //代码，名称，可卖数量，成本价
    public Option() {
    }

    private String code;

    private String name;

    private int availableNum;

    private double cost;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * 获得当前价
     * @return
     */
    public double getNewestPrice(){
        return 0;

    }

    /**
     * 获得差价=当前价-成本价
     * @return
     */
    public double getPriceDifference(){
        return 0;
    }
}


