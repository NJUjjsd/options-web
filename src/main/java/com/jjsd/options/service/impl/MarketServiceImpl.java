package com.jjsd.options.service.impl;

import com.jjsd.options.dao.KInfoDao;
import com.jjsd.options.dao.MarketDao;
import com.jjsd.options.dao.impl.KInfoSqlImpl;
import com.jjsd.options.dao.impl.MarketDaoImpl;
import com.jjsd.options.entity.vo.ContactInfoVO;
import com.jjsd.options.entity.vo.ETFBasicInfoVO;
import com.jjsd.options.entity.market.KInfoSimple;
import com.jjsd.options.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/15.
 */
@Service
public class MarketServiceImpl implements MarketService{
    static KInfoDao dao;
    static MarketDao marketDao;
    static {
        dao = new KInfoSqlImpl();
        marketDao = new MarketDaoImpl();
    }
    @Override
    public ArrayList<KInfoSimple> getWeekK() {
        return dao.getWeekK();

    }

    @Override
    public ArrayList<KInfoSimple> getMonthK() {
        return dao.getMonthK();

    }

    @Override
    public ArrayList<KInfoSimple> getDayK() {

        return dao.getDayK();
    }

    @Override
    public ArrayList<String> getContactDueMonths() {
        return marketDao.getContactDueMonths();
    }

    @Override
    public String getETFUpdateTime() {
        return marketDao.getETFUpdateTime();
    }

    @Override
    public ETFBasicInfoVO getETFBasicInfo() {
        return marketDao.getETFBasicInfo();
    }

    @Override
    public ArrayList<String> getContactInfoUpdateTime() {
        return marketDao.getContactInfoUpdateTime();
    }

    @Override
    public ArrayList<ContactInfoVO> getContactInfo() {
        return marketDao.getContactInfo();
    }

    @Override
    public ArrayList<ArrayList> getAllContactInfo() {
        return marketDao.getAllContactInfo();
    }
}
