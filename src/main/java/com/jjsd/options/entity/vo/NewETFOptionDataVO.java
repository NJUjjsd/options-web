package com.jjsd.options.entity.vo;

import java.util.ArrayList;

/**
 * Created by a297 on 17/9/5.
 */
public class NewETFOptionDataVO {
    private ArrayList<String> dueMonths;
    private String ETFUpdateTime;
    private ETFBasicInfoVO basicInfo;
    private ArrayList<String> contactUpdateTime;
    private  ArrayList<ArrayList> contactInfo;

    public NewETFOptionDataVO(ArrayList<String> dueMonths, String ETFUpdateTime, ETFBasicInfoVO basicInfo, ArrayList<String> contactUpdateTime, ArrayList<ArrayList> contactInfo) {
        this.dueMonths = dueMonths;
        this.ETFUpdateTime = ETFUpdateTime;
        this.basicInfo = basicInfo;
        this.contactUpdateTime = contactUpdateTime;
        this.contactInfo = contactInfo;
    }

    public ArrayList<String> getDueMonths() {
        return dueMonths;
    }

    public String getETFUpdateTime() {
        return ETFUpdateTime;
    }

    public ETFBasicInfoVO getBasicInfo() {
        return basicInfo;
    }

    public ArrayList<String> getContactUpdateTime() {
        return contactUpdateTime;
    }

    public ArrayList<ArrayList> getContactInfo() {
        return contactInfo;
    }
}
