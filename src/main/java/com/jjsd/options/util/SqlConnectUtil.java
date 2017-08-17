package com.jjsd.options.util;

import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
/**
 * Created by ${zrz} on 2017/8/16.
 */
public final class SqlConnectUtil {
    static String url = "jdbc:mysql://118.89.193.72:3306/futures";
    static String user = "jjsd";
    static String password = "jjsd";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    public static Connection getSqlConnect(){
        return getConnection(url,user,password);

    }
    public static Connection getConnection(String url,String user,String password){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return connection;
    }
    public static void close(Connection connection,Statement statement,ResultSet resultSet)
            throws SQLException{
        if(null!=resultSet){
            resultSet.close();
        }
        if(null!=connection){
            connection.close();
        }
        if(null!=statement){
            statement.close();
        }
    }

}