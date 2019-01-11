<%@ page language="java" import="java.util.Locale" pageEncoding="UTF-8" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
<%-- 把与语言相关的所有字符串都写成变量--%>
<%
    /*
    1. 获取Locale，这是由客户端的浏览器提供的Locale
    2. 创建ResourceBundle
    3. 把所有的语言信息使用rb.getString("xxx")来替换！
    */
    Locale locale = request.getLocale();
    ResourceBundle rb = ResourceBundle.getBundle("res", locale);
%>
<h1><%=rb.getString("login") %>
</h1>
<form action="" method="post">
    <%=rb.getString("username") %>：<input type="text" name="username"/><br/>
    <%=rb.getString("password") %>：<input type="password" name="password"/><br/>
    <input type="submit" value="<%=rb.getString("login") %>"/>
</form>
</body>
</html>