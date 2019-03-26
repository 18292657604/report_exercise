package com.skycloud.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by angel on 2019/3/23.
 */

public class ExportService {
    private static String realPath = "C:/apache-tomcat-8.0.47/webapps/ROOT/report8.jasper";
    private static String destPath = "C:/apache-tomcat-8.0.47/webapps/ROOT/report8.html";
    private static String url = "jdbc:mysql://localhost:3306/unified_portal";

    /**
     * 生成报表
     * @return
     */
    public static void saveReport(Map<String, Object> paramMap) throws ClassNotFoundException, SQLException, JRException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, "root", "root");
        File destFile =new File(destPath);
        //生成HTML文件
        JasperRunManager.runReportToHtmlFile(realPath,destFile.getPath(), paramMap, connection);

        try {
            destFile.createNewFile();
        } catch (IOException e) {
            System.out.println("创建报表异常");
            e.printStackTrace();
        }
        System.out.println("=======html生成完毕========");

    }

    public static void main(String[] args) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("ip", "8.1.11.69");
        saveReport(paramMap);
    }

}
