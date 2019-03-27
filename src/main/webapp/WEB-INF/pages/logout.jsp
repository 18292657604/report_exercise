<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        function search() {
        }
    </script>
</head>
<body>
<%
//    File reportFile = new File("E:/self_project/report_exercise/src/main/webapp/WEB-INF/report/report8.jasper");
    String reaportPath = "E:/self_project/report_exercise/src/main/webapp/WEB-INF/report/report8.jasper";
    Map parameters = new HashMap();
    parameters.put("ip", "8.1.11.69");
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unified_portal","root","root");
    JasperRunManager.runReportToHtmlFile(reaportPath,parameters, conn);
//    response.sendRedirect("report8.html");
%>
ip:<input type="text" id="ip" name="ip">&nbsp;&nbsp;
<button onclick="search()">搜素</button>
<br/>
<br/>
<iframe id="hello" src="../report/report8.html" style="width: 80%;height: 352px;">
    不支持iframe
</iframe>
</body>
</html>