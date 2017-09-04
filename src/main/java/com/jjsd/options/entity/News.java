package com.jjsd.options.entity;


import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhujing on 2017/8/5.
 */

/**
 *
 *
 *
 *
 */

public class News{


    //id唯一标识
    @Id
    private String id;

    //文章标题
    private String title;

    //文章内容
    private String text;

    //日期，调用getDateToString，方法获得"yyyy-mm-dd"
    private Date date;

    //原文链接
    private String url;

    //阅读数量（我们自己网站的）
    private int readNum;

    //股票代码，两种：上证50ETF510050和浦发银行600000（其他49只个股类似）
    private String code;

    //新闻种类，三种：新闻、公告、研报
    private String type;

    //是否置顶
    private boolean isTop;

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public String[] getResolvedText() {
        return text.split("\r");
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

    public String getDateToString(){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        return sdf.format(this.date);
    }


}
