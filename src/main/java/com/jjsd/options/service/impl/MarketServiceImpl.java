package com.jjsd.options.service.impl;

import com.jjsd.options.dao.KInfoDao;
import com.jjsd.options.dao.impl.KInfoSqlImpl;
import com.jjsd.options.entity.KInfo;
import com.jjsd.options.entity.KInfoSimple;
import com.jjsd.options.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/15.
 */
@Service
public class MarketServiceImpl implements MarketService{
    static KInfoDao dao;
    static {
        dao = new KInfoSqlImpl();
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

        return dao.getWeekK();
    }
}
