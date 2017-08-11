package com.jjsd.options.service;

import com.jjsd.options.entity.News;
import com.jjsd.options.exception.ParameterException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by zhujing on 2017/8/4.
 */
public interface NewsService{

    /**
     *
     * @param keyword
     * @return
     */
    List<News> search(String keyword);

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param code
     * @param type
     * @param isDescByReadNum
     * @return
     */
    Page<News> classify(int pageNum,int pageSize,String code, String type, boolean isDescByReadNum) throws ParameterException;

    /**
     *
     * @param id
     * @return
     */
    boolean readNumUpdate(String id);

}
