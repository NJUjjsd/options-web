package com.jjsd.options.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jjsd.options.entity.ETFBaseInfo;
import com.jjsd.options.entity.ETFInfo;
import com.mongodb.util.JSON;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

/**
 * Created by ${zrz} on 2017/8/14.
 */
public class ETFInfoUtil {
    static String headUrl = "http://finance.sina.com.cn/fund/quotes/";
    static String endUrl="/bc.shtml";

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
    public static void main(String[] args) {
        getOtherInfo("510050");
    }
}
