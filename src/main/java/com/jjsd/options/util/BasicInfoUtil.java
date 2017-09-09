package com.jjsd.options.util;

import sun.applet.Main;

import javax.sound.midi.Soundbank;
import java.sql.*;

/**
 * Created by ${zrz} on 2017/9/8.
 */
public class BasicInfoUtil {
    public static void main(String[] args) {
        System.out.println(getEndDate("10001010"));
    }

    /**
     * 根据id查找交易代码
     * @param id
     * @return
     */
    public static String getTradeCode(String id){
        ResultSet set = null;
        Connection connection = SqlConnectUtil.getSqlConnect();
        PreparedStatement statement = null;
        String sql = "select tradeCode from ETFBasic where id ="+"'"+id+"'";
        String result = null;
        try {
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            set.next();
            result = set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 根据id查找简称
     * @param id
     * @return
     */
    public static String getName(String id){
        ResultSet set = null;
        Connection connection = SqlConnectUtil.getSqlConnect();
        PreparedStatement statement = null;
        String sql = "select EName from ETFBasic where id ="+"'"+id+"'";
        String result = null;
        try {
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            set.next();
            result = set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据交易代码查找id
     * @return
     */
    public static String getId(String tradeCode){
        ResultSet set = null;
        Connection connection = SqlConnectUtil.getSqlConnect();
        PreparedStatement statement = null;
        String sql = "select id from ETFBasic where tradeCode ="+"'"+tradeCode+"'";
        String result = null;
        try {
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            set.next();
            result = set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
    /**
     * 根据id得到结束日期，id为10开头，日期格式为yyyy-mm-dd
     * @param id
     * @return
     */
    public static String getEndDate(String id){
        ResultSet set = null;
        Connection connection = SqlConnectUtil.getSqlConnect();
        PreparedStatement statement = null;
        String sql = "select date from ETFBasic where id ="+"'"+id+"'";
        String result = null;
        try {
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            set.next();
            result = set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
