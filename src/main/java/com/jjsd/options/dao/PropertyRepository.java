package com.jjsd.options.dao;

import com.jjsd.options.entity.user.Property;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhujing on 2017/9/8.
 */
public interface PropertyRepository extends JpaRepository<Property, String> {

    /**
     *
     * @param email
     * @return
     */
    Property findByEmail(String email);




}
