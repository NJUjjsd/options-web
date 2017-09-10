package com.jjsd.options.entity.user;

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

    private String userName;

    private String password;

    //是否设置资产

    private boolean isSetProperty;

    //是否设置成本

    private boolean isSetCost;

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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    
    public boolean isSetCost() {
        return isSetCost;
    }

    public void setSetCost(boolean setCost) {
        isSetCost = setCost;
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
