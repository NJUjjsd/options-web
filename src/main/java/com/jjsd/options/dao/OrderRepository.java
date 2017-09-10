package com.jjsd.options.dao;

import com.jjsd.options.entity.user.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhujing on 2017/9/10.
 */
public interface OrderRepository extends JpaRepository<Order, String> {


}
