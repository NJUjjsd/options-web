package com.jjsd.options.util;

import net.sf.json.JSONObject;
import org.apache.xpath.SourceTree;
import sun.applet.Main;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${zrz} on 2017/9/8.
 */
public class BasicInfoUtil {
    public static void main(String[] args) {
        System.out.println(JSONObject.fromObject(getAllCode()));
        System.out.println(11&1);

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

    /**
     * 得到所有的id和名字信息
     * @return
     */
    public static Map<String,String> getAllCode(){
        ResultSet set = null;
        Connection connection = SqlConnectUtil.getSqlConnect();
        PreparedStatement statement = null;
        String sql = "select tradeCode,EName from ETFBasic";
        Map<String,String> result = new HashMap<>();
        try {
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            while(set.next()){
                result.put(set.getString(1),set.getString(2));
            }
            SqlConnectUtil.close(connection,statement,set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }


}
