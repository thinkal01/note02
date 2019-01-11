<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.note.old.web.MessageUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
<h1><%=MessageUtils.getText("msg.login") %>
</h1>
<form action="<c:url value='/base/index.jsp'/>" method="post">
    <%=MessageUtils.getText("msg.username")%>：<input type="text" name="username"/><br/>
    <%=MessageUtils.getText("msg.password")%>：<input type="password" name="password"/><br/>
    <input type="submit" value='<%=MessageUtils.getText("msg.login")%>'/>
</form>
</body>
</html>