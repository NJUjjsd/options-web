package com.jjsd.options.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

/**
 * Created by zhujing on 2017/8/5.
 */

@Entity
@Table(name = "User")
public class User {
    @Id
    private String email;

    private String password;

    //是否设置资产、期权map（代码、数量）、上证50etf数量、最高无风险利率、本金

    private boolean isSetProperty;

    private HashMap<String,Integer> options;

    private int numOfSETF;

    private double r;

    private double b;

    //是否设置成本、买入认沽成本c1、买入认购成本c2、买入etf成本c3、卖出认沽成本c4、卖出认购成本c5、卖出etf成本c6

    private boolean isSetCost;

    private double c1;

    private double c2;

    private double c3;

    private double c4;

    private double c5;

    private double c6;

    //是否邮箱验证、验证码、验证时间

    private boolean status;

    private String token;

    private long activateTime;

    public boolean isSetProperty() {
        return isSetProperty;
    }

    public void setSetProperty(boolean setProperty) {
        isSetProperty = setProperty;
    }

    public boolean isSetCost() {
        return isSetCost;
    }

    public void setSetCost(boolean setCost) {
        isSetCost = setCost;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Integer> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }

    public int getNumOfSETF() {
        return numOfSETF;
    }

    public void setNumOfSETF(int numOfSETF) {
        this.numOfSETF = numOfSETF;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(long activateTime) {
        this.activateTime = activateTime;
    }
}
