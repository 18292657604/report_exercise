package com.skycloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.skycloud.entity.JavaBeanPerson;
import com.skycloud.service.ExportService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author angel
 * @date 2019/3/22
 */
@Controller
@RequestMapping("/home")
public class ReportController {


    @RequestMapping(value = "showReport", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject showReport(@RequestParam("name") String name){
        JSONObject resultJson = new JSONObject();

        Map<String, Object> paramJson = new HashMap<String, Object>();

        paramJson.put("name", name);

        try {
            ExportService.saveReport(paramJson);

            resultJson.put("message", "导出成功");
        } catch (Exception e) {
            resultJson.put("message", "导出失败");
            e.printStackTrace();
        }

        return resultJson;
    }

    /**
     * jdbc作为数据源
     * @param model
     * @param format
     * @return
     */
    @RequestMapping(value = "showJdbc/{format}", method = RequestMethod.GET)
    public String showJdbc(Model model,@PathVariable("format") String format){
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(null);

        // 动态指定报表模板url
        model.addAttribute("url", "/WEB-INF/spring_report.jasper");
        model.addAttribute("format", format); // 报表格式
        model.addAttribute("jrMainDataSource", jrDataSource);

        return "reportView"; // 对应jasper-views.xml中的bean id
    }


    /**
     * javabean 作为数据源
     * @param model
     * @param format
     * @param name
     * @return
     */
    @RequestMapping(value = "show/{format}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable("format") String format, @RequestParam("name") String name) {

        //获取数据集合
        List<JavaBeanPerson> beanPersonList = JavaBeanPerson.getList();

        //封装结果的集合
        List<JavaBeanPerson> resultList = new ArrayList<>();
        if("".equals(name)||name==null){
            resultList = beanPersonList;
        }else{
            for (JavaBeanPerson person:beanPersonList) {
                if(name.equals(person.getName())){
                    resultList.add(person);
                }
            }
        }
        if(resultList.isEmpty()){
            resultList.add(new JavaBeanPerson());
        }

        // 报表数据源
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(resultList);

        // 动态指定报表模板url
        model.addAttribute("url", "/WEB-INF/spring_report.jasper");
        model.addAttribute("format", format); // 报表格式
        model.addAttribute("jrMainDataSource", jrDataSource);

        return "reportView"; // 对应jasper-views.xml中的bean id
    }

}
