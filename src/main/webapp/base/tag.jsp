<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="it" uri="/WEB-INF/tlds/itcast-tag.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
<it:myTag5 test="${empty param.xxx }">
    <h1><it:myTag4/></h1>
</it:myTag5>

<h1><it:myTag1/></h1>
<h2><it:myTag2/></h2>
<hr/>
<%
    request.setAttribute("xxx", "zhangSan");
%>
<h3>
    <it:myTag3>${xxx }</it:myTag3>
</h3>
<h3>
    <it:myTag3>我是张三的大哥</it:myTag3>
</h3>
</body>
</html>