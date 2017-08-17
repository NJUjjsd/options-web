package com.jjsd.options.dao;

import com.jjsd.options.entity.KInfoSimple;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/8/16.
 */
public interface KInfoDao {
    /**
     *
     * @return 日期升序列
     */
    public ArrayList<KInfoSimple> getWeekK();

    /**
     *
     * @return 日期升序列
     */
    public ArrayList<KInfoSimple> getMonthK();

    /**
     *
     * @return 日期升序列
     */
    public ArrayList<KInfoSimple> getDayK();
}
