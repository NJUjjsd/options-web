package com.jjsd.options.service;

import com.jjsd.options.exception.ParameterException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhujing on 2017/8/10.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;


    @Test
    public void menu() throws Exception {
        System.out.println(newsService.menu().toString());


    }

    @Test(expected=ParameterException.class)
    public void search() throws Exception {

        newsService.search(-1,-1," 乐视");
        Assert.assertEquals(newsService.search(0,10," 乐视").getTotalPages(),9);

    }

    @Test(expected=ParameterException.class)
    public void classify() throws Exception {
        newsService.classify(-1,10,"600000","新闻",true);
        newsService.classify(0,-1,"600000","新闻",true);
        newsService.classify(0,10,"haha","新闻",true);
        newsService.classify(0,10,"600000","haha",true);
        Assert.assertEquals(newsService.classify(0,10,"600000","新闻",true).getTotalPages(),4);


    }

    @Test
    @Ignore
    public void readNumUpdate() throws Exception {

    }

    @Test
    public void getAllTitles() throws Exception {
        Assert.assertEquals(newsService.getAllTitles().size(),2478);

    }
}