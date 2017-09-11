package com.jjsd.options.service;

import com.jjsd.options.entity.user.*;

import java.util.List;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface UserService {


    /**
     * 注册
     * @param email
     * @param userName
     * @param password
     * @return
     */
    boolean signUp(String email,String userName,String password);


    /**
     * 登陆
     * @param email
     * @param password
     * @return
     */
    boolean login(String email,String password);


    /**
     * 修改密码
     * @param email
     * @param password
     * @return
     */
    boolean modify(String email,String password);


    /**
     * 按email找用户
     * @param email
     * @return
     */
    User loadUserByEmail(String email);


    /**
     * 按email找交易成本
     * @param email
     * @return
     */
    Cost loadCostByEmail(String email);


    /**
     * 按email找资产
     * @param email
     * @return
     */
    Property loadPropertyByEmail(String email);


    /**
     * 填写或更新交易成本
     * @param cost
     * @return
     */
    boolean fillInCost(Cost cost);


    /**
     * 填写或更新资产
     * @param
     * @return
     */
    boolean fillInProperty(String email,double r,double b);


    /**
     * 获得撤单列表
     * @param email
     * @return
     */
    List<Order> getOrderList(String email);


    /**
     * 下单
     * @param order
     * @return
     */
    boolean makeOrder(Order order);


    /**
     * 撤单
     * @param orderId
     * @return
     */
    boolean cancelOrder(Long orderId);


    /**
     * 获得投资建议列表
     * @param email
     * @return
     */
    List<Recommendation> getRecommendationList(String email);



}
