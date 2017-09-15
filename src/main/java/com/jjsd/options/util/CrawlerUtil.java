package com.jjsd.options.util;

import com.jjsd.options.dao.NewsRepository;
import com.jjsd.options.entity.news.News;
import com.jjsd.options.service.TradeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhujing on 2017/8/5.
 */
@Component
public class CrawlerUtil {
//    .replace("银华杯十佳银行理财师大赛，惊喜大奖至高荣誉等你来！","").replace("新浪财经App：直播上线 博主一对一指导 新浪港股APP：实时行情 独家内参","")

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TradeService tradeService;

    public static final Map<String, String> stockCode;

    public static final List<String> type;

    static{
        stockCode=new HashMap<>();
        type=new ArrayList<>();

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


        stockCode.put("510050","上证50ETF");

        type.add("新闻");
        type.add("公告");
        type.add("研报");

    }


    //新浪个股新闻
    public static List<News> getNFromSina(String code){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        List<News> list=new ArrayList<>();
        try {
            String URL="http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllNewsStock/symbol/sh"+code+".phtml";
            Document doc = Jsoup.parse(new URL(URL).openStream(), "GBK", URL);
            Element element=doc.select("div.datelist").first();
            Element ul=element.getElementsByTag("ul").first();
            Elements detail=ul.getElementsByTag("a");

            Iterator iterator=detail.iterator();
            while (iterator.hasNext()){

                News news=new News();
                Element e= (Element) iterator.next();
                String title=getUTF8BytesFromGBKString(e.text());
                String url=e.attr("href");

                if(url.indexOf("2017")<0){
                    continue;
                }
                String time=url.substring(url.indexOf("2017"),url.indexOf("2017")+10);

//                if (time.compareTo(this.lastTime(code,"新闻"))<0){
//                    continue;
//                }
                if (time.compareTo("2017-07-00")<0){
                    continue;
                }
                news.setTitle(title);
                news.setTop(kewordCheck(title));
                news.setUrl(url);
                news.setCode(stockCode.get(code)+code);
                news.setType("新闻");
                news.setReadNum(0);
                news.setDate(sdf.parse(time));

                list.add(news);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        Iterator it=list.iterator();
        while (it.hasNext()){
            News news= (News) it.next();
            StringBuilder text=new StringBuilder();
            try {
                Document doc1 = Jsoup.connect(news.getUrl()).get();
                Element element1=doc1.select("div.left").first();
                Elements elements=element1.getElementsByTag("p");

                Iterator iterator1=elements.iterator();
                while (iterator1.hasNext()){
                    Element e= (Element) iterator1.next();
                    text.append(e.text()+"\r\n");
                }

                news.setText(text.toString().split("进入【新浪财经股吧】讨论")[0].replace("银华杯十佳银行理财师大赛，惊喜大奖至高荣誉等你来！","").replace("新浪财经App：直播上线 博主一对一指导 新浪港股APP：实时行情 独家内参",""));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(news.getUrl());
                it.remove();
                continue;
            }

        }
        return list;



    }

    //新浪个股公告
    public static List<News> getRFromSina(String code){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        List<News> list=new ArrayList<>();
        try {
            String u="http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllBulletin/stockid/"+code+".phtml";
            Document doc = Jsoup.parse(new URL(u).openStream(), "GBK", u);
            Element element=doc.select("div.datelist").first();
            Element ul=element.getElementsByTag("ul").first();
            Elements detail=ul.getElementsByTag("a");
            Iterator iterator=detail.iterator();
            while (iterator.hasNext()){
                News news=new News();
                Element e= (Element) iterator.next();
                String title=getUTF8BytesFromGBKString(e.text());
                String url="http://vip.stock.finance.sina.com.cn"+e.attr("href");

                news.setTitle(title);
                news.setTop(kewordCheck(title));
                news.setUrl(url);
                news.setCode(stockCode.get(code)+code);
                news.setType("公告");
                news.setReadNum(0);

                list.add(news);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it=list.iterator();
        while (it.hasNext()){
            News news= (News) it.next();
            try {
                Document doc1 = Jsoup.parse(new URL(news.getUrl()).openStream(), "GBK", news.getUrl());
                Element element1=doc1.select("div#content").first().getElementsByTag("pre").first();
                Element element2=doc1.select("[style=text-align:center;height:12px;]").first();

                String text=getUTF8BytesFromGBKString(element1.text());
                String time=element2.text().substring(element2.text().indexOf(":")+1);
                news.setText(text);
//                if(time.compareTo(this.lastTime(code,"公告"))<0||news.getText().equals("公告内容详见附件")){
                if(time.compareTo("2017-07-00")<0||news.getText().equals("公告内容详见附件")){
                    it.remove();
                    continue;
                }else {
                    news.setDate(sdf.parse(time));
                }

            } catch (Exception e) {
                it.remove();
                e.printStackTrace();
                System.out.println(news.getUrl());
                continue;
            }

        }
        return list;

    }

    //新浪个股研报
    public static List<News> getAFromSina(String code){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        List<News> list=new ArrayList<>();
        try {
            String u="http://vip.stock.finance.sina.com.cn/q/go.php/vReport_List/kind/search/index.phtml?symbol="+code+"&t1=all";
            Document doc = Jsoup.parse(new URL(u).openStream(), "GBK", u);
            Elements detail=doc.select("td.tal.f14");

            Iterator iterator=detail.iterator();
            while (iterator.hasNext()){
                Element e= (Element) iterator.next();
                String time=e.nextElementSibling().nextElementSibling().text();

                e=e.getElementsByTag("a").first();
                String title=getUTF8BytesFromGBKString(e.attr("title"));
                String url=e.attr("href");
//                if(time.compareTo(this.lastTime(code,"研报"))<0){
                if(time.compareTo("2017-07-00")<0){
                    continue;
                }

                News news=new News();
                news.setTitle(title);
                news.setTop(kewordCheck(title));
                news.setUrl(url);
                news.setCode(stockCode.get(code)+code);
                news.setType("研报");
                news.setReadNum(0);
                news.setDate( sdf.parse(time));
                list.add(news);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it=list.iterator();
        while (it.hasNext()){
            News news= (News) it.next();
            try {
                Document doc1 = Jsoup.parse(new URL(news.getUrl()).openStream(), "GBK", news.getUrl());
                Element element1=doc1.select("div.blk_container").first().getElementsByTag("p").first();
                element1=Jsoup.parse(element1.toString().replace("&nbsp;&nbsp;&nbsp;","\\r\\n"));
                String text=element1.text().replace("\\r\\n","\r\n");
                news.setText(getUTF8BytesFromGBKString(text));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(news.getUrl());
                continue;
            }


        }
        return list;

    }

    //凤凰个股新闻
    public static List<News> getNFromIfeng(String code){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        List<News> list=new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://app.finance.ifeng.com/info/news_gsxw.php?code=sh"+code).get();
            Elements detail=doc.select("span#title");

            Iterator iterator=detail.iterator();
            while (iterator.hasNext()){
                Element e= (Element) iterator.next();
                Elements elements=e.getElementsByTag("a");
                String time=elements.first().attr("name");
                String url=elements.last().attr("href");
                String title=elements.last().text();
//                if(time.length()<10||time.compareTo(this.lastTime(code,"新闻"))<0){
                if(time.length()<10||time.compareTo("2017-07-00")<0){
                    continue;
                }

                News news=new News();
                news.setTitle(title);
                news.setTop(kewordCheck(title));
                news.setUrl(url);
                news.setCode(stockCode.get(code)+code);
                news.setType("新闻");
                news.setReadNum(0);
                news.setDate( sdf.parse(time));
                list.add(news);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it=list.iterator();
        while (it.hasNext()){
            News news= (News) it.next();
            StringBuilder text=new StringBuilder();
            try {
                Document doc1 = Jsoup.connect(news.getUrl()).get();
                Element element1=doc1.select("div#main_content").first();
                Elements elements=element1.getElementsByTag("p");


                Iterator iterator1=elements.iterator();
                while (iterator1.hasNext()){
                    Element e= (Element) iterator1.next();
                    text.append(e.text()+"\r\n");
                }
                news.setText(text.toString());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(news.getUrl());
                it.remove();
                continue;
            }

        }
        return list;

    }

    //上证50etf公告
    public static List<News> getROfEFromHexun(){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        List<News> list=new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://jingzhi.funds.hexun.com/fundsreport/list.aspx?fundcode=510050").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            Elements detail=doc.select("div#newsList").first().getElementsByTag("ul").first().getElementsByTag("li");
            Iterator iterator=detail.iterator();
            while (iterator.hasNext()){
                Element element= (Element) iterator.next();

                String time=element.getElementsByTag("span").first().text().substring(1,11);
                String title=element.getElementsByTag("a").first().text();
                String url=element.getElementsByTag("a").first().attr("href");

//                if(time.compareTo(this.lastTime("510050","公告"))<0){
                if(time.compareTo("2017-07-00")<0){
                    continue;
                }

                News news=new News();
                news.setTitle(title);
                news.setTop(kewordCheck(title));
                news.setUrl(url);
                news.setCode("上证50ETF510050");
                news.setType("公告");
                news.setReadNum(0);
                news.setDate(sdf.parse(time));
                list.add(news);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it=list.iterator();
        while (it.hasNext()){
            News news= (News) it.next();
            try {
                Document doc1 = Jsoup.connect(news.getUrl()).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
                Element element1=doc1.select("div#mainright").first().getElementsByTag("div").first().getElementsByTag("pre").first();
                String text=element1.text();
                news.setText(text);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(news.getUrl());
                it.remove();
                continue;
            }


        }
        return list;
    }

    //基金要闻
    public static List<News> getNofEFromTencent(){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        List<News> list=new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://finance.qq.com/fund/jjyw/list.htm").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            Elements elements=doc.select("div#tablelsw").first().getElementsByTag("table").first().getElementsByTag("tbody").first().getElementsByTag("tr");

            Iterator iterator=elements.iterator();
            while (iterator.hasNext()){
                Element element= (Element) iterator.next();

                String title=element.getElementsByTag("td").first().getElementsByTag("a").first().text();
                String url="http://finance.qq.com/"+element.getElementsByTag("td").first().getElementsByTag("a").first().attr("href");
                String time=sdf.format(new Date()).substring(0,4)+"-"+element.getElementsByTag("td").last().text().substring(1,6);
//                if(time.compareTo(this.lastTime("510050","新闻"))<0){
                if(time.compareTo("2017-07-00")<0){
                    continue;
                }

                News news=new News();
                news.setTitle(title);
                news.setTop(kewordCheck(title));
                news.setUrl(url);
                news.setCode("上证50ETF510050");
                news.setType("新闻");
                news.setReadNum(0);
                news.setDate(sdf.parse(time));
                list.add(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it=list.iterator();
        while (it.hasNext()){
            News news= (News) it.next();
            StringBuilder text=new StringBuilder();
            try {
                Document doc1 = Jsoup.connect(news.getUrl()).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
                Elements element1=doc1.select("div#Cnt-Main-Article-QQ").first().getElementsByTag("p");
                Iterator iterator=element1.iterator();
                while (iterator.hasNext()){
                    Element e=(Element)iterator.next();
                    text.append(e.text()+"\r\n");
                }
                news.setText(text.toString().replace("图片来源：中国网财经",""));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(news.getUrl());
                it.remove();
                continue;
            }
        }
        return list;


    }


    private static boolean kewordCheck(String text){
        String [] keyword={
                "停牌",
                "复牌",
                "重组",
                "合并",
                "破产"
        };
        for (int i = 0; i <keyword.length; i++) {
            if(text.indexOf(keyword[i])>0){
                return true;
            }

        }
        return false;
    }


    private String lastTime(String code, String type){
        Page p=newsRepository.findByCodeAndType(CrawlerUtil.stockCode.get(code)+code,type,new PageRequest(0,1,new Sort(Sort.Direction.DESC,"date")));
        return ((News)p.getContent().get(0)).getDateToString();
    }



    public static String getUTF8BytesFromGBKString(String gbkStr) throws UnsupportedEncodingException {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128 && m >= 0) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            utfBytes = tmp;

        }
        return new String(utfBytes,"UTF-8");
    }

    /**
     * 定时任务
     */
    @Scheduled(cron="0 0/30 1-2 * * ?")
    private void update(){
        for (String key:CrawlerUtil.stockCode.keySet()){

            if (key.equals("510050")){
                newsRepository.save(getROfEFromHexun());
                newsRepository.save(getNofEFromTencent());

            }else {
                newsRepository.save(getRFromSina(key));
                newsRepository.save(getAFromSina(key));
                newsRepository.save(getNFromSina(key));
                newsRepository.save(getNFromIfeng(key));

            }


        }
    }

    @Scheduled(cron="0 0/1 9-15 * * ?")
    public void deal() {
        tradeService.dealAllTrade();
    }




}
