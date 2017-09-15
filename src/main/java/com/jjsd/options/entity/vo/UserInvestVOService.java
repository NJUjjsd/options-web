package com.jjsd.options.entity.vo;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by a297 on 17/9/14.
 */
public class UserInvestVOService {
    public static UserEntrustVO getUserEntrustVO(Map<String, String> entrust){
        String email = entrust.get("email");
        String code = entrust.get("code");
        String optionName = entrust.get("optionName");
        boolean isBuy = Boolean.getBoolean(entrust.get("isBuy"));
        String optionNum = entrust.get("optionNum");
        String price = entrust.get("price");

        return new UserEntrustVO(email, code, optionName, isBuy, optionNum, price, "23333333333");
    }

    public static ArrayList<UserEntrustVO> getCancelEntrustVOs(JSONArray array) {
        ArrayList<UserEntrustVO> vos = new ArrayList<>(array.size());
        for (Object obj : array) {
            Map<String, String> eachCancel = (Map<String, String>) obj;
            UserEntrustVO vo = new UserEntrustVO(
                    eachCancel.get("email"),
                    eachCancel.get("code"),
                    eachCancel.get("optionName"),
                    Boolean.parseBoolean(eachCancel.get("isBuy")),
                    eachCancel.get("optionNum"),
                    eachCancel.get("price"),
                    "23333333333"
            );
            vos.add(vo);
        }
        return vos;
    }

    public static ArrayList<InformationVO> generateInformation(){
        ArrayList<InformationVO> vos = new ArrayList<>(1);
        vos.add(new InformationVO(
                "875928078@qq.com",
                "510050", "上证50ETF", "2.3", true,
                "510050C1709M02250", "name1", "2.1",true,
                "510050P1709M02250", "name2", "2.3",false,
                "2.22"));
        return vos;
    }
}
