package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.entity.vo.InformationVO;
import com.jjsd.options.entity.vo.InvestBasicInfoVO;
import com.jjsd.options.entity.vo.UserEntrustVO;
import com.jjsd.options.entity.vo.UserInvestVOService;
import com.jjsd.options.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by a297 on 17/9/9.
 */
@Controller
@RequestMapping(value = "/api/userInvest", produces = "application/json;charset=UTF-8")
public class UserInvestController {

    @Autowired
    private InvestService investService;

    private static int count = 0;


    @GetMapping(value = "/investBasicInfo")
    public @ResponseBody String getInvestBasicInfo(@RequestParam String email){
        // TODO
        count ++ ;
        String e = count+"hhh";
        InvestBasicInfoVO investBasicInfoVO = investService.getInvestBasicInfo(e);
        return JSON.toJSONString(investBasicInfoVO);
    }
    @PostMapping(value = "/entrust")
    public @ResponseBody String userEntrust(@RequestBody Map<String, String> entrust) {
        UserEntrustVO userEntrustVO = UserInvestVOService.getUserEntrustVO(entrust);
        return JSON.toJSONString(investService.userEntrust(userEntrustVO).toString());
    }

    @PostMapping(value = "/combinationEntrust")
    public @ResponseBody String combinationEntrust(@RequestBody Map<String, String> combinationEntrust) {
        InformationVO informationVO = UserInvestVOService.getInformationEntrustVO(combinationEntrust);
        return JSON.toJSONString(investService.informationEntrust(informationVO));
    }
}
