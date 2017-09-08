package com.jjsd.options.service;

import com.jjsd.options.entity.user.Cost;
import com.jjsd.options.entity.user.Property;
import com.jjsd.options.entity.user.User;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface UserService {


    /**
     *
     * @param email
     * @param userName
     * @param password
     * @return
     */
    boolean signUp(String email,String userName,String password);


    /**
     *
     * @param email
     * @param password
     * @return
     */
    boolean login(String email,String password);


    /**
     *
     * @param email
     * @param password
     * @return
     */
    boolean modify(String email,String password);


    /**
     *
     * @param email
     * @return
     */
    User loadUserByEmail(String email);


    /**
     *
     * @param email
     * @param cost
     * @return
     */
    boolean fillInCost(String email, Cost cost);


    /**
     *
     * @param email
     * @param property
     * @return
     */
    boolean fillInProperty(String email, Property property);





}
