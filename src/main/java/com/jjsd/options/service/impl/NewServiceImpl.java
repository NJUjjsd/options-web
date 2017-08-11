package com.jjsd.options.service.impl;

import com.jjsd.options.dao.NewsRepository;
import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import com.jjsd.options.service.NewsService;
import com.jjsd.options.util.CrawlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by zhujing on 2017/8/9.
 */

@Service
public class NewServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;


    @Override
    public Page<News> search(int pageNum, int pageSize,String keyword) throws ParameterException {
        if(pageNum<0||pageSize<0){
            throw new ParameterException("错误传参");
        }

        Pageable mp=new PageRequest(pageNum,pageSize,new Sort(Sort.Direction.DESC,"date"));
        Page result=newsRepository.findByTitleLike(keyword,mp);
        return result;
    }

    @Override
    public Page<News> classify(int pageNum, int pageSize, String code, String type, boolean isDescByReadNum) throws ParameterException {

        if(pageNum<0||pageSize<0|| !CrawlerUtil.stockCode.containsKey(code)||!CrawlerUtil.type.contains(type)){
            throw new ParameterException("错误传参");
        }

        Pageable mp=null;
        if(isDescByReadNum){
            mp=new PageRequest(pageNum,pageSize,new Sort(Sort.Direction.DESC,"isTop").and(new Sort(Sort.Direction.DESC,"readNum")).and(new Sort(Sort.Direction.DESC,"date")));

        }else{
            mp=new PageRequest(pageNum,pageSize,new Sort(Sort.Direction.DESC,"readNum").and(new Sort(Sort.Direction.DESC,"date")));
        }

        Page result=newsRepository.findByCodeAndType(CrawlerUtil.stockCode.get(code)+code,type,mp);
        return result;
    }


    @Override
    public boolean readNumUpdate(String id) {
        News news=newsRepository.findById(id);
        if(news==null){
            return false;
        }
        news.setReadNum(news.getReadNum()+1);
        newsRepository.save(news);
        return true;
    }
}
