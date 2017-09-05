package com.jjsd.options.service.stub;

import com.google.gson.JsonObject;
import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import com.jjsd.options.service.NewsService;
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
        return null;
    }

    @Override
    public Page<News> search(int pageNum, int pageSize, String keyword) throws ParameterException {
        List<News> content = new ArrayList<>();
        for(int i=pageNum*pageSize;i<pageNum*pageSize+pageSize;i++){
            News news = new News();
            news.setCode("上证50ETF510050");
            news.setDate(new Date());
            news.setReadNum(0);
            news.setText("这是第"+i+"条新闻"+"Type something Type something Type something" +
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
        Pageable mp=new PageRequest(pageNum,pageSize,null);
        Page<News> result = new PageImpl<News>(content,mp,20);
        return result;
    }

    @Override
    public Page<News> classify(int pageNum, int pageSize, String code, String type, boolean isDescByReadNum) throws ParameterException {
        return null;
    }

    @Override
    public boolean readNumUpdate(String id) {
        return false;
    }

    @Override
    public List<String> getAllTitles() {
        return null;
    }
}
