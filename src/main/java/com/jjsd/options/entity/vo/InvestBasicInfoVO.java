package com.jjsd.options.entity.vo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by a297 on 17/9/12.
 */
public class InvestBasicInfoVO {
    // 用于 界面 交易代码 自动补全 和 交易名称 的显示
    private Map<String, String> contractCodeAndName;
    // 可用余额 (>=0，最好两位小数)
    // (经本金计算得到，用于交易时判断钱够不够，不够的话界面有交互，拒绝用户发起买入操作)
    private String balance;
    // 目前持有的产品列表
    private ArrayList<HoldingVO> holding;
    // 最高无风险利率，范围[0， 0.1]
    private String noRiskRate;
    // 本金
    private String principal;
    // 总资产
    private String assets;

    public InvestBasicInfoVO(Map<String, String> contractCodeAndName, String balance, ArrayList<HoldingVO> holding, String noRiskRate, String principal, String assets) {
        this.contractCodeAndName = contractCodeAndName;
        this.balance = balance;
        this.holding = holding;
        this.noRiskRate = noRiskRate;
        this.principal = principal;
        this.assets = assets;
    }

    public Map<String, String> getContractCodeAndName() {
        return contractCodeAndName;
    }

    public String getBalance() {
        return balance;
    }

    public ArrayList<HoldingVO> getHolding() {
        return holding;
    }

    public String getNoRiskRate() {
        return noRiskRate;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getAssets() {
        return assets;
    }
}
