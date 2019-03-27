<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"  + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>报表搜素页面</title>
    <script type="text/javascript" src="<%=basePath%>report/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        function search() {
            var ip=$("#ip").val();
            var instance_name=$("#instance_name").val();
            if(ip==undefined){
                ip='';
            }
            if(instance_name==undefined){
                instance_name='';
            }
            url = "<%=basePath%>report/search?ip="+ip+"&instanceName="+instance_name;

            $.get(url,function (data) {

                data = JSON.parse(data);

                $("#report").html(data.content)
            });
        }
    </script>
</head>
<body onload="search();">

    ip:<input type="text" id="ip" name="ip"/>&nbsp;&nbsp;
    <%--实例名:<input type="text" id="instance_name" name="instance_name"/>&nbsp;&nbsp;--%>
    <button onclick="search()">搜素</button>
    <div id="report">

    </div>
</body>
</html>
