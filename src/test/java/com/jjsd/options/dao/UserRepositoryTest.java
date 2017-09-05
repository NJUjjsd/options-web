package com.jjsd.options.dao;

import com.jjsd.options.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * Created by zhujing on 2017/8/5.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail(){

    }


    @Test
    public void go(){
        User u=new User();
        u.setEmail("916110197@qq.com");
        u.setPassword("haha");
        HashMap<String ,Integer> a=new HashMap();
        a.put("a",1);
        a.put("b",2);
        u.setOptions(a);
        userRepository.save(u);
    }




}