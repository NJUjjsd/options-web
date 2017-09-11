package com.jjsd.options.dao;

import com.jjsd.options.dao.impl.KInfoSqlImpl;
import com.oracle.tools.packager.Log;
import net.sf.json.JSONArray;
import org.junit.Test;

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
        Log.debug(JSONArray.fromObject(dao.getWeekK()).toString());
    }

    @Test
    public void getMonthK() throws Exception {
        Log.debug(JSONArray.fromObject(dao.getMonthK()).toString());
    }

    @Test
    public void getDayK() throws Exception {
        Log.debug(JSONArray.fromObject(dao.getDayK()).toString());
    }

}