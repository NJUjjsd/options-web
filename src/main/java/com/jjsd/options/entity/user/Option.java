package com.jjsd.options.entity.user;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by zhujing on 2017/9/7.
 */
@Embeddable
public class Option implements Serializable {

    public Option() {
    }

    private String code;

    private String name;

    private int totalNum;

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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
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

    public double getNewestPrice(){
        return 0;

    }

    public double getPriceDifference(){
        return 0;
    }
}


