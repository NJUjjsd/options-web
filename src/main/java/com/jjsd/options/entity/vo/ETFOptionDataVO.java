package com.jjsd.options.entity.vo;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/20.
 */
public class ETFOptionDataVO {
    private ArrayList<String> dueMonths;
    private String ETFUpdateTime;
    private ETFBasicInfoVO basicInfo;
    private ArrayList<String> contactUpdateTime;
    private  ArrayList<ContactInfoVO> contactInfo;

    public ETFOptionDataVO(ArrayList<String> dueMonths, String ETFUpdateTime, ETFBasicInfoVO basicInfo, ArrayList<String> contactUpdateTime, ArrayList<ContactInfoVO> contactInfo) {
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

    public ArrayList<ContactInfoVO> getContactInfo() {
        return contactInfo;
    }
}
