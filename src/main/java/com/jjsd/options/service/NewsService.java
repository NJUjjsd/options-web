package com.jjsd.options.service;

import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import org.springframework.data.domain.Page;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface NewsService{

    /**
     * 新闻标题关键字搜索
     * @param keyword
     * @return
     */
    Page<News> search(int pageNum,int pageSize,String keyword) throws ParameterException;

    /**
     * 新闻分类
     * @param pageNum 当前页数，0开始
     * @param pageSize 页大小
     * @param code 股票代码 详见CrawlerUtil static
     * @param type 新闻种类 同上
     * @param isDescByReadNum
     * @return
     */
    Page<News> classify(int pageNum,int pageSize,String code, String type, boolean isDescByReadNum) throws ParameterException;

    /**
     * 阅读数增加
     * @param id
     * @return
     */
    boolean readNumUpdate(String id);

}
