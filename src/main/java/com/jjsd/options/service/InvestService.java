package com.jjsd.options.service;

import com.jjsd.options.entity.vo.*;
import com.jjsd.options.util.ResultState;

import java.util.ArrayList;

public interface InvestService {

    /**
     * 获得用户推荐列表
     * @param email
     * @return
     */
    java.util.List<RecommendationVO> getDecision(String email);

    InvestBasicInfoVO getInvestBasicInfo(String email);

    ResultState userEntrust(UserEntrustVO userEntrustVO);

    ResultState informationEntrust(InformationVO informationVO);

    ArrayList<UserEntrustVO> getUserEntrust(String email);

    ResultState cancelEntrust(ArrayList<UserEntrustVO> userEntrustVOs);


}
