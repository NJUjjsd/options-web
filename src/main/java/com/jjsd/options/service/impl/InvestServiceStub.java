package com.jjsd.options.service.impl;

import com.jjsd.options.entity.vo.*;
import com.jjsd.options.service.InvestService;
import com.jjsd.options.util.ResultState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a297 on 17/9/9.
 */
@Service
public class InvestServiceStub implements InvestService {

    @Override
    public List<RecommendationVO> getDecision(String email) {
        return null;
    }

    @Override
    public InvestBasicInfoVO getInvestBasicInfo(String email) {
        Map<String, String > contractCodeAndName = new HashMap<>();
        contractCodeAndName.put("510050", "上证50ETF");
        contractCodeAndName.put("510050C1709M02250", "名字1");
        contractCodeAndName.put("510050P1709M02250", "名字2");
        contractCodeAndName.put("510050C1709M02300", "名字3");

        String balance = "10000.99";

        ArrayList<HoldingVO> holding = new ArrayList<>(4);
        HoldingVO vo0 = new HoldingVO("510050C1709M02250", email+"名字1", "100", "2.45", "+0.34", "+10000");
        HoldingVO vo1 = new HoldingVO("510050P1709M02250", email+"名字2",  "200", "2.45", "+0.34", "+10000");
        HoldingVO vo2 = new HoldingVO("510050C1709M02300", email+"名字3", "300", "2.45", "+0.34", "+10000");
        HoldingVO vo3 = new HoldingVO("510050", "上证50ETF", "400", "2.45", "+0.34", "+10000");
        holding.add(vo0);
        holding.add(vo1);
        holding.add(vo2);
        holding.add(vo3);

        String noRiskRate = "0.025";
        String principal = "5000";
        String assets = "15000";

        InvestBasicInfoVO investBasicInfoVO = new InvestBasicInfoVO(
                contractCodeAndName, balance, holding, noRiskRate, principal, assets, null
        );
        return investBasicInfoVO;
    }

    @Override
    public ResultState userEntrust(UserEntrustVO userEntrustVO) {
        if (userEntrustVO.getEmail().equals("875928078@qq.com")){
            return ResultState.SUCCEED;
        }
        return ResultState.FAIL;
    }

    @Override
    public ResultState informationEntrust(InformationVO informationVO) {
        if (informationVO.getEmail().equals("875928078@qq.com")){
            return ResultState.SUCCEED;
        }
        return ResultState.FAIL;
    }
}
