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
