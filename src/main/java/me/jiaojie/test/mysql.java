/*
 * Copyright (C) 2016 SINA Corporation
 *  
 *  
 * 
 * This script is firstly created at 2016-01-29.
 * 
 * To see more infomation,
 *    visit our official website http://finance.sina.com.cn/.
 */
package me.jiaojie.test;

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class mysql {

    public static final String url = "jdbc:mysql://172.16.6.243:3333/duizhang";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "jiaojie";
    public static final String password = "654321";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public mysql() {
        try {
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
//            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeSql(String sql) {
        try {
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            pst = null;
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
