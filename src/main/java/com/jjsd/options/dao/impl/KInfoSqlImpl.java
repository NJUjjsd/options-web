package com.jjsd.options.dao.impl;

import com.jjsd.options.dao.KInfoDao;
import com.jjsd.options.entity.KInfoSimple;
import com.jjsd.options.util.SqlConnectHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ${zrz} on 2017/8/16.
 */
public class KInfoSqlImpl implements KInfoDao{


    static Connection connection=null;//数据库连接
    static PreparedStatement statement=null;//句柄
    ResultSet resultSet = null;//存储结果
    static {
        connection = SqlConnectHelper.getSqlConnect();
    }

    private void freeConnect(){       //释放连接
        try {
            SqlConnectHelper.close(connection, statement, resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 将单表的全部数据填充到List中
     * @param tableName 表名
     * @return
     */
    private ArrayList<KInfoSimple> fillList(String tableName){
        ArrayList<KInfoSimple> resultList = new ArrayList<>();
        try {

            connection = SqlConnectHelper.getSqlConnect();
            String selectSql = "select kdate,openPrice,closePrice,highPrice,lowPrice,turnOver from " + tableName + " order by kdate ";
            statement = connection.prepareStatement(selectSql);
            resultSet=statement.executeQuery();
            System.out.println(selectSql);
            while (resultSet.next()){
                String date = resultSet.getString(1);
                double openPrice = resultSet.getDouble(2);
                double closePrcie = resultSet.getDouble(3);
                double highPrice = resultSet.getDouble(4);
                double lowPrice = resultSet.getDouble(5);
                double turnOver = resultSet.getDouble(6)/10000;
                KInfoSimple tempInfo = new KInfoSimple(date,openPrice,closePrcie,highPrice,lowPrice,turnOver);
                resultList.add(tempInfo);
            }
        }catch (SQLException e){

            e.printStackTrace();
        }finally {
            freeConnect();
        }

        return resultList;

    }
    @Override
    public ArrayList<KInfoSimple> getWeekK() {
        String tableName = "weekKinfo";
        return fillList(tableName);
    }

    @Override
    public ArrayList<KInfoSimple> getMonthK() {
        String tableName = "monthKinfo";
        return fillList(tableName);
    }

    @Override
    public ArrayList<KInfoSimple> getDayK() {
        String tableName = "dayKinfo";
        return fillList(tableName);
    }
}
