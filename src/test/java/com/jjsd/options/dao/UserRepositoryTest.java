package com.jjsd.options.dao;

import com.jjsd.options.util.AesEncryptUtil;
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


    }



    @Test
    public void go() throws Exception {
//        System.out.println(AesEncryptUtil.encrypt());
//        System.out.println(AesEncryptUtil.desEncrypt());
        String a="停牌";
        System.out.println(AesEncryptUtil.encrypt(a));
        System.out.println(AesEncryptUtil.desEncrypt(AesEncryptUtil.encrypt(a)));

    }




}