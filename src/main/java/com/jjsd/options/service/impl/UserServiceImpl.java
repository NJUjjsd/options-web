package com.jjsd.options.service.impl;

import com.jjsd.options.dao.CostRepository;
import com.jjsd.options.dao.EntrustmentRepository;
import com.jjsd.options.dao.PropertyRepository;
import com.jjsd.options.dao.UserRepository;
import com.jjsd.options.entity.user.*;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhujing on 2017/9/5.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private EntrustmentRepository entrustmentRepository;

    @Autowired
    private PropertyRepository propertyRepository;


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
        user.setSetCost(false);
        user.setSetProperty(false);
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
    public boolean modify(String email,String password,String newPassword) {
        User u=userRepository.findByEmail(email);
        if(u==null||!u.getPassword().equals(password)){
            return false;
        }
        u.setPassword(newPassword);
        userRepository.save(u);
        return true;
    }

    @Override
    public boolean update(User user) {
        if (user==null||userRepository.findByEmail(user.getEmail())==null){
            throw new NullPointerException();
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Cost loadCostByEmail(String email) {
        return costRepository.findByEmail(email);
    }

    @Override
    public Property loadPropertyByEmail(String email) {
        return propertyRepository.findByEmail(email);
    }

    @Override
    public boolean fillInCost(Cost cost) {
        if (cost==null){
            throw new NullPointerException();
        }
        User u=userRepository.findByEmail(cost.getEmail());
        Cost c=costRepository.findByEmail(cost.getEmail());
        if(c==null||u==null){
            return false;
        }
        if (u.isSetCost()==false){
            u.setSetCost(true);
        }
        userRepository.save(u);
        costRepository.save(cost);
        return true;
    }

    @Override
    public boolean fillInProperty(String email,double r,double b) {
        User u=userRepository.findByEmail(email);
        Property p=propertyRepository.findByEmail(email);
        if(p==null||u==null){
            return false;
        }
        p.setR(r);
        p.setB(b);
        if (u.isSetProperty()==false){
            u.setSetProperty(true);
        }
        userRepository.save(u);
        propertyRepository.save(p);
        return true;
    }

    @Override
    public List<Entrustment> getEntrustmentList(String email) {
        return entrustmentRepository.findByUserEmail(email);
    }

    @Override
    public boolean makeOrder(Entrustment order) {
        Property property=propertyRepository.findByEmail(order.getUserEmail());
        if (order==null||property==null){
            throw new NullPointerException();
        }

        String code=order.getCode();
        int num=order.getOptionNum();
        double price=order.getPrice();

        //买入则减少资金
        if (order.isBuy()){
            double b=property.getB()-num*price;
            property.setB(b);

        }else {//卖出则对应减少数量
            List<Option> list=property.getOptions();
            Iterator iterator=list.iterator();
            while (iterator.hasNext()){

                Option option= (Option) iterator.next();
                int position=list.indexOf(option);

                if(option.getCode().equals(code)){

                    int n=option.getAvailableNum()-num;
                    option.setAvailableNum(n);

                    list.remove(position);
                    list.add(position,option);

                }
            }
            property.setOptions(list);
        }
        propertyRepository.save(property);
        entrustmentRepository.save(order);
        return true;
    }

    @Override
    public boolean cancelOrder(Long entrustmentId) {
        Entrustment entrustment=entrustmentRepository.findByEntrustmentId(entrustmentId);
        if (entrustment==null){
            throw new NullPointerException();
        }

        Property property=propertyRepository.findByEmail(entrustment.getUserEmail());
        if (property==null){
            throw new NullPointerException();
        }


        String code=entrustment.getCode();
        int num=entrustment.getOptionNum();
        double price=entrustment.getPrice();

        //买入则恢复资金
        if (entrustment.isBuy()){
            double b=property.getB()+num*price;
            property.setB(b);


        }else {//卖出则对应恢复数量
            List<Option> list=property.getOptions();
            Iterator iterator=list.iterator();
            while (iterator.hasNext()){

                Option option= (Option) iterator.next();
                int position=list.indexOf(option);

                if(option.getCode().equals(code)){

                    int n=option.getAvailableNum()+num;
                    option.setAvailableNum(n);

                    list.remove(position);
                    list.add(position,option);

                }
            }
            property.setOptions(list);
        }
        propertyRepository.save(property);
        entrustmentRepository.delete(entrustment);
        return true;
    }

    @Override
    public boolean dealOrder(Long entrustmentId,double totalCost) {
        Entrustment entrustment=entrustmentRepository.findByEntrustmentId(entrustmentId);
        if (entrustment==null){
            throw new NullPointerException();
        }

        Property property=propertyRepository.findByEmail(entrustment.getUserEmail());
        if (property==null){
            throw new NullPointerException();
        }


        String code=entrustment.getCode();
        int num=entrustment.getOptionNum();
        double price=entrustment.getPrice();

        //买入则增加数量,加差价
        if (entrustment.isBuy()){

            List<Option> list=property.getOptions();
            Iterator iterator=list.iterator();
            while (iterator.hasNext()){
                Option option= (Option) iterator.next();
                int position=list.indexOf(option);

                if(option.getCode().equals(code)){

                    int n=option.getAvailableNum()+num;
                    option.setAvailableNum(n);

                    list.remove(position);
                    list.add(position,option);

                }
            }
            property.setOptions(list);
            property.setB(property.getB()+num*price-totalCost);
        }else {//卖出则增加本金，加差价

            property.setB(property.getB()-num*price+totalCost);
        }
        propertyRepository.save(property);
        entrustmentRepository.delete(entrustment);
        return true;

    }




}
