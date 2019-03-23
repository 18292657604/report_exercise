package test;

import com.skycloud.entity.JavaBeanPerson;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author angel
 * @date 2019/3/22
 */
public class ReportTest {
    
    public final static String xmlPath = "E:/self_project/report_exercise/src/main/webapp/WEB-INF/spring_report.jasper";
    public final static String printPath = "E:/self_project/report_exercise/src/main/webapp/WEB-INF/spring_report.jrprint";

    public static void main(String[] args) throws JRException {
        //将jrxml生成jasper
        //JasperCompileManager.compileReportToFile(xmlPath);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "刘备");
        map.put("sex", "男");
        map.put("age", 35);
        map.put("hometown", "陕西西安");
        map.put("phone", "13571416592");

        JasperFillManager.fillReportToFile(xmlPath, map);

        /* 预览报表文件.jrprint */
        File sourceFile = new File(printPath);
        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile); //生成报表结果
        JasperViewer.viewReport(jasperPrint);      //预览报表

    }
}
