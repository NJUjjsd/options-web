package com.jjsd.options.service;

import com.jjsd.options.entity.News;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface NewsService extends MongoRepository<News, String> {
}
