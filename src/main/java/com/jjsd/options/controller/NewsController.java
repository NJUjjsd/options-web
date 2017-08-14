package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import com.jjsd.options.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/8/13.
 */
@Controller
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/news/list")
    public @ResponseBody String getNews(@RequestParam int page){
        Page<News> news = null;
        try {
            news = newsService.search(page,6,"keyword");
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(news);
    }

    @GetMapping(value = "/newsDetails")
    public @ResponseBody
    String getNewsDetailsByID(@RequestParam String newsID){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("heading", "this is heading");
        jsonObject.put("source", "2017-08-14");
        jsonObject.put("paragraphs", "ppp");
        jsonObject.put("original", "ooo");

        return JSON.toJSONString(jsonObject);
    }

}
