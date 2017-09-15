package com.jjsd.options.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.jjsd.options.dao.CostRepository;
import com.jjsd.options.dao.MarketDao;
import com.jjsd.options.dao.PropertyRepository;
import com.jjsd.options.entity.user.Cost;
import com.jjsd.options.entity.user.Entrustment;
import com.jjsd.options.entity.user.Option;
import com.jjsd.options.entity.user.Property;
import com.jjsd.options.entity.vo.*;
import com.jjsd.options.service.InvestService;
import com.jjsd.options.service.MarketService;
import com.jjsd.options.service.UserService;
import com.jjsd.options.service.impl.investModel.AdviceModel;
import com.jjsd.options.util.BasicInfoUtil;
import com.jjsd.options.util.InvestMode;
import com.jjsd.options.util.ResultState;
import com.jjsd.options.util.UserInvestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InvestServiceImpl implements InvestService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private UserService userService;
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
                recommendationVO.setX(Double.parseDouble(contactInfoVO.getUpCurrentPrice()));
                recommendationVO.setY(Double.parseDouble(contactInfoVO.getDownCurrentPrice()));
                recommendationVO.setZ(Double.parseDouble(marketDao.getETFBasicInfo().getCurPrice()));

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

                result.add(recommendationVO);



            }
        }

        return result;
    }

    @Override
    public InvestBasicInfoVO getInvestBasicInfo(String email) {
        Map<String, String> contractCodeAndName = UserInvestUtil.getContractCodeAndName();
        // 个人资产
        Property property = userService.loadPropertyByEmail(email);

        if(property==null){
            return new InvestBasicInfoVO(
                    contractCodeAndName, 0+"", null, "0.00", "0.00", "0.00");
        }
        // 可用余额
        String balance = UserInvestUtil.getBalance(property);
        // 目前持有
        ArrayList<HoldingVO> holding = UserInvestUtil.getHolding(property);
        // 最高无风险利率
        String noRiskRate = property.getR()+"";
        // 本金
        String principal =UserInvestUtil.getBalance(property);
        // 总资产
        String assets = property.getTotal()+"";

        return new InvestBasicInfoVO(
                contractCodeAndName, balance, holding, noRiskRate, principal, assets);
    }

    @Override
    public ResultState userEntrust(UserEntrustVO userEntrustVO) {

        Entrustment entrustment = new Entrustment();

        System.out.println(userEntrustVO.getEmail());
        entrustment.setUserEmail(userEntrustVO.getEmail());

        System.out.println(userEntrustVO.getIsBuy());
        entrustment.setBuy(userEntrustVO.getIsBuy());

        System.out.println(userEntrustVO.getCode());
        entrustment.setCode(userEntrustVO.getCode());

        System.out.println(userEntrustVO.getOptionName());
        entrustment.setOptionName(userEntrustVO.getOptionName());

        System.out.println(Double.valueOf(userEntrustVO.getPrice()));
        entrustment.setPrice(Double.valueOf(userEntrustVO.getPrice()));

        System.out.println(Integer.valueOf(userEntrustVO.getOptionNum()));
        entrustment.setOptionNum(Integer.valueOf(userEntrustVO.getOptionNum()));

        boolean result = userService.makeOrder(entrustment);
        return result ? ResultState.SUCCEED : ResultState.FAIL;
    }

    @Override
    public ArrayList<UserEntrustVO> getUserEntrust(String email) {
        // 委托列表
        List<Entrustment> entrustments = userService.getEntrustmentList(email);
        ArrayList<UserEntrustVO> userEntrustVOs = new ArrayList<>(entrustments.size());
        for (Entrustment e:entrustments) {
            String code = e.getCode();
            String name = e.getOptionName();
            String price = e.getPrice()+"";
            String num = e.getOptionNum()+"";
            String id = e.getEntrustmentId()+"";
            boolean isBuy = e.isBuy();
            userEntrustVOs.add(
                    new UserEntrustVO(email, code, name, isBuy, num, price, id)
            );
        }
        return userEntrustVOs;
    }

    @Override
    public ResultState cancelEntrust(ArrayList<UserEntrustVO> userEntrustVOs) {
        for (UserEntrustVO vo:userEntrustVOs) {
            System.out.println("id");
            System.out.println(vo.getId());
           boolean res = userService.cancelOrder(Long.valueOf(vo.getId()));
            if(!res){
                return ResultState.FAIL;
            }
        }
        return ResultState.SUCCEED;
    }

    public ArrayList<InformationVO> getInformationVOs(String email) {

        // 组合策略列表
        List<RecommendationVO> recommendationVOs = this.getDecision(email);
        // 返回的组合列表
        ArrayList<InformationVO> informationVOs = new ArrayList<>();
        // 代码－名称
        Map<String, String> contactAndName = UserInvestUtil.getContractCodeAndName();

            for (Iterator iter = recommendationVOs.iterator(); iter.hasNext();) {
                RecommendationVO rec = (RecommendationVO)iter.next();
                // 根据代码拿到名称
                String upCode = rec.getCallOptionCode();
                String upOptionName = contactAndName.get(upCode);
                String upPrice = rec.getX()+"";
                boolean upIsBuy = true;

                String code = "510050";
                String optionName = contactAndName.get(code);
                String price = rec.getZ()+"";
                boolean isBuy = !upIsBuy;

                String downCode = rec.getPutOptionCode();
                String downOptionName = contactAndName.get(downCode);
                String downPrice = rec.getY()+"";
                boolean downIsBuy = !upIsBuy;

                String each = rec.getProfit()+"";

                InformationVO info = new InformationVO(
                        email, code, optionName, price, isBuy,
                        upCode, upOptionName, upPrice, upIsBuy,
                        downCode, downOptionName, downPrice, downIsBuy,
                        each);
                informationVOs.add(info);
            }

        return informationVOs;

//        return UserInvestVOService.generateInformation();
    }


}
