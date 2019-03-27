package com.skycloud.controller;

import com.alibaba.fastjson.JSONObject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lsy
 * @date 2019/3/22
 */
@Controller
@RequestMapping("/report")
public class HomeController {

    @RequestMapping("index")
    @ResponseBody
    public String homePage(){
        return "This  is home page";
    }

    @RequestMapping("login")
    public String indexPage(){
        return "successful";
    }

    @RequestMapping("logout")
    public ModelAndView logout(){
        ModelMap map = new ModelMap();
        map.put("id", 1);
        map.put("name", "hello world");
        return new ModelAndView("logout", map);
    }

    @RequestMapping("enterView")
    public String enterView(){
        return "report";
    }

    @RequestMapping("search")
    @ResponseBody
    public JSONObject search(HttpServletRequest request, @RequestParam("ip") String ip, @RequestParam("instanceName") String instanceName) throws ClassNotFoundException, JRException, SQLException {
        Connection connection = null;
        try {
            Map parameters = new HashMap();
            parameters.put("ip", ip);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unified_portal","root","root");
            JRDataSource dataSource =null;
            String reportPath = "E:/self_project/report_exercise/src/main/webapp/WEB-INF/report/report8.jasper";
            File reportFile = new File(reportPath);
            StringBuffer reportContent = new StringBuffer();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
            JasperPrint jasperPrint = null;

            if (connection != null) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            } else {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            }
            request.getSession().setAttribute("jasperPrint", jasperPrint);
            JRHtmlExporter exporter = new JRHtmlExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, reportContent);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.TRUE);

            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "utf-8");
            exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
            exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
            exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
            exporter.exportReport();

            JSONObject resultJson = new JSONObject();
            resultJson.put("content", reportContent);
            return resultJson;
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("释放数据库连接");
        }
    }


}
