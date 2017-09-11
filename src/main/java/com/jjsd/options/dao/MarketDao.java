package com.jjsd.options.dao;

import com.jjsd.options.entity.market.TransUnitPo;
import com.jjsd.options.entity.vo.ContactInfoVO;
import com.jjsd.options.entity.vo.ETFBasicInfoVO;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/9/5.
 */
public interface MarketDao {
    /**
     * 上证50etf 510050的实时信息
     * @return ETF基本信息
     */
    ETFBasicInfoVO getETFBasicInfo();

    /**
     * 根据id得到实时信息
     * @param id 格式为100开头，如实用500开头交易代码请先使用BasicInfoUtil.getId进行转换
     * @return
     */
     TransUnitPo getEtfById(String id);

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
