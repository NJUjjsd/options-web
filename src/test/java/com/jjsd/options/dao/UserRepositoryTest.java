package com.jjsd.options.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by zhujing on 2017/8/5.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail() throws Exception {
        System.out.println(userRepository.findByEmail("1513592323@qq.com").isStatus());


    }


    /**
     * 删除所有用户方法，直接跑
     * @throws Exception
     */
    @Test
    public void go() throws Exception {
        userRepository.deleteAll();
    }



}