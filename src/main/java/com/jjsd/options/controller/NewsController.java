package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.entity.news.News;
import com.jjsd.options.entity.market.StockCode;
import com.jjsd.options.exception.ParameterException;
import com.jjsd.options.service.NewsService;
import com.jjsd.options.util.AesEncryptUtil;
import com.jjsd.options.util.CrawlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by john on 2017/8/13.
 */
@Controller
@RequestMapping(value = "/api/news", produces = "application/json;charset=UTF-8")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/search")
    public @ResponseBody String searchNews(@RequestParam int page,@RequestParam int pageSize,@RequestParam String keyword){

//        try {
//            System.out.println(java.net.URLDecoder.decode(keyword,"UTF-8"));
//            System.out.println(AesEncryptUtil.desEncrypt(java.net.URLDecoder.decode(keyword,"UTF-8")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Page<News> news = null;
        try {
            news = newsService.search(page,pageSize,keyword);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(news);
    }

    @GetMapping(value = "/classifiedNews")
    public @ResponseBody String getClassifiedNews(@RequestParam int page,@RequestParam int pageSize,
                                                  @RequestParam String code,@RequestParam String type,
                                                  @RequestParam boolean isDescByReadNum){
        Page<News> news = null;
        try {
            news = newsService.classify(page,pageSize,code,type,isDescByReadNum);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(news);
    }

    @GetMapping(value = "/stockCode")
    public @ResponseBody String getStockCode(){
        Map<String,String> map = CrawlerUtil.stockCode;
        ArrayList<StockCode> result = new ArrayList<>();
        for(String key:map.keySet()){
            if(!key.equals("510050")){
                result.add(new StockCode(key,map.get(key)));
            }
        }
        return JSON.toJSONString(result);
    }

    @GetMapping(value = "/titles")
    public @ResponseBody String getTitles(@RequestParam String keyword){
        ArrayList<String> result = (ArrayList<String>) newsService.getAllTitles(keyword);
        System.out.println(keyword);
        System.out.println(result);
        return JSON.toJSONString(result);
    }

    @GetMapping(value = "/readNumUpdate")
    public @ResponseBody boolean readNumUpdate(@RequestParam String id){

        return newsService.readNumUpdate(id);
    }


}
