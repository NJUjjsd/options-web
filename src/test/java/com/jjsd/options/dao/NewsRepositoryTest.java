package com.jjsd.options.dao;

import com.jjsd.options.util.CrawlerUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhujing on 2017/8/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;


    @Test
    public void findById() throws Exception {

        Assert.assertEquals(newsRepository.findById("5989905c5243010bdf3d56df").getTitle(),"中国建筑:上半年新签合同额快速增长,基建、海外业务持续耀眼");
        Assert.assertEquals(newsRepository.findById(null),null);
        Assert.assertEquals(newsRepository.findById("hahah"),null);
    }

    @Test
    public void findByCodeAndType() throws Exception {
        Assert.assertEquals(newsRepository.findByCodeAndType("浦发银行600000","haha",new PageRequest(0,10,new Sort(Sort.Direction.DESC,"date"))).getTotalPages(),0);
        Assert.assertEquals(newsRepository.findByCodeAndType("10","新闻",new PageRequest(0,10,new Sort(Sort.Direction.DESC,"date"))).getTotalPages(),0);
        Assert.assertEquals(newsRepository.findByCodeAndType("10","haha",new PageRequest(0,10,new Sort(Sort.Direction.DESC,"date"))).getSize(),10);
        Assert.assertEquals(newsRepository.findByCodeAndType("浦发银行600000","新闻",new PageRequest(0,10,new Sort(Sort.Direction.DESC,"date"))).getTotalPages(),4);

    }

    @Test
    public void findByTitleLike() throws Exception {
//        Assert.assertEquals(newsRepository.findByTitleLike("乐视",new PageRequest(0,10,new Sort(Sort.Direction.DESC,"date"))).getTotalPages(),9);
//        Assert.assertEquals(newsRepository.findByTitleLike("haha",new PageRequest(0,10,new Sort(Sort.Direction.DESC,"date"))).getTotalPages(),0);

    }

    @Test
    public void go(){
        for (String key: CrawlerUtil.stockCode.keySet()){
            System.out.println(key);
            if (key.equals("510050")){
                newsRepository.save(CrawlerUtil.getROfEFromHexun());
                newsRepository.save(CrawlerUtil.getNofEFromTencent());

            }else {
                newsRepository.save(CrawlerUtil.getRFromSina(key));
                newsRepository.save(CrawlerUtil.getNFromSina(key));
                newsRepository.save(CrawlerUtil.getAFromSina(key));
                newsRepository.save(CrawlerUtil.getNFromIfeng(key));

            }
        }
    }


}