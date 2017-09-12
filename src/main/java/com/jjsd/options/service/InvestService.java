package com.jjsd.options.service;

import com.jjsd.options.entity.vo.RecommendationVO;

import java.util.List;

public interface InvestService {

    /**
     * 获得用户推荐列表
     * @param email
     * @return
     */
    public List<RecommendationVO> getDecision(String email);

}
