package com.jjsd.options.dao;

import com.jjsd.options.entity.user.Entrustment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhujing on 2017/9/10.
 */
public interface EntrustmentRepository extends JpaRepository<Entrustment, Long> {

    Entrustment findByEntrustmentId(Long id);


    List<Entrustment> findByUserEmail(String email);


}
