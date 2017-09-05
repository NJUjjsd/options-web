package com.jjsd.options.dao;

import com.jjsd.options.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhujing on 2017/8/5.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *
     * @param email
     * @return
     */
    User findByEmail(String email);
}
