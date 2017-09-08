package com.jjsd.options.entity.user;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhujing on 2017/9/6.
 */

@Entity
@Table(name = "Property")
public class Property implements Serializable {
    //期权map（代码、数量）、上证50etf数量、最高无风险利率、本金


    public Property() {
    }

    @Id
    private String email;

    @ElementCollection
    private List<Option> options;

    private int numOfSETF;

    private double r;

    private double b;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
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

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getTotal(){
        return 0;
    }


}
