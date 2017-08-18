package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.entity.KInfo;
import com.jjsd.options.entity.KInfoSimple;
import com.jjsd.options.service.MarketService;
import com.jjsd.options.service.impl.MarketServiceImpl;
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

}
