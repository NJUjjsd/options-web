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

        return new UserEntrustVO(email, code, optionName, isBuy, optionNum, price);
    }
    public static InformationVO getInformationEntrustVO(Map<String, String> information){
        String email = information.get("email");
        String code = information.get("code");
        String optionName = information.get("optionName");
        String price = information.get("price");
        String optionNum = information.get("optionNum");
        boolean isBuy = Boolean.parseBoolean(information.get("isBuy"));
        String upCode = information.get("upCode");
        String upOptionName = information.get("upOptionName");
        String upPrice = information.get("upPrice");
        String upOptionNum = information.get("upOptionNum");
        boolean upIsBuy = Boolean.parseBoolean(information.get("upIsBuy"));
        String downCode = information.get("downCode");
        String downOptionName = information.get("downOptionName");
        String downPrice = information.get("downPrice");
        String downOptionNum = information.get("downOptionNum");
        boolean downIsBuy = Boolean.parseBoolean(information.get("downIsBuy"));
        String totalProfit = information.get("totalProfit");

        return new InformationVO(email,
                code, optionName, price, optionNum, isBuy,
                upCode, upOptionName, upPrice, upOptionNum, upIsBuy,
                downCode, downOptionName, downPrice, downOptionNum, downIsBuy,
                totalProfit);
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
                    eachCancel.get("price")
            );
            vos.add(vo);
        }
        return vos;
    }

    public static InformationVO generateInformation(){
        return new InformationVO(
                "875928078@qq.com",
                "510050", "上证50ETF", "2.3", "130000", true,
                "510050C1709M02250", "name1", "2.1", "100", true,
                "510050P1709M02250", "name2", "2.3", "130000", false,
                "2333333");
    }
}
