package com.jjsd.options.service;

import com.jjsd.options.entity.ETFBasicInfoVO;
import com.jjsd.options.entity.KInfoSimple;
import com.jjsd.options.entity.ContactInfoVO;

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

    /**
     * 
     * @return 合约到期的月份列表，如：["2017-08", "2017-09", "2017-12", "2018-03"]
     */
    ArrayList<String> getContactDueMonths();


    /**
     *
     * @return ETF信息更新时间，如："2017-08-17 15:15:04"
     */
    String getETFUpdateTime();

    /**
     * 
     * @return ETF基本信息
     */
    ETFBasicInfoVO getETFBasicInfo();

    /**
     * 
     * @return 合约基本信息更新时间列表，如：["2017-08-17 15:15:04", "2017-08-17 15:15:05", "2017-08-17 15:15:06", "2017-08-17 15:15:07"]
     */
    ArrayList<String> getContactInfoUpdateTime();

    /**
     * 
     * @return 合约基本信息列表(len=4, 更新的时间与上面对应)
     */
    ArrayList<ContactInfoVO> getContactInfo();

    /**
     *
     * @return 合约基本信息列表(len=4, 更新的时间与上面对应; 即四个ArrayList，每个ArrayList包含的是该月到期的合约)
     */
    ArrayList<ArrayList> getAllContactInfo();
}
