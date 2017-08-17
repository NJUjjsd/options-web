package com.jjsd.options.dao;

import com.jjsd.options.dao.impl.KInfoSqlImpl;
import com.jjsd.options.entity.KInfo;
import net.sf.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ${zrz} on 2017/8/16.
 */
public class KInfoDaoTest {
    KInfoDao dao ;

    public KInfoDaoTest() {
        this.dao = new KInfoSqlImpl();
    }

    @Test
    public void getWeekK() throws Exception {
        System.out.println(JSONArray.fromObject(dao.getWeekK()).toString());
    }

    @Test
    public void getMonthK() throws Exception {
        System.out.println(JSONArray.fromObject(dao.getMonthK()).toString());
    }

    @Test
    public void getDayK() throws Exception {
        System.out.println(JSONArray.fromObject(dao.getDayK()).toString());
    }

}