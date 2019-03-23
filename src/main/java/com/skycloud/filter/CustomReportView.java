package com.skycloud.filter;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import java.util.Map;

/**
 * 重写Jasper过滤器
 * @author angel
 * @date 2019/3/22
 */
public class CustomReportView extends JasperReportsMultiFormatView {

    private JasperReport report;

    public CustomReportView() {
        super();
    }

    @Override
    protected JasperPrint fillReport(Map<String, Object> model) throws Exception {
        if (model.containsKey("url")) {
            setUrl(String.valueOf(model.get("url")));
            this.report = loadReport();
        }

        return super.fillReport(model);
    }

    @Override
    protected JasperReport getReport() {
        return this.report;
    }

}
