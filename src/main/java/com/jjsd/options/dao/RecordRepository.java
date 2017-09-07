package com.jjsd.options.dao;

import com.jjsd.options.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhujing on 2017/9/5.
 */
public interface RecordRepository extends JpaRepository<Record, Long> {


    /**
     *
     * @param email
     * @return
     */
    List<Record> findByEmail(String email);


}
