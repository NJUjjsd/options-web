package com.jjsd.options.entity.user;

import javax.persistence.*;

/**
 * Created by zhujing on 2017/9/10.
 */
@Entity
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String userEmail;

    private String code;

    private String name;

    private boolean isBuy;

    private int num;

    private double price;

    private boolean isDeal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

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

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeal() {
        return isDeal;
    }

    public void setDeal(boolean deal) {
        isDeal = deal;
    }
}
