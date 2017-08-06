package com.jjsd.options.util;

import com.jjsd.options.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujing on 2017/8/5.
 */
public class CrawlerUtil {


    private static final Map<String, String> stockCode;

    static{
        stockCode=new HashMap<>();

        stockCode.put("600000","浦发银行");
        stockCode.put("600016","民生银行");
        stockCode.put("600028","中国石化");
        stockCode.put("600029","南方航空");
        stockCode.put("600030","中信证券");
        stockCode.put("600036","招商银行");
        stockCode.put("600048","保利地产");
        stockCode.put("600050","中国联通");
        stockCode.put("600100","同方股份");
        stockCode.put("600104","上汽集团");

        stockCode.put("600111","北方稀土");
        stockCode.put("600340","华夏幸福");
        stockCode.put("600485","信威集团");
        stockCode.put("600518","康美药业");
        stockCode.put("600519","贵州茅台");
        stockCode.put("600547","山东黄金");
        stockCode.put("600606","绿地控股");
        stockCode.put("600837","海通证券");
        stockCode.put("600887","伊利股份");
        stockCode.put("600919","江苏银行");

        stockCode.put("600958","东方证券");
        stockCode.put("600999","招商证券");
        stockCode.put("601006","大秦铁路");
        stockCode.put("601088","中国神华");
        stockCode.put("601166","兴业银行");
        stockCode.put("601169","北京银行");
        stockCode.put("601186","中国铁建");
        stockCode.put("601198","东兴证券");
        stockCode.put("601211","国泰君安");
        stockCode.put("601229","上海银行");

        stockCode.put("601288","农业银行");
        stockCode.put("601318","中国平安");
        stockCode.put("601328","交通银行");
        stockCode.put("601336","新华保险");
        stockCode.put("601390","中国中铁");
        stockCode.put("601398","工商银行");
        stockCode.put("601601","中国太保");
        stockCode.put("601628","中国人寿");
        stockCode.put("601668","中国建筑");
        stockCode.put("601688","华泰证券");

        stockCode.put("601766","中国中车");
        stockCode.put("601788","光大证券");
        stockCode.put("601800","中国交建");
        stockCode.put("601818","光大银行");
        stockCode.put("601857","中国石油");
        stockCode.put("601881","中国银河");
        stockCode.put("601901","方正证券");
        stockCode.put("601985","中国核电");
        stockCode.put("601988","中国银行");
        stockCode.put("601989","中国重工");

    }

//    http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllBulletin/stockid/600000.phtml
//    http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllNewsStock/symbol/sh600000.phtml
//    http://vip.stock.finance.sina.com.cn/q/go.php/vReport_List/kind/search/index.phtml?symbol=600000&t1=all

//    http://app.finance.ifeng.com/info/news_gsxw.php?code=sh600000

//    http://app.finance.ifeng.com/info/news_gsxw.php?code=sh600000

    public static void main(String[] args) {
        Document doc=null;
        try {

//            doc = Jsoup.connect("http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllNewsStock/symbol/sh600000.phtml").get();
            doc = Jsoup.connect("http://finance.sina.com.cn/stock/s/2017-08-03/doc-ifyitamv4569554.shtml").get();




        } catch (IOException e) {
            e.printStackTrace();
        }
//        新浪个股资讯list
//        Element element=doc.select("div.datelist").first();
//        Element ul=element.getElementsByTag("ul").first();
//        Elements detail=ul.getElementsByTag("a");
//        Iterator iterator=detail.iterator();
//        while (iterator.hasNext()){
//            Element e= (Element) iterator.next();
//            System.out.println(e.text()+" "+e.attr("href"));
//        }

//        新浪公司公告detail
//        Element element=doc.select("div#content").first();
//        System.out.println(element.getElementsByTag("pre").first().text());


//        新浪个股资讯detail
//        Element element=doc.select("div.left").first();
//        Elements elements=element.getElementsByTag("p");
//        Iterator iterator=elements.iterator();
//        while (iterator.hasNext()){
//            Element e= (Element) iterator.next();
//            System.out.println(e.text());
//        }





    }

    //新浪个股新闻
    private List<News> getNFromSina(String code){
        return null;

    }

    //新浪个股公告
    private List<News> getRFromSina(String code){
        return null;

    }

    //新浪个股研报
    private List<News> getAFromSina(String code){
        return null;

    }


    //凤凰个股新闻
    private List<News> getNFromIfeng(String code){
        return null;
    }

    //东方财富网个股研报
    private List<News> getAFromEastmoney(String code){
        return  null;
    }




}
