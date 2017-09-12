package com.jjsd.options.entity.user;

import com.jjsd.options.dao.MarketDao;
import com.jjsd.options.dao.impl.MarketDaoImpl;
import com.jjsd.options.util.BasicInfoUtil;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by zhujing on 2017/9/7.
 */
@Embeddable
public class Option implements Serializable {
    private static MarketDao marketDao;
    static {
        marketDao=new MarketDaoImpl();
    }

    //代码，名称，可卖数量，成本价
    public Option() {
    }

    private String code;

    private String name;

    private int availableNum;

    private double cost;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * 获得当前价
     * @return
     */
    public double getNewestPrice(){
        if(code.equals("510050")){
            return Double.parseDouble(marketDao.getETFBasicInfo().getCurPrice());

        }
        return Double.parseDouble(marketDao.getEtfById(BasicInfoUtil.getId(this.code)).getCurrentPrice());


    }

    /**
     * 获得差价=(当前价-成本价)*数量
     * @return
     */
    public double getPriceDifference(){
        return (this.getNewestPrice()-this.cost)*this.availableNum;
    }

    /**
     * 涨跌幅
     * @return
     */
    public double getPriceChangePercent(){
        if(code.equals("510050")){
            return Double.parseDouble(marketDao.getETFBasicInfo().getFluPrice());
        }
        return Double.parseDouble(marketDao.getEtfById(BasicInfoUtil.getId(this.code)).getFluctuation());


    }


    @Override
    public boolean equals(Object obj) {

        Option option= (Option) obj;

        if (option.getCode().equals(this.getCode())){
            return true;
        }
        return false;
    }
}


