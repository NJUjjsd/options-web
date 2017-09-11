package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.entity.user.Cost;
import com.jjsd.options.entity.user.Property;
import com.jjsd.options.entity.user.User;
import com.jjsd.options.entity.user.UserInfo;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.AesEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 2017/9/10.
 */
@Controller
@RequestMapping(value="/api/users", produces = "application/json;charset=UTF-8")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    public @ResponseBody String getUserInfo(String email){
        String decrypt = null;
        try {
            decrypt = AesEncryptUtil.desEncrypt(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        String eDecode = URLDecoder.decode(decrypt);
//        User user = userService.loadUserByEmail(email);
//        Property property = userService.loadPropertyByEmail(email);
//        Cost cost = userService.loadCostByEmail(email);
        User user = user();
        Cost cost = cost();
        Property property = property();
        Date date = new Date(user.getActivateTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        String registerTime = simpleDateFormat.format(date);
        UserInfo userInfo = new UserInfo(eDecode,user.getUserName(),registerTime,
                cost.getC1(),cost.getC2(),cost.getC3(),cost.getC4(),cost.getC5(),cost.getC6(),
                property.getR(),property.getB(),property.getTotal());
        return JSON.toJSONString(userInfo);
    }

    private User user(){
        User user = new User();
        user.setEmail("1511592323@qq.com");
        user.setActivateTime(103232);
        user.setUserName("小插曲ee");
        return user;
    }

    private Cost cost(){
        Cost cost = new Cost();
        cost.setC1(12.3);
        cost.setC2(34.5);
        cost.setC3(783.0);
        cost.setC4(3543.9);
        cost.setC5(80.0);
        cost.setC6(76.4);
        return cost;
    }

    private Property property(){
        Property property = new Property();
        property.setB(32647);
        property.setR(0.025);
        return property;
    }
}
