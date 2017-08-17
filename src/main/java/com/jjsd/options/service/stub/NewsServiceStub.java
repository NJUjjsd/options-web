package com.jjsd.options.service.stub;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import com.jjsd.options.service.NewsService;
import com.jjsd.options.util.CrawlerUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 2017/8/13.
 */
@Service
public class NewsServiceStub implements NewsService{
    @Override
    public JsonObject menu() {
        JsonObject result=new JsonObject();
        return result;
    }

    @Override
    public Page<News> search(int page, int pageSize, String keyword) throws ParameterException {

        return newsList(page,pageSize,keyword);
    }

    @Override
    public Page<News> classify(int page, int pageSize, String code, String type, boolean isDescByReadNum) throws ParameterException {
        return newsList(page,pageSize,type+"("+code+")");
    }

    @Override
    public boolean readNumUpdate(String id) {
        return false;
    }

    private Page<News> newsList(int page, int pageSize, String keyword){
        List<News> content = new ArrayList<>();
        for(int i=page*pageSize;i<page*pageSize+pageSize;i++){
            News news = new News();
            news.setCode("上证50ETF510050");
            news.setDate(new Date());
            news.setReadNum(0);
            news.setText("这是第"+i+"条新闻,来源于"+keyword+"，Type something Type something Type something" +
                    "Type something" +
                    "Type something" +
                    "Type something" +
                    "Type somethingType something" +
                    "Type something" +
                    "Type something" +
                    "Type something" +
                    "Type something" +
                    "");
            news.setTitle("郑煤机(00564)重组拟购资产已通过中国商务部反垄断审查 A股继续停牌");
            news.setType("新闻");
            news.setTop(false);
            news.setUrl("www.baidu.com");
            content.add(news);
        }
        Pageable mp=new PageRequest(page,pageSize,null);
        Page<News> result = new PageImpl<News>(content,mp,20);
        return result;
    }
}
