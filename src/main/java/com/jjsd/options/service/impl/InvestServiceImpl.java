package com.jjsd.options.service.impl;

import com.jjsd.options.dao.CostRepository;
import com.jjsd.options.dao.MarketDao;
import com.jjsd.options.dao.PropertyRepository;
import com.jjsd.options.entity.user.Cost;
import com.jjsd.options.entity.user.Option;
import com.jjsd.options.entity.user.Property;
import com.jjsd.options.entity.vo.*;
import com.jjsd.options.service.InvestService;
import com.jjsd.options.service.MarketService;
import com.jjsd.options.service.impl.investModel.AdviceModel;
import com.jjsd.options.util.BasicInfoUtil;
import com.jjsd.options.util.InvestMode;
import com.jjsd.options.util.ResultState;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InvestServiceImpl implements InvestService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private MarketService marketService;
    @Autowired
    private MarketDao marketDao;

    /**
     * 获取ETF的剩余天数
     *
     * @param code
     * @return 剩余天数
     */
    private Integer getRemainderDays(String code) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date endDate = simpleDateFormat.parse(BasicInfoUtil.getEndDate(code));
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            double end = cal.getTimeInMillis();
            cal.setTime(today);
            double now = cal.getTimeInMillis();
            Integer between_days = Math.toIntExact(Math.round((end - now) / (86400000))); //1000 * 3600 * 24 = 86400000
            return between_days;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<RecommendationVO> getDecision(String email) {
        List<RecommendationVO> result=new ArrayList<>();

        Property property=propertyRepository.findByEmail(email);
        Cost cost=costRepository.findByEmail(email);
        if (property==null||cost==null){
            throw new NullPointerException();
        }

        //
        List l=property.getOptions();
        double b=property.getB();


        //
        List list=marketService.getAllContactInfo();
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            List list1= (List) iterator.next();
            Iterator iterator1=list1.iterator();

            //
            while (iterator1.hasNext()){
                ContactInfoVO contactInfoVO= (ContactInfoVO) iterator1.next();
                Integer remainderDays = getRemainderDays(BasicInfoUtil.getId(contactInfoVO.getUpTradingCode()));

                AdviceModel adviceModel=new AdviceModel(Double.parseDouble(contactInfoVO.getUpCurrentPrice()),Double.parseDouble(contactInfoVO.getDownCurrentPrice()),Double.parseDouble(marketDao.getETFBasicInfo().getCurPrice()),Double.parseDouble(contactInfoVO.getUpExercisePrice()),property.getR(),cost.getC1(),cost.getC2(),cost.getC3(),cost.getC4(),cost.getC5(),cost.getC6(),remainderDays, InvestMode.NORMAL);
                ModelResultVO modelResultVO=adviceModel.getDecision();

                if (modelResultVO.getState().equals("NONE")){
                    continue;
                }

                //

                Option option1=new Option();
                option1.setCode(contactInfoVO.getDownTradingCode());
                Option option2=new Option();
                option2.setCode(contactInfoVO.getUpTradingCode());
                RecommendationVO recommendationVO=new RecommendationVO();

                if((modelResultVO.getState().equals("BUY")&&!l.contains(option1))){
                    adviceModel=new AdviceModel(Double.parseDouble(contactInfoVO.getUpCurrentPrice()),Double.parseDouble(contactInfoVO.getDownCurrentPrice()),Double.parseDouble(marketDao.getETFBasicInfo().getCurPrice()),Double.parseDouble(contactInfoVO.getUpExercisePrice()),property.getR(),cost.getC1(),cost.getC2(),cost.getC3(),cost.getC4(),cost.getC5(),cost.getC6(),remainderDays, InvestMode.ONLY_BUY);
                    modelResultVO=adviceModel.getDecision();

                    recommendationVO.setOnlyBuy(true);
                    recommendationVO.setCallOptionCode(contactInfoVO.getUpTradingCode());
                    recommendationVO.setPutOptionCode(null);
                    recommendationVO.setCallBuy(true);
                    recommendationVO.setProfit(modelResultVO.getProfit());



                }else if((modelResultVO.getState().equals("SELL")&&!l.contains(option2))){
                    adviceModel=new AdviceModel(Double.parseDouble(contactInfoVO.getUpCurrentPrice()),Double.parseDouble(contactInfoVO.getDownCurrentPrice()),Double.parseDouble(marketDao.getETFBasicInfo().getCurPrice()),Double.parseDouble(contactInfoVO.getUpExercisePrice()),property.getR(),cost.getC1(),cost.getC2(),cost.getC3(),cost.getC4(),cost.getC5(),cost.getC6(),remainderDays, InvestMode.ONLY_BUY);
                    modelResultVO=adviceModel.getDecision();

                    recommendationVO.setOnlyBuy(true);
                    recommendationVO.setCallOptionCode(null);
                    recommendationVO.setPutOptionCode(contactInfoVO.getDownTradingCode());
                    recommendationVO.setCallBuy(false);
                    recommendationVO.setProfit(modelResultVO.getProfit());



                }else {
                    recommendationVO.setCallOptionCode(contactInfoVO.getUpTradingCode());
                    recommendationVO.setPutOptionCode(contactInfoVO.getDownTradingCode());
                    recommendationVO.setOnlyBuy(false);
                    recommendationVO.setCallBuy(true);
                    recommendationVO.setProfit(modelResultVO.getProfit());

                }

                list.add(recommendationVO);



            }
        }

        return list;
    }

    @Override
    public InvestBasicInfoVO getInvestBasicInfo(String email) {
        return null;
    }

    @Override
    public ResultState userEntrust(UserEntrustVO userEntrustVO) {
        return null;
    }

    @Override
    public ResultState informationEntrust(InformationVO informationVO) {
        return null;
    }



}
