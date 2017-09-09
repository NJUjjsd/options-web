package com.jjsd.options.util;

<<<<<<< HEAD
=======
import com.jjsd.options.entity.ETFStoreBasic;
import com.jjsd.options.entity.market.ETFStoreInfo;
>>>>>>> origin/master


import com.jjsd.options.entity.market.ETFBaseInfo;
import com.jjsd.options.entity.market.ETFStoreBasic;
import com.jjsd.options.entity.market.ETFStoreInfo;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/9/6.
 */
public class HistoryDataUtil extends ETFInfoUtil {
    public static void main(String[] args) {
        storeBasicEtf();
        //storeTodayEtf();
    }

    /**
     * 存储今日的etf期权信息
     */
    public static ETFStoreInfo getTodayEtf(String id) {
        String info1Str = queryUrl(nowInfoUrl + id);
        ArrayList<String> info1 = dealListInfo(info1Str);
        String info2Str = queryUrl(FluctuationUrl + id);
        ArrayList<String> info2 = ETFInfoUtil.dealListInfo(info2Str);
        double price = Double.valueOf(info1.get(8));
        String dateTime = info1.get(32).substring(0, 10);
        String code = info2.get(12);
        ETFStoreInfo storeInfo = new ETFStoreInfo(dateTime, code, price);
        return storeInfo;
    }

    private static void storeInSql(ETFStoreInfo info) {
        SqlConnectUtil.getSqlConnect();
        String insertLine = "insert into etfHistory values(" + "'" + info.getDate() + "'" + "," + "'" + info.getCode() + "'" + "," + info.getPrice() + ")";
        System.out.println(insertLine);
        Connection connection = SqlConnectUtil.getSqlConnect();
        Statement statement = null;
        ResultSet set = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertLine);
            SqlConnectUtil.close(connection, statement, set);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void storeBasicInSql(ETFStoreBasic storeBasic) {
        SqlConnectUtil.getSqlConnect();
        String insertLine = "insert into ETFBasic values(" + "'" + storeBasic.getId() + "'" + "," + "'" + storeBasic.getTradeCode() + "'" + "," + "'" + storeBasic.getName() + "'" + "," + "'" + storeBasic.getDate() + "'" + ")";
        System.out.println(insertLine);
        Connection connection = SqlConnectUtil.getSqlConnect();
        Statement statement = null;
        ResultSet set = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertLine);
            SqlConnectUtil.close(connection, statement, set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void storeTodayEtf() {
        ArrayList<String> months = getAllMonths();
        for (String month : months) {
            month = monthTrans(month);
            String upListStr = queryUrl(upListUrl + month);
            String downListStr = queryUrl(downListUrl + month);
            ArrayList<String> upList = dealListInfo(upListStr);
            ArrayList<String> downList = dealListInfo(downListStr);
            for (String id : upList) {
                id = id.substring(7);
                ETFStoreInfo info = getTodayEtf(id);
                storeInSql(info);
            }
            for (String id : downList) {
                id = id.substring(7);
                ETFStoreInfo info = getTodayEtf(id);
                storeInSql(info);
            }
        }

    }

    private static ETFStoreBasic getEtfBasic(String id) {
        String info1Str = queryUrl(nowInfoUrl + id);
        ArrayList<String> info1 = dealListInfo(info1Str);
        String info2Str = queryUrl(FluctuationUrl + id);
        ArrayList<String> info2 = ETFInfoUtil.dealListInfo(info2Str);
        double price = Double.valueOf(info1.get(8));
        String name = info2.get(0);
        String TradeCode = null;
        try {

            TradeCode = new String(info2.get(12).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ETFStoreBasic storeBasic = new ETFStoreBasic(id, TradeCode, name);
        return storeBasic;
    }

    public static void storeBasicEtf() {
        String[] dates = {"2017-09-27", "2017-10-25", "2017-12-27", "2018-03-28"};
        int i = 0;
        ArrayList<String> months = getAllMonths();
        String date = null;
        for (String month : months) {
            date = dates[i];
            month = monthTrans(month);
            String upListStr = queryUrl(upListUrl + month);
            String downListStr = queryUrl(downListUrl + month);
            ArrayList<String> upList = dealListInfo(upListStr);
            ArrayList<String> downList = dealListInfo(downListStr);
            for (String id : upList) {

                id = id.substring(7);
                ETFStoreBasic info = getEtfBasic(id);
                info.setDate(date);
                storeBasicInSql(info);
            }
            for (String id : downList) {
                id = id.substring(7);
                ETFStoreBasic info = getEtfBasic(id);
                info.setDate(date);
                storeBasicInSql(info);
            }
            i++;
        }
    }


}
