package com.jjsd.options.service.impl;

import com.jjsd.options.entity.vo.ModelResultVO;
import com.jjsd.options.service.InvestService;
import com.jjsd.options.util.BasicInfoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InvestServiceImpl implements InvestService {

    /**
     * 获取ETF的剩余天数
     *
     * @param code
     * @return 剩余天数
     */
    private Integer getRemainderDays(String code) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date endDate = simpleDateFormat.parse(BasicInfoUtil.getEndDate(code));
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            double end = cal.getTimeInMillis();
            cal.setTime(today);
            double now = cal.getTimeInMillis();
            Integer between_days = Math.toIntExact(Math.round((end - now) / (86400000))); //1000 * 3600 * 24 = 86400000
            return between_days;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ModelResultVO getDecision() {
        String code = "";
        Integer remainderDays = getRemainderDays(code);
        //return new AdviceModel(Params...).getDecision();
        return null;
    }
}
