package com.jjsd.options.entity.user;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by zhujing on 2017/9/6.
 */

@Entity
@Table(name = "Property")
public class Property {


    @Id
    private String email;
    //期权和etf的list（代码，名称，可卖数量，成本价）、最高无风险利率、本金

    @ElementCollection
    private List<Option> options;

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

    /**
     * 获得总资产
     * @return
     */
    public double getTotal(){
        return 0;
    }


}
