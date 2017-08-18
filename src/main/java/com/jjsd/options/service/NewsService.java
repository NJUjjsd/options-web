package com.jjsd.options.service;

import com.google.gson.JsonObject;
import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface NewsService{
    JsonObject menu();

    /**
     * 新闻标题关键字搜索
     * @param pageNum 当前页数，0开始
     * @param pageSize 页大小
     * @param keyword 关键字
     * @return 新闻分页，page对象可以获得list，以及当前页号，页大小，总页数
     */
    Page<News> search(int pageNum,int pageSize,String keyword) throws ParameterException;

    /**
     * 新闻分类
     * @param pageNum 当前页数，0开始
     * @param pageSize 页大小
     * @param code 股票代码 详见CrawlerUtil stockcode的key部分
     * @param type 新闻种类 CrawlerUtil type
     * @param isDescByReadNum 是否按阅读数排序
     * @return 新闻分页
     */
    Page<News> classify(int pageNum,int pageSize,String code, String type, boolean isDescByReadNum) throws ParameterException;

    /**
     * 阅读数增加，当用户点开一则新闻是，调用此方法
     * @param id 文章标识
     * @return
     */
    boolean readNumUpdate(String id);


    /**
     * 所有新闻标题
     * @return
     */
    List<String> getAllTitles();

}
