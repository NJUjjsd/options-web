package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.entity.market.KInfoSimple;
import com.jjsd.options.entity.vo.ETFBasicInfoVO;
import com.jjsd.options.entity.vo.NewETFOptionDataVO;
import com.jjsd.options.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/15.
 */
@Controller
@RequestMapping(value = "/api/market", produces = "application/json;charset=UTF-8")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/ETFKLine")
    public @ResponseBody String getETFKLineRawData(@RequestParam String tab){
        ArrayList<KInfoSimple> data;
        if(tab.equals("weekly")){
            data = marketService.getWeekK();
        } else if(tab.equals("monthly")){
            data = marketService.getMonthK();
        } else {
            data = marketService.getDayK();
        }
        return JSON.toJSONString(data);
    }

    @GetMapping(value="/ETFOption")
    public @ResponseBody String getOptionData(){
        // 到期月份
        ArrayList<String> dueMonths = marketService.getContactDueMonths();
        // ETF基本信息更新时间
        String ETFUpdateTime = marketService.getETFUpdateTime();
        // ETF基本信息
        ETFBasicInfoVO basicInfo = marketService.getETFBasicInfo();
        // 合约信息更新时间
        ArrayList<String> contactUpdateTime = marketService.getContactInfoUpdateTime();
        // 合约信息
        ArrayList<ArrayList> contactInfo = marketService.getAllContactInfo();

        // 给前端的data对象
        NewETFOptionDataVO optionDataVO = new NewETFOptionDataVO(
                dueMonths, ETFUpdateTime, basicInfo, contactUpdateTime, contactInfo);
        System.out.print(JSON.toJSONString(optionDataVO));
        return JSON.toJSONString(optionDataVO);
    }
}