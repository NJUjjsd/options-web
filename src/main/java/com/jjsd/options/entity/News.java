package com.jjsd.options.entity;

import java.util.Date;

/**
 * Created by zhujing on 2017/8/5.
 */
public class News {

    private String title;

    private String text;

    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
