package com.jjsd.options.util;

import com.jjsd.options.entity.market.ETFStoreInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/9/6.
 */
public class HistoryDataUtil extends ETFInfoUtil{
    public static void main(String[] args) {
        storeTodayEtf();
    }
    /**
     * 存储今日的etf期权信息
     */
    public static ETFStoreInfo getTodayEtf(String id){
        String info1Str = queryUrl(nowInfoUrl+id);
        ArrayList<String> info1 = dealListInfo(info1Str);
        String info2Str = queryUrl(FluctuationUrl+id);
        ArrayList<String> info2 = ETFInfoUtil.dealListInfo(info2Str);
        double price = Double.valueOf(info1.get(8));
        String dateTime = info1.get(32).substring(0,10);
        String code = info2.get(12);
        ETFStoreInfo storeInfo = new ETFStoreInfo(dateTime,code,price);
        return storeInfo;
    }
    private static void storeInSql(ETFStoreInfo info){
        SqlConnectUtil.getSqlConnect();
        String insertLine = "insert into etfHistory values("+"'"+info.getDate()+"'"+","+"'"+info.getCode()+"'"+","+info.getPrice()+")";
        System.out.println(insertLine);
        Connection connection = SqlConnectUtil.getSqlConnect();
        Statement statement=null;
        ResultSet set=null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertLine);
            SqlConnectUtil.close(connection,statement,set);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public static void storeTodayEtf(){
        ArrayList<String> months = getAllMonths();
        for(String month:months){
            month = monthTrans(month);
            String upListStr = queryUrl(upListUrl+month);
            String downListStr= queryUrl(downListUrl+month);
            ArrayList<String> upList = dealListInfo(upListStr);
            ArrayList<String> downList = dealListInfo(downListStr);
            for(String id:upList){
                id = id.substring(7);
                ETFStoreInfo info = getTodayEtf(id);
                storeInSql(info);
            }
            for(String id:downList){
                id = id.substring(7);
                ETFStoreInfo info = getTodayEtf(id);
                storeInSql(info);
            }
        }
    }


}
