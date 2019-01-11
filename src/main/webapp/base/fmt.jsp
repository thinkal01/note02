<%@ page language="java" import="java.util.Date" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
</head>

<body>
<%
    Date date = new Date();
    request.setAttribute("date", date);
%>
<fmt:formatDate value="${requestScope.date }" pattern="yyyy-MM-dd HH:mm:ss"/>

<%
    request.setAttribute("num1", 3.1415926);
%>
<fmt:formatNumber value="${requestScope.num1 }" pattern="0.000"/><br/>
<fmt:formatNumber value="${requestScope.num1 }" pattern="#.###"/>
</body>
</html>