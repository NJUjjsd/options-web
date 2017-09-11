package com.jjsd.options.dao;

import com.jjsd.options.dao.impl.MarketDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ${zrz} on 2017/9/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketDaoTest {
    MarketDao dao;
    @Before
    public void init(){
        dao = new MarketDaoImpl();
    }
    @Test
    public void getETFBasicInfo() throws Exception {
        assertNotNull(dao.getETFBasicInfo());
    }

    @Test
    public void getEtfById() throws Exception {
        assertNotNull(dao.getEtfById("10000846"));
    }

    @Test
    public void getContactDueMonths() throws Exception {
        assertNotNull(dao.getContactDueMonths());
    }

    @Test
    public void getETFUpdateTime() throws Exception {
        assertNotNull(dao.getETFUpdateTime());
    }

    @Test
    public void getContactInfoUpdateTime() throws Exception {
        assertNotNull(dao.getContactInfoUpdateTime());
    }

    @Test
    public void getContactInfo() throws Exception {

    }

    @Test
    public void getAllContactInfo() throws Exception {
        assertNotNull(dao.getAllContactInfo());
    }

}