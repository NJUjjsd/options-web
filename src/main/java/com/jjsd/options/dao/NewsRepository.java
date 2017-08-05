package com.jjsd.options.dao;

import com.jjsd.options.entity.News;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhujing on 2017/8/5.
 */
public interface NewsRepository extends MongoRepository<News, String> {
}
