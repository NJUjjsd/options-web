package com.jjsd.options.service.impl;


import com.jjsd.options.entity.KInfoSimple;
import com.jjsd.options.entity.vo.ContactInfoVO;
import com.jjsd.options.entity.vo.ETFBasicInfoVO;
import com.jjsd.options.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/20.
 */
@Service
public class MarketServiceStub implements MarketService {
    @Override
    public ArrayList<KInfoSimple> getWeekK() {
        return null;
    }

    @Override
    public ArrayList<KInfoSimple> getMonthK() {
        return null;
    }

    @Override
    public ArrayList<KInfoSimple> getDayK() {
        return null;
    }

    @Override
    public ArrayList<String> getContactDueMonths() {
        ArrayList<String> res = new ArrayList<>();
        res.add("2017-08");
        res.add("2017-09");
        res.add("2017-12");
        res.add("2018-03");
        return res;
    }

    @Override
    public String getETFUpdateTime() {
        return "2017-08-18 15:15:04";
    }

    @Override
    public ETFBasicInfoVO getETFBasicInfo() {
        ETFBasicInfoVO res = new ETFBasicInfoVO("510050", "上证50ETF", "2.645", "-0.007", "-0.26%", "0.26%", "258921", "6851.636");
        return res;
    }

    @Override
    public ArrayList<String> getContactInfoUpdateTime() {
        ArrayList<String> res = new ArrayList<>();
        res.add("2017-08-18 15:15:04");
        res.add("2017-08-18 15:15:05");
        res.add("2017-08-18 15:15:06");
        res.add("2017-08-18 15:15:07");
        return res;
    }

    @Override
    public ArrayList<ContactInfoVO> getContactInfo() {
        ArrayList<ContactInfoVO> res = new ArrayList<>();
        res.add(new ContactInfoVO("2.450", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res.add(new ContactInfoVO("2.450", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res.add(new ContactInfoVO("2.450", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        return res;
    }

    @Override
    public ArrayList<ArrayList> getAllContactInfo() {
        ArrayList<ContactInfoVO> res0 = new ArrayList<>();
        res0.add(new ContactInfoVO("2.450", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res0.add(new ContactInfoVO("2.450", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res0.add(new ContactInfoVO("2.450", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));

        ArrayList<ContactInfoVO> res1 = new ArrayList<>();
        res1.add(new ContactInfoVO("1.110", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res1.add(new ContactInfoVO("1.110", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res1.add(new ContactInfoVO("1.110", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));

        ArrayList<ContactInfoVO> res2 = new ArrayList<>();
        res2.add(new ContactInfoVO("3.330", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res2.add(new ContactInfoVO("3.330", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res2.add(new ContactInfoVO("3.330", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));

        ArrayList<ContactInfoVO> res3 = new ArrayList<>();
        res3.add(new ContactInfoVO("4.440", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res3.add(new ContactInfoVO("4.440", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));
        res3.add(new ContactInfoVO("4.440", "510050C1708M02450", "2.2017", "6.16%", "0.1900", "510050P1708M02450", "0.0002", "-33.33%", "0.0003"));

        ArrayList<ArrayList> res = new ArrayList<>();
        res.add(res0);
        res.add(res1);
        res.add(res2);
        res.add(res3);
        return res;
    }
}
