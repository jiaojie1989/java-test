/*
 * Copyright (C) 2016 SINA Corporation
 *  
 *  
 * 
 * This script is firstly created at 2016-01-28.
 * 
 * To see more infomation,
 *    visit our official website http://finance.sina.com.cn/.
 */
package me.jiaojie.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

import me.jiaojie.test.mysql;

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world !");
//        queryMysql();
        HashSet<String> set = readFile("/data/scripts/duizhang/201602_wx/test");
        doThings(set);
//        System.out.println(set);
        System.out.println("End !");
    }

    protected static void doThings(HashSet<String> set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = it.next().toString();
            String[] strArr = str.split(",");
//            System.out.println("OrderId : " + strArr[0]);
            HashMap<String, String> pay = queryMysql(strArr[0]);
            HashMap<String, String> sub = queryMysql4Subscribe(strArr[0]);
            pay.put("totalPayments", queryWxAll(strArr[0]));
            if (validateWhetherRefundOrder(pay)) {
                writeFile("/data/scripts/duizhang/201602_wx/wbpay_wxfinance_fill.txt", generateWxFill(pay, sub));
                writeFile("/data/scripts/duizhang/201602_wx/wbpay_wxfinance_strike.txt", generateWxStrike(pay, sub));
                writeFile("/data/scripts/duizhang/201601_wx/wbpay_finance_pay.txt", generateWxPay(pay, sub));
                pay.put("status", "-1");
                pay.put("totalPayments", "-" + pay.get("totalPayments"));
                writeFile("/data/scripts/duizhang/201602_wx/wbpay_wxfinance_fill.txt", generateWxFill(pay, sub));
            } else {
                if (null != pay.get("orderId")) {
                    writeFile("/data/scripts/duizhang/201602_wx/wbpay_wxfinance_fill.txt", generateWxFill(pay, sub));
                    writeFile("/data/scripts/duizhang/201602_wx/wbpay_wxfinance_order_wx.txt", generateWxOrder(pay, sub));
                    writeFile("/data/scripts/duizhang/201602_wx/wbpay_finance_pay.txt", generateWxPay(pay, sub));
                } else {
                    System.out.println(strArr[0]);
                }
            }
        }
//        System.out.println(writeStr);
    }

    protected static boolean validateWhetherRefundOrder(HashMap<String, String> pay) {
        HashSet<String> set = new HashSet<String>() {
            {
                add("00216022348201036306119");
                add("00016012985201247514022");
                add("000160112814476061406305");
                add("000160112814476468304334");
            }
        };
        if (set.contains(pay.get("orderId"))) {
            return true;
        } else {
            return false;
        }
    }

    protected static String generateWxFill(HashMap<String, String> pay, HashMap<String, String> sub) {
        String str = "";
        try {
            str += pay.get("orderId");
            str += "\t";
            str += pay.get("payId");
            str += "\t";
            str += pay.get("sid");
            str += "\t";
            str += pay.get("totalPayments");
            str += "\t";
            str += pay.get("payTime");
            str += "\t";
            str += "微信";
            str += "\t";
            str += pay.get("status");
            str += "\t";
            str += " ";
            str += "\t";
            str += " ";
            str += "\t";
            str += "直充";
            str += "\r\n";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    protected static String generateWxOrder(HashMap<String, String> pay, HashMap<String, String> sub) {
        String str = "";
        try {
            str += "理财师";
            str += "\t";
            str += "模拟交易";
            str += "\t";
            str += " ";
            str += "\t";
            str += pay.get("sid");
            str += "\t";
            str += " ";
            str += "\t";
            str += " ";
            str += "\t";
            str += pay.get("orderId");
            str += "\t";
            str += pay.get("cTime");
            str += "\t";
            str += pay.get("payId");
            str += "\t";
            str += pay.get("payTime");
            str += "\t";
            str += pay.get("totalPayments");
            str += "\t";
            str += sub.get("startDate");
            str += "\t";
            str += "SUCCESS";
            str += "\t";
            str += "否";
            str += "\t";
            str += "直充";
            str += "\t";
            str += sub.get("startDate");
            str += "\t";
            str += sub.get("endDate");
            str += "\r\n";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    protected static String generateWxStrike(HashMap<String, String> pay, HashMap<String, String> sub) {
        String str = "";
        try {
            str += "理财师";
            str += "\t";
            str += "模拟交易";
            str += "\t";
            str += " ";
            str += "\t";
            str += pay.get("sid");
            str += "\t";
            str += " ";
            str += "\t";
            str += " ";
            str += "\t";
            str += pay.get("orderId");
            str += "\t";
            str += pay.get("cTime");
            str += "\t";
            str += pay.get("payId");
            str += "\t";
            str += pay.get("payTime");
            str += "\t";
            str += pay.get("totalPayments");
            str += "\t";
            str += pay.get("payTime");
            str += "\t";
            str += pay.get("totalPayments");
            str += "\t";
            str += "SUCCESS";
            str += "\t";
            str += "否";
            str += "\t";
            str += "直充";
            str += "\t";
            str += sub.get("startDate");
            str += "\t";
            str += sub.get("endDate");
            str += "\r\n";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    protected static String generateWxPay(HashMap<String, String> pay, HashMap<String, String> sub) {
        String str = "";
        try {
            str += pay.get("orderId");
            str += "\t";
//            str += "2016-02";
            str += queryZhangDanMonth(pay.get("orderId"));
            str += "\t";
            str += "2016-02-26";
            str += "\r\n";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    protected static void writeFile(String fileName, String content) {
        try {
//            System.out.println("Start writing \"" + fileName + "\"");
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
//                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(fileName, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(content);
//            System.out.println(content);
            bufferWritter.close();
            fileWritter.close();
//            System.out.println("Completed writing \"" + fileName + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    protected static HashSet readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        HashSet ret = new HashSet();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
//            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                //System.out.println("line " + line + ": " + tempString);
                ret.add(tempString);
//                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return ret;
        }
    }

    protected static String queryWxAll(String orderId) {
        String sql = "select * from wx_all where orderId=\'" + orderId + "\'";
//        System.out.println(sql);
        mysql db = new mysql();
        String ret = "";
        try {
            ResultSet queryRet = db.executeSql(sql);
            while (queryRet.next()) {
                ret = queryRet.getString("dingdanMonth");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(ret);
            return ret;
        }
    }

    protected static String queryZhangDanMonth(String orderId) {
        String sql = "select * from wx_all where orderId=\'" + orderId + "\'";
//        System.out.println(sql);
        mysql db = new mysql();
        String ret = "";
        try {
            ResultSet queryRet = db.executeSql(sql);
            while (queryRet.next()) {
                ret = queryRet.getString("shujubao");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(ret);
            return ret;
        }
    }

    protected static HashMap<String, String> queryMysql(String orderId) {
        String sql = "select * from pay_order where order_id='" + orderId + "' and status=1 limit 1";
//        System.out.println(sql);
        mysql db = new mysql();
//        String str = "";
        HashMap map = new HashMap<String, String>();
        try {
            ResultSet ret = db.executeSql(sql);
            while (ret.next()) {
//                String sid = ret.getString("sid");
//                String status = ret.getString("status");
//                String payTime = ret.getString("pay_time");
//                String totalPayments = ret.getString("total_payments");
//                String refundStatus = ret.getString("refund_status");
//                str = sid + "\t" + status + "\t" + payTime + "\t" + totalPayments + "\t" + refundStatus + "\r\n";
//                System.out.println(str);
                map.put("sid", ret.getString("sid"));
                map.put("status", ret.getString("status"));
                map.put("payTime", ret.getString("pay_time"));
                map.put("totalPayments", ret.getString("total_payments"));
                map.put("refundStatus", ret.getString("refund_status"));
                map.put("payId", ret.getString("pay_id"));
                map.put("cTime", ret.getString("creat_time"));
                map.put("orderId", orderId);
            }
            ret.close();
        } catch (SQLException e) {
//            str = "\r\n";
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    protected static HashMap<String, String> queryMysql4Subscribe(String orderId) {
        String sql = "select * from subscribe_order where order_id='" + orderId + "' and status=1 limit 1";
//        System.out.println(sql);
        mysql db = new mysql();
        HashMap map = new HashMap<String, String>();
//        String str = "";
        try {
            ResultSet ret = db.executeSql(sql);
            while (ret.next()) {
//                String id = ret.getString("id");
//                String sid = ret.getString("sid");
//                String startDate = ret.getString("start_date");
//                String endDate = ret.getString("end_date");
//                String status = ret.getString("status");
//                String price = ret.getString("price");
//                str = id + "\t" + sid + "\t" + startDate + "\t" + endDate + "\t" + status + "\t" + price + "\t" + orderId + "\r\n";
//                System.out.println(str);
                map.put("id", ret.getString("id"));
                map.put("sid", ret.getString("sid"));
                map.put("startDate", ret.getString("start_date"));
                map.put("endDate", ret.getString("end_date"));
                map.put("status", ret.getString("status"));
                map.put("price", ret.getString("price"));
                map.put("orderId", orderId);
            }
            ret.close();
        } catch (SQLException e) {
//            str = "\r\n";
            e.printStackTrace();
        } finally {
            return map;
        }
    }
}
