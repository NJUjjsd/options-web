package com.jjsd.options.entity.vo;

/**
 * Created by john on 2017/9/12.
 */
public class Account {
    private String email;

    private String userName;

    private String password;

    private String token;

    public Account(){}

    public Account(String email, String userName, String password, String token) {

        this.email = email;
        this.userName = userName;
        this.password = password;
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }
}
