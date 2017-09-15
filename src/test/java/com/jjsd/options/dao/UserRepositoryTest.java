package com.jjsd.options.dao;

import com.jjsd.options.entity.user.User;
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
        User user=userRepository.findByEmail("1513592323@qq.com");
        user.setSetCost(false);
        user.setSetProperty(false);
        userRepository.save(user);


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