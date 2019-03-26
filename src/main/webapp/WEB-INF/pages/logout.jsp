<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="java.util.HashMap"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    File reportFile = new File("C:/Users/angel/Desktop/report/report8.jasper");
    Map parameters = new HashMap();
    parameters.put("ip", "");
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unified_portal","root","root");
    JasperRunManager.runReportToHtmlFile(reportFile.getPath(),
            parameters, conn);
    response.sendRedirect("report/report8.html");
%>
</body>
</html>