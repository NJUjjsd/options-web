package com.jjsd.options.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjsd.options.entity.vo.ModelResultVO;
import com.jjsd.options.service.InvestService;
import com.jjsd.options.service.impl.investModel.AdviceModel;
import com.jjsd.options.util.UrlFetcher;

import java.io.IOException;

public class InvestServiceImpl implements InvestService {

    /**
     * 获取ETF的剩余天数
     *
     * @param date
     * @return 剩余天数
     */
    private Integer getRemainderDays(String date) {
        String url = "http://stock.finance.sina.com.cn/futures/api/openapi.php/StockOptionService.getRemainderDay?date=" + date;
        try {
            String result = UrlFetcher.getUrlString(url);
            JSONObject jsonObject = JSON.parseObject(result);
            jsonObject = jsonObject.getJSONObject("result");
            jsonObject = jsonObject.getJSONObject("data");
            int remainderDays = (int) jsonObject.get("remainderDays");
            return remainderDays;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ModelResultVO getDecision() {
        //String date = "";
        //Integer remainderDays = getRemainderDays(date);
        //return new AdviceModel(Params...).getDecision();
        return null;
    }
}
