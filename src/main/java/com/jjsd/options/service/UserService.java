package com.jjsd.options.service;

import com.jjsd.options.entity.User;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface UserService {


    /**
     *
     * @param user
     * @return
     */
    boolean signUp(User user);


    /**
     *
     * @param email
     * @param password
     * @return
     */
    boolean login(String email,String password);


    /**
     *
     * @param user
     * @return
     */
    boolean modify(User user);


    /**
     *
     * @param email
     * @return
     */
    User loadUserByEmail(String email);




}
