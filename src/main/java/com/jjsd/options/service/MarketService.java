package com.jjsd.options.service;

import com.jjsd.options.entity.KInfoSimple;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/15.
 */
public interface MarketService {

    /**
     *
     * @return 日期升序列
     */
    ArrayList<KInfoSimple> getWeekK();

    /**
     *
     * @return 日期升序列
     */
    ArrayList<KInfoSimple> getMonthK();

    /**
     *
     * @return 日期升序列
     */
    ArrayList<KInfoSimple> getDayK();

}
