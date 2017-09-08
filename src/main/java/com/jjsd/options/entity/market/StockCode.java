package com.jjsd.options.entity.market;

/**
 * Created by john on 2017/8/16.
 */
public class StockCode {
    private String code;
    private String name;
    public StockCode(String code,String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
