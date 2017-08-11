package com.jjsd.options.dao;

import com.jjsd.options.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

/**
 * Created by zhujing on 2017/8/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    @Test

    public void go(){



//        598bfeb972796025c8da69a6
//        newsRepository.save()

        Pageable mp=new PageRequest(0,5,new Sort(Sort.Direction.DESC,"isTop").and(new Sort(Sort.Direction.ASC,"date")));

        Page p=newsRepository.findByCodeAndType("上证50ETF510050","新闻",mp);
        System.out.println(p.getNumber());

        System.out.println(p.getSize());
        System.out.println(p.getTotalPages());

        Iterator iterator=p.iterator();
        while (iterator.hasNext()){
            News news= (News) iterator.next();
            System.out.println(news.getTitle());
            System.out.println(news.getDate().toString());
        }







    }

}

