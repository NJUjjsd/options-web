package com.jjsd.options.service.impl;

import com.jjsd.options.dao.UserRepository;
import com.jjsd.options.entity.Cost;
import com.jjsd.options.entity.Property;
import com.jjsd.options.entity.User;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhujing on 2017/9/5.
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean signUp(String email,String userName,String password) {
        User u=userRepository.findByEmail(email);
        if(u!=null&&u.isStatus()==true){
            return false;
        }
        User user=new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        user.setStatus(false);
        try {
            user = EmailUtil.activateMail(user);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(String email, String password) {
        User u=userRepository.findByEmail(email);
        if (u!=null&&u.isStatus()==true&&u.getPassword().equals(password)){
            return true;
        }

        return false;
    }

    @Override
    public boolean modify(String email,String password) {
        User u=userRepository.findByEmail(email);
        if(u==null){
            return false;
        }
        u.setPassword(password);
        userRepository.save(u);
        return true;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean fillInCost(String email, Cost cost) {
        User u=userRepository.findByEmail(email);
        if(u==null){
            return false;
        }
        u.setCost(cost);
        userRepository.save(u);
        return true;
    }

    @Override
    public boolean fillInProperty(String email, Property property) {
        User u=userRepository.findByEmail(email);
        if(u==null){
            return false;
        }
        u.setProperty(property);
        userRepository.save(u);
        return true;
    }
}
