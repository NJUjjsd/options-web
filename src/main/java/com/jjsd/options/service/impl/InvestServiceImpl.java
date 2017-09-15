package com.jjsd.options.service.impl;

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
import com.jjsd.options.util.UserInvestUtil;
import com.jjsd.options.util.ResultState;
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
//        Map<String, String> contractCodeAndName = UserInvestUtil.getContractCodeAndName();

        // 个人资产 TODO 拿到个人资产再说
//        Property property = userService.loadPropertyByEmail(email);

//        // 可用余额
//        String balance = UserInvestUtil.getBalance(property);
//        // 目前持有
//        ArrayList<HoldingVO> holding = UserInvestUtil.getHolding(property);
//        // 最高无风险利率
//        String noRiskRate = property.getR()+"";
//        // 本金
//        String principal = property.getB()+"";
//        // 总资产
//        String assets = property.getTotal()+"";

//        return new InvestBasicInfoVO(
//                contractCodeAndName, balance, holding, noRiskRate, principal, assets);

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
                contractCodeAndName, balance, holding, noRiskRate, principal, assets);
        return investBasicInfoVO;
    }

    @Override
    public ResultState userEntrust(UserEntrustVO userEntrustVO) {
        Entrustment entrustment = new Entrustment();

        entrustment.setBuy(userEntrustVO.getIsBuy());
        entrustment.setCode(userEntrustVO.getCode());
        entrustment.setOptionName(userEntrustVO.getOptionName());
        entrustment.setPrice(Double.valueOf(userEntrustVO.getPrice()));
        entrustment.setOptionNum(Integer.valueOf(userEntrustVO.getOptionNum()));

        boolean result = userService.makeOrder(entrustment);
        return result ? ResultState.SUCCEED : ResultState.FAIL;
    }

    @Override
    public ResultState informationEntrust(InformationVO informationVO) {
        Entrustment entrustment_ETF = new Entrustment();

        entrustment_ETF.setBuy(informationVO.getIsBuy());
        entrustment_ETF.setCode(informationVO.getCode());
        entrustment_ETF.setOptionName(informationVO.getOptionName());
        entrustment_ETF.setPrice(Double.valueOf(informationVO.getPrice()));
        entrustment_ETF.setOptionNum(Integer.valueOf(informationVO.getOptionNum()));

        Entrustment entrustment_up = new Entrustment();
        entrustment_up.setBuy(informationVO.getUpIsBuy());
        entrustment_up.setCode(informationVO.getUpCode());
        entrustment_up.setOptionName(informationVO.getUpOptionName());
        entrustment_up.setPrice(Double.valueOf(informationVO.getUpPrice()));
        entrustment_up.setOptionNum(Integer.valueOf(informationVO.getUpOptionNum()));

        Entrustment entrustment_down = new Entrustment();
        entrustment_down.setBuy(informationVO.getDownIsBuy());
        entrustment_down.setCode(informationVO.getDownCode());
        entrustment_down.setOptionName(informationVO.getDownOptionName());
        entrustment_down.setPrice(Double.valueOf(informationVO.getDownPrice()));
        entrustment_down.setOptionNum(Integer.valueOf(informationVO.getDownOptionNum()));

        boolean result_ETF = userService.makeOrder(entrustment_ETF);
        boolean result_up = userService.makeOrder(entrustment_up);
        boolean result_down = userService.makeOrder(entrustment_down);

        return result_ETF && result_up && result_down
                ? ResultState.SUCCEED : ResultState.FAIL;
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
           boolean res = userService.cancelOrder(Long.valueOf(vo.getId()));
            if(!res){
                return ResultState.FAIL;
            }
        }
        return ResultState.SUCCEED;
    }


}
