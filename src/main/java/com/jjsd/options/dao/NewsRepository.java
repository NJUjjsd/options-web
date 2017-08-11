package com.jjsd.options.dao;

import com.jjsd.options.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhujing on 2017/8/5.
 */

@Repository
public interface NewsRepository extends MongoRepository<News, String> {

    News findById(String id);


    Page<News> findByCodeAndType(String code,String type, Pageable pageable);

}
