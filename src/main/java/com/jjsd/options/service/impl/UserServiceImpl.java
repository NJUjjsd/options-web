package com.jjsd.options.service.impl;

import com.jjsd.options.dao.UserRepository;
import com.jjsd.options.entity.User;
import com.jjsd.options.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhujing on 2017/9/5.
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean signUp(User user) {
        if(user==null){
            throw new NullPointerException();
        }

        User u=userRepository.findByEmail(user.getEmail());
        if(u!=null&&u.isStatus()==true){
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
    public boolean modify(User user) {
        if(user==null){
            throw new NullPointerException();
        }
        User u=userRepository.findByEmail(user.getEmail());
        if(u==null){
            return false;
        }

        userRepository.save(user);
        return true;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
