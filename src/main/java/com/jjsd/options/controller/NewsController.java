package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

/**
 * Created by john on 2017/8/13.
 */
@Controller
@RequestMapping(value = "/api/news", produces = "application/json;charset=UTF-8")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/list")
    public @ResponseBody String getNews(@RequestParam int page){
        Page<News> news = null;
        try {
            news = newsService.search(page,6,"keyword");
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(news);
    }

}
