package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjsd.options.entity.user.*;
import com.jjsd.options.entity.vo.Account;
import com.jjsd.options.entity.vo.UserInfo;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.AesEncryptUtil;
import com.jjsd.options.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public @ResponseBody String getUserInfo(@RequestBody String email){
        String decrypt = null;
        try {
            decrypt = AesEncryptUtil.desEncrypt(JSONObject.parse(email).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(decrypt);
//        User user = userService.loadUserByEmail(email);
//        Property property = userService.loadPropertyByEmail(email);
//        Cost cost = userService.loadCostByEmail(email);
        User user = user();
        Cost cost = cost();
        Property property = property();
        Date date = new Date(user.getActivateTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        String registerTime = simpleDateFormat.format(date);
        UserInfo userInfo = new UserInfo(decrypt,user.getUserName(),registerTime,
                cost.getC1(),cost.getC2(),cost.getC3(),cost.getC4(),cost.getC5(),cost.getC6(),
                property.getR(),property.getB(),10000);
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public @ResponseBody boolean signUp(@RequestBody Account account){
       System.out.println(account);
       String email = null;
       String userName = null;
       String password = null;
       try{
           email = AesEncryptUtil.desEncrypt(account.getEmail());
           userName = AesEncryptUtil.desEncrypt(account.getUserName());
           password = AesEncryptUtil.desEncrypt(account.getPassword());
       }catch (Exception e){
           e.printStackTrace();
       }
       System.out.println(email+"   "+password+"   "+userName+"   ");
//       return userService.signUp(email,userName,password);
        return true;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody boolean login(@RequestBody Account account){
        System.out.println(account);
        String email = null;
        String password = null;
        try{
            email = AesEncryptUtil.desEncrypt(account.getEmail());
            password = AesEncryptUtil.desEncrypt(account.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(email+"   "+password+"   ");
//       return userService.login(email,password);
        return true;
    }

    @RequestMapping("/activatemail")
    public ModelAndView hello(HttpServletRequest request, Model model) throws IOException, AddressException, MessagingException, NoSuchAlgorithmException {
        //获取激活参数
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        Long time = System.currentTimeMillis();
        User u = userService.loadUserByEmail(email);
        if (u != null) {
            if (!u.isStatus() && u.getActivateTime() != 1) {
                if (u.getActivateTime() < time) {

                    //过期--激活失败
                    u.setActivateTime(Long.parseLong("-1"));
                    //重新发送激活邮件
                    u = EmailUtil.activateMail(u);
                    //重新设置了有效时间和token激活码
                    userService.update(u);
                } else if (u.getActivateTime()>time){
                    //在时间内
                    u.setActivateTime(Long.parseLong("1"));
                    if (u.getToken().equals(token)) {
                        //在时间内且激活码通过，激活成功
                        u.setStatus(true);

                        //重新设置token防止被禁用的用户利用激活
                        u.setToken(token.replace("1", "c"));
                        userService.update(u);

                    }
                }

            }
        } else if (u == null) {
            System.out.println("无此用户");
        }
        HttpSession session=request.getSession();
        session.setAttribute("username",u.getUserName());
        return new ModelAndView("log_index");
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
