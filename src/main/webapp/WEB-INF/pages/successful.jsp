<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,
              net.sf.jasperreports.engine.*,
              net.sf.jasperreports.engine.util.*,
              java.util.*,java.sql.*,
              net.sf.jasperreports.engine.export.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        function search() {
            alert("搜素成功");
        }
    </script>
</head>
<body>
ip:<input type="text" id="ip" name="ip"/>&nbsp;&nbsp;
实例名:<input type="text" id="ip" name="ip"/>&nbsp;&nbsp;
类型：<select id="type" name="type">
        <option>db2</option>
        <option>oracle</option>
        <option>IBP</option>
        <option>PCFWQ</option>
     </select>&nbsp;&nbsp;
<button onclick="search()">搜素</button>
<%
    System.out.println("===========");
    File reportFile = new File("C:/Users/angel/Desktop/report/report8.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
    Map parameters = new HashMap();
    parameters.put("ip", "");
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unified_portal","root","root");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
    JRHtmlExporter exporter = new JRHtmlExporter();
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
    exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
    exporter.exportReport();
    out.flush();
    conn.close();
%>
</body>
</html>
