package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjsd.options.entity.user.*;
import com.jjsd.options.entity.vo.Account;
import com.jjsd.options.entity.vo.Password;
import com.jjsd.options.entity.vo.UserInfo;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.AesEncryptUtil;
import com.jjsd.options.util.EmailUtil;
import com.jjsd.options.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static final String register = "http://localhost: 8000/users/register";

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public @ResponseBody String getUserInfo(@RequestBody Account account){
        String email = account.getEmail();
        try{
            email = AesEncryptUtil.desEncrypt(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(email);
        User user = userService.loadUserByEmail(email);
        UserInfo userInfo = new UserInfo(email, user.getUserName(),user.isSetCost(),user.isSetProperty());
        if(user.isSetCost()){
            Cost cost = userService.loadCostByEmail(email);
            userInfo.setCost(cost);
        }
        if(user.isSetProperty()){
            Property property = userService.loadPropertyByEmail(email);
            userInfo.setProperty(property);
        }
        // 0全都填写，1未写
        int status = 0;
        String message = "";
        if(!(user.isSetProperty()&&user.isSetCost())){
            status = 1;
            message = "请将交易成本和目前持有补充完整，否则您将无法投资";
        }
        return JSONResult.fillResultString(status,message,userInfo);
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    public @ResponseBody String modifyUserInfo(@RequestBody UserInfo userInfo){
        System.out.println(userInfo.getEmail());
        String email = userInfo.getEmail();
        try{
            email = AesEncryptUtil.desEncrypt(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(email);
        Cost cost = new Cost();
        cost.setEmail(email);
        cost.setC1(userInfo.getBuyPut());
        cost.setC2(userInfo.getBuySubscribe());
        cost.setC3(userInfo.getBuyETF());
        cost.setC4(userInfo.getSellPut());
        cost.setC5(userInfo.getSellSubscribe());
        cost.setC6(userInfo.getSellETF());

        boolean flagCost = userService.fillInCost(cost);
        System.out.println(userInfo.getRiskRate()+"   "+userInfo.getCapital());
        boolean flagPro = userService.fillInProperty(email,userInfo.getRiskRate(),userInfo.getCapital());
        System.out.println(flagCost);
        System.out.println(flagPro);
        int status = 0;
        String message = "信息修改成功";
        if(!(flagCost&&flagPro)){
            status = 1;
            message = "woops ~~>,<~~ 修改信息失败";
        }
        return JSONResult.fillResultString(status,message,flagCost&&flagPro);
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public @ResponseBody String changePassword(@RequestBody Password password){
        String email = password.getEmail();
        String prePassword = password.getPrePassword();
        String newPassword = password.getNewPassword();
        try{
            email = AesEncryptUtil.desEncrypt(email);
            prePassword = AesEncryptUtil.desEncrypt(prePassword);
            newPassword = AesEncryptUtil.desEncrypt(newPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(email+"  "+prePassword+"  "+newPassword);
        boolean result = userService.modify(email,prePassword,newPassword);
        int status = result?0:1;
        String message = result?"密码修改成功":"woops ~~>,<~~ 密码修改失败";
        System.out.println(message);
        return JSONResult.fillResultString(status,message,result);
    }


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public @ResponseBody String signUp(@RequestBody Account account){
       System.out.println(account);
        String email = account.getEmail();
        String password = account.getPassword();
        String userName = account.getUserName();
        try{
            email = AesEncryptUtil.desEncrypt(email);
            password = AesEncryptUtil.desEncrypt(password);
            userName = AesEncryptUtil.desEncrypt(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
       System.out.println(email+"   "+password+"   "+userName+"   ");
       boolean result = false;

       try {
           result = userService.signUp(email,userName,password);
       }catch (MessagingException e){
           e.printStackTrace();
           return JSONResult.fillResultString(1,"激活链接发送失败，请稍后再试",result);
       }
       System.out.println("注册结果"+result);
        if (result){
            return JSONResult.fillResultString(0,"激活链接已成功发送到您的邮箱，请在24小时内激活您的账号",result);
        } else {
            return JSONResult.fillResultString(1,"woops ~~>,<~~ 该账号已被注册",result);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody Account account){
        String email = account.getEmail();
        String password = account.getPassword();
        try{
            email = AesEncryptUtil.desEncrypt(email);
            password = AesEncryptUtil.desEncrypt(password);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(email+"   "+password+"   ");
        boolean result = userService.login(email,password);
        if (result){
            return JSONResult.fillResultString(0,"登录成功",result);
        } else {
            return JSONResult.fillResultString(1,"用户名或密码错误",result);
        }
    }

    @RequestMapping(value = "/activatemail", method = RequestMethod.GET)
    public @ResponseBody String activatemail(String token,String email) throws IOException, MessagingException, NoSuchAlgorithmException {
        //获取激活参数
//        String email = account.getEmail();
//        String token = account.getToken();
        System.out.println("========================");
        System.out.println(email+"   "+token);
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
                    return "woops ~~>,<~~ 激活链接已过期";
//                    return JSONResult.fillResultString(3,"woops ~~>,<~~ 激活链接已过期",false);
                } else if (u.getActivateTime()>time){
                    //在时间内
                    u.setActivateTime(Long.parseLong("1"));
                    if (u.getToken().equals(token)) {
                        //在时间内且激活码通过，激活成功
                        u.setStatus(true);

                        //重新设置token防止被禁用的用户利用激活
                        u.setToken(token.replace("1", "c"));
                        userService.update(u);
                        return "恭喜您成功激活账号";
//                        return JSONResult.fillResultString(0,"恭喜您成功激活账号",true);
                    } else {
                        System.out.println("++++++++++++++++++++++++++");
                        System.out.println(u.getToken());
                        return "woops ~~>,<~~ 激活码验证不通过";
//                        return JSONResult.fillResultString(1,"woops ~~>,<~~ 激活码验证不通过",false);
                    }
                }

            }
        } else if (u == null) {
            return "woops ~~>,<~~ 无此用户";
//            return JSONResult.fillResultString(2,"woops ~~>,<~~ 无此用户",false);
        }
        return "恭喜您成功激活账号";
//        return JSONResult.fillResultString(0,"恭喜您成功激活账号","恭喜您成功激活账号");
    }
}
