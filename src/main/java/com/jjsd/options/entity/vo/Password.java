package com.jjsd.options.entity.vo;

/**
 * Created by john on 2017/9/15.
 */
public class Password {

    String email;
    String prePassword;
    String newPassword;

    public Password(){}

    public Password(String email, String prePassword, String newPassword) {
        this.email = email;
        this.prePassword = prePassword;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPrePassword() {
        return prePassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
