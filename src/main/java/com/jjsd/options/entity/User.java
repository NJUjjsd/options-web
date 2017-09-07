package com.jjsd.options.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhujing on 2017/8/5.
 */

@Entity
@Table(name = "User")
public class User {
    @Id
    private String email;

    private String password;

    //是否设置资产

    private boolean isSetProperty;

    private Property property;

    //是否设置成本

    private boolean isSetCost;

    private Cost cost;

    //是否邮箱验证、验证码、验证时间

    private boolean status;

    private String token;

    private long activateTime;

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

    public boolean isSetProperty() {
        return isSetProperty;
    }

    public void setSetProperty(boolean setProperty) {
        isSetProperty = setProperty;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public boolean isSetCost() {
        return isSetCost;
    }

    public void setSetCost(boolean setCost) {
        isSetCost = setCost;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
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
