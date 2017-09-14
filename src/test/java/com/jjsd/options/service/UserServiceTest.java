package com.jjsd.options.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhujing on 2017/9/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void signUp() throws Exception {
        System.out.println(userService.loadUserByEmail("1513592323@qq.com").getEmail());

    }

    @Test
    public void login() throws Exception {

    }

    @Test
    public void modify() throws Exception {

    }

    @Test
    public void loadUserByEmail() throws Exception {

    }

    @Test
    public void loadCostByEmail() throws Exception {

    }

    @Test
    public void loadPropertyByEmail() throws Exception {

    }

    @Test
    public void fillInCost() throws Exception {

    }

    @Test
    public void fillInProperty() throws Exception {

    }

    @Test
    public void getOrderList() throws Exception {

    }

    @Test
    public void makeOrder() throws Exception {

    }

    @Test
    public void cancelOrder() throws Exception {

    }

    @Test
    public void getRecommendationList() throws Exception {

    }

}