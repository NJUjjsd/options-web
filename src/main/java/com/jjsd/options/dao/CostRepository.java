package com.jjsd.options.dao;

import com.jjsd.options.entity.user.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhujing on 2017/9/8.
 */
public interface CostRepository extends JpaRepository<Cost, String> {

    /**
     *
     * @param email
     * @return
     */
    Cost findByEmail(String email);
}
