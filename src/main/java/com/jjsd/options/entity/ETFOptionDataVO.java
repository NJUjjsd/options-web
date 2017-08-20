package com.jjsd.options.entity;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/20.
 */
public class ETFOptionDataVO {
    private ArrayList<String> dueMonths;
    private ETFBasicInfoVO basicInfoVOs;
    private ArrayList<String> contactInfoUpdateTime;
    private  ArrayList<ContactInfoVO> contactInfoVOs;

    public ETFOptionDataVO(ArrayList<String> dueMonths, ETFBasicInfoVO basicInfoVOs, ArrayList<String> contactInfoUpdateTime, ArrayList<ContactInfoVO> contactInfoVOs) {
        this.dueMonths = dueMonths;
        this.basicInfoVOs = basicInfoVOs;
        this.contactInfoUpdateTime = contactInfoUpdateTime;
        this.contactInfoVOs = contactInfoVOs;
    }

    public ArrayList<String> getDueMonths() {
        return dueMonths;
    }

    public ETFBasicInfoVO getBasicInfoVOs() {
        return basicInfoVOs;
    }

    public ArrayList<String> getContactInfoUpdateTime() {
        return contactInfoUpdateTime;
    }

    public ArrayList<ContactInfoVO> getContactInfoVOs() {
        return contactInfoVOs;
    }
}
