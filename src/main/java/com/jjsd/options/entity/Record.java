package com.jjsd.options.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhujing on 2017/8/6.
 */
@Entity
@Table(name = "Record")
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    
    private String email;

    private Date date;

    private Property property;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
