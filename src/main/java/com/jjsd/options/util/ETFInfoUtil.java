package com.jjsd.options.util;

import com.jjsd.options.entity.market.ETFBaseInfo;
import com.jjsd.options.entity.market.ETFInfo;
import com.jjsd.options.entity.market.TransUnitPo;
import com.jjsd.options.entity.vo.ContactInfoVO;
import com.jjsd.options.entity.vo.ETFBasicInfoVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ${zrz} on 2017/8/14.
 */
public class ETFInfoUtil {
    static String headUrl = "http://finance.sina.com.cn/fund/quotes/";
    static String endUrl="/bc.shtml";
    //etf期权网址
    static String optionUrl="http://www.sse.com.cn/assortment/options/price/";
    //查看有那几个月合约
    static String monthUrl = "http://stock.finance.sina.com.cn/futures/api/openapi.php/StockOptionService.getStockName";
    //查看合约剩余日期
    static String remainedDayUrl="http://stock.finance.sina.com.cn/futures/api/openapi.php/StockOptionService.getRemainderDay?date=";
    //查看某月份到期的看涨期权代码列表
    static String upListUrl = "http://hq.sinajs.cn/list=OP_UP_510050";
    //查看某月份到期的看跌期权代码列表
    static String downListUrl = "http://hq.sinajs.cn/list=OP_DOWN_510050";
    //根据合约代码获得实时期权行情
    static String nowInfoUrl="http://hq.sinajs.cn/list=CON_OP_";
    //期权隐含波动率
    static String FluctuationUrl = "http://hq.sinajs.cn/list=CON_SO_";
    //50etf实时行情
    static String etfNowInfoUrl="http://hq.sinajs.cn/list=sh510050";
    //50etf实时行情详细信息
    static String etfNowDetailUrl = "http://hq.sinajs.cn/list=s_sh510050";
    /**
     *
     * @param code 股票代码
     * @return
     */
    public static String getOtherInfo(String code){
        Document doc = null;
        String url = headUrl+code+endUrl;
        try {
            doc = Jsoup.connect(url).get();

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        //System.out.println(doc.toString());
        //实时涨跌幅

        double nowRise =Double.valueOf(getWord("fund_data_item","涨跌幅",doc));
        double unitValue = Double.valueOf(getWord("fund_data_item","单位净值",doc));
        double threeMRise = Double.valueOf(getWord("fund_data_item","近3月涨幅",doc));
        double oneYRise = Double.valueOf(getWord("fund_data_item","近1年涨幅",doc));
        double threeYRise = Double.valueOf(getWord("fund_data_item","近3年涨幅",doc));
        String risePlace = getWord("fund_data_item","涨跌幅排名",doc);
        String threeMPlace = getWord("fund_data_item","近3月涨幅排名",doc);
        String oneYPlace = getWord("fund_data_item","近1年涨幅排名",doc);
        String threeYPlace = getWord("fund_data_item","近3年涨幅排名",doc);
        System.out.println(oneYPlace+threeMRise);

        //抓取基本信息
        String formDate= getBaseWord("fund_info_item","成立日期",doc);
        double latestScale =Double.valueOf( getBaseWord("fund_info_item","最新规模",doc));
        String manager = getBaseWord("fund_info_item","管理人",doc);
        double accUnitValue = Double.valueOf(getBaseWord("fund_info_item","累计单位净值",doc));
        double drawsBouns = Double.valueOf(getBaseWord("fund_info_item","累计分红",doc));
        String fundManager = getBaseWord("fund_info_item","基金经理",doc);
        System.out.println(formDate+latestScale+fundManager);
        ETFBaseInfo baseInfo = new ETFBaseInfo(formDate,latestScale,manager,accUnitValue,drawsBouns,fundManager);
        ETFInfo etfInfo = new ETFInfo(unitValue,nowRise,threeMRise,oneYRise,threeYRise,threeMPlace,risePlace,oneYPlace,threeYPlace,baseInfo);
        System.out.println(JSONObject.fromObject(etfInfo).toString());
        return "wrong";
    }

    /**
     * 抽取信息
     * @param className 所在类名
     * @param titleName 需要抽取的名字
     * @return
     */
    private static String getWord(String className,String titleName,Document document){
        String result;
        Elements element = document.getElementsByClass(className).select(":contains("+titleName+")").select("span");
        String valueText ;
        System.out.println(element);
        if(element.size()>1){
            valueText=element.get(1).text();
        }
        else {
            valueText=element.text();
        }
        valueText=valueText.split(" ")[0];
        System.out.println(valueText);
        if(valueText.contains("%")){
            valueText = valueText.substring(0,valueText.length()-1);
        }
        return valueText;
    }

    /**
     * 抽取基本信息的字段
     * @param className
     * @param titleName
     * @param document
     * @return
     */
    private static String getBaseWord(String className,String titleName,Document document){
        String result;
        Elements element = document.getElementsByClass(className).select("span").select(":contains("+titleName+")");
        String valueText ;
        System.out.println(element);
        if(element.size()>1){
            valueText=element.get(1).text();
        }
        else {
            valueText=element.text();
        }
        valueText=valueText.split("：")[1];

        if(valueText.contains("元") ||valueText.contains("亿")){
            valueText = valueText.substring(0,valueText.length()-1);
        }
        System.out.println(valueText);
        return valueText;
    }

    /**
     * 查询url获得信息
     * @param url
     * @return
     */
    public static String queryUrl(String url){
        try {
            URL netUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(netUrl.openConnection().getInputStream(),"GBK"));
            return reader.readLine();
            //JSONObject object = JSONObject.fromObject(reader.readLine());
            //JSONArray array =object.getJSONObject("result").getJSONObject("data").getJSONArray("contractMonth");


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到所有月份，格式为yyyy-mm
     * @return
     */
    public static ArrayList getAllMonths(){
        String queryLine = queryUrl(monthUrl);
        JSONObject object = JSONObject.fromObject(queryLine);
        JSONArray array = object.getJSONObject("result").getJSONObject("data").getJSONArray("contractMonth");
        array.remove(0);
        return new ArrayList<>(Arrays.asList(array.toArray()));
    }

    /**
     * 日期格式转换，yyyy-mm到yymm
     * @param prime
     * @return
     */
    public static String monthTrans(String prime){
        String year = prime.substring(2,4);
        String month =prime.substring(5,7);
        return year+month;
    }

    /**
     * 处理爬取的list字符串，转为arraylist
     * @param prime
     * @return
     */
    public static ArrayList<String> dealListInfo(String prime){
        int first = prime.indexOf("=")+2;
        int last = prime.length()-2;
        prime = prime.substring(first,last);
        String [] resultList = prime.split(",");
        return new ArrayList<>(Arrays.asList(resultList));

    }

    /**
     * 爬起etf实时信息
     * @return
     */
    public static ETFBasicInfoVO getNowETFInfo(){
        String query1 = queryUrl(etfNowInfoUrl);
        String query2 = queryUrl(etfNowDetailUrl);
        ArrayList<String> info1=dealListInfo(query1);
        ArrayList<String> info2 = dealListInfo(query2);
        System.out.println(JSONArray.fromObject(info2).toString());

        String name = info2.get(0);
        String nowPrice = info2.get(1);
        String rise = info2.get(2);  //涨跌额
        String riseScope = info2.get(3)+"%"; //涨跌幅
        String turnOver = info2.get(4);   //成交量，单位手
        String volume = info2.get(5);     //成交额，单位万元
        double lastPrice = Double.valueOf(info1.get(2));
        double highPrice = Double.valueOf(info1.get(4));
        double lowPrice = Double.valueOf(info1.get(5));
        double amplitude = (highPrice-lowPrice)/lastPrice*100;
        amplitude = dateTransUtil.precisionTrans(amplitude,2);
        String amplitudeStr = amplitude+"";
        ETFBasicInfoVO basicInfoVO = new ETFBasicInfoVO("510050",name,nowPrice,rise,riseScope,amplitudeStr,turnOver,volume);
        return basicInfoVO;
    }
    /**
     * 得到etf行情时间
     */
    public static String getETFTime(){
        String query1 = queryUrl(etfNowInfoUrl);
        ArrayList<String> info1=dealListInfo(query1);
        String day = info1.get(info1.size()-3);
        String time = info1.get(info1.size()-2);

        return day+" "+time;
    }
    /**
     * 根据代码得到单元交易信息
     * @param id
     * @return
     */
    private static TransUnitPo getTransUnit(String id){
        String info1Str = queryUrl(nowInfoUrl+id);
        ArrayList<String> info1 = dealListInfo(info1Str);
        String  currentPrice = info1.get(2);
        String  Fluctuation =info1.get(6);
        String  preClosingPrice = info1.get(8);
        String  upExercisePrice = info1.get(7);
        String info2Str = queryUrl(FluctuationUrl+id);
        ArrayList<String> info2 = dealListInfo(info2Str);
        String TradingCode = info2.get(12);
        String time = info1.get(32);
        TransUnitPo unitPo = new TransUnitPo(upExercisePrice,TradingCode,currentPrice,Fluctuation,preClosingPrice,time);
        //System.out.println(JSONObject.fromObject(unitPo).toString());

        return unitPo;
    }
    public static String getMonthTime(String month){
        ArrayList list = new ArrayList();
        month = monthTrans(month);
        String upListStr = queryUrl(upListUrl+month);
        ArrayList<String> upList = dealListInfo(upListStr);
        String upId = upList.get(0).substring(7);
        String info1Str = queryUrl(nowInfoUrl+upId);
        ArrayList<String> info1 = dealListInfo(info1Str);
        String time = info1.get(32);
        return time;
    }
    /**
     * 根据月份爬取信息
     * @param month 格式为yyyy-mm
     * @return
     */
    public static ArrayList getInfoByMonth(String month){
        ArrayList list = new ArrayList();
        month = monthTrans(month);
        String upListStr = queryUrl(upListUrl+month);
        String downListStr= queryUrl(downListUrl+month);
        ArrayList<String> upList = dealListInfo(upListStr);
        ArrayList<String> downList = dealListInfo(downListStr);

        for(int i=0;i<upList.size();i++){
            String upId = upList.get(i).substring(7);
            String downId = downList.get(i).substring(7);
            TransUnitPo upUnit = getTransUnit(upId);
            TransUnitPo downUnit = getTransUnit(downId);
            ContactInfoVO vo = new ContactInfoVO(upUnit.getUpExercisePrice(),upUnit.getTradingCode(),upUnit.getCurrentPrice(),upUnit.getFluctuation(),upUnit.getPreClosingPrice(),downUnit.getTradingCode(),downUnit.getCurrentPrice(),downUnit.getFluctuation(),downUnit.
                    getPreClosingPrice());
            //System.out.println(JSONObject.fromObject(vo).toString());
            list.add(vo);
        }

        return list;

    }
    /**
     * 爬取etf期权信息（表格）
     */
    public static void getOptionInfo(){


    }
    public static void main(String[] args) {
        getNowETFInfo();
        //System.out.println(JSONArray.fromObject(getAllMonths()).toString());
        System.out.println(JSONArray.fromObject(getInfoByMonth("2018-03")).toString());
        //getOtherInfo("510050");
    }
}
