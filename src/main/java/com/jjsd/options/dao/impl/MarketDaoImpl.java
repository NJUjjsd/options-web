package com.jjsd.options.dao.impl;

import com.jjsd.options.dao.MarketDao;
import com.jjsd.options.entity.vo.ContactInfoVO;
import com.jjsd.options.entity.vo.ETFBasicInfoVO;
import com.jjsd.options.util.ETFInfoUtil;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/9/5.
 */
public class MarketDaoImpl implements MarketDao{
    public static void main(String[] args) {
        MarketDao dao= new MarketDaoImpl();
        /*System.out.println(JSONArray.fromObject(dao.getContactDueMonths()));
        System.out.println(JSONArray.fromObject(dao.getContactInfoUpdateTime()));
        System.out.println(JSONObject.fromObject(dao.getETFBasicInfo()));*/
        System.out.println(dao.getETFUpdateTime());
    }

    @Override
    public ArrayList<String> getContactDueMonths() {
        return ETFInfoUtil.getAllMonths();
    }

    @Override
    public String getETFUpdateTime() {
        return ETFInfoUtil.getETFTime();
    }

    @Override
    public ETFBasicInfoVO getETFBasicInfo() {
        return ETFInfoUtil.getNowETFInfo();
    }

    @Override
    public ArrayList<String> getContactInfoUpdateTime() {
        ArrayList<String > months = ETFInfoUtil.getAllMonths();
        ArrayList list = new ArrayList();
        for(String month:months){
            list.add(ETFInfoUtil.getMonthTime(month));
        }
        return list;
    }

    @Override
    public ArrayList<ContactInfoVO> getContactInfo() {
        return null;
    }

    @Override
    public ArrayList<ArrayList> getAllContactInfo() {
        ArrayList<String > months = ETFInfoUtil.getAllMonths();
        ArrayList list = new ArrayList();
        for(String month:months){
            ArrayList list1 = ETFInfoUtil.getInfoByMonth(month);
            list.add(list1);
        }
        return list;
    }
}


