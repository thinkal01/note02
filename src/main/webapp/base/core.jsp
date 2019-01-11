<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="code" value="<script>alert('hello');</script>" scope="request"/>
<c:out value="${code }" escapeXml="true"/><br/>

<c:url value="/AServlet"/><br/>
${pageContext.request.contextPath }/AServlet

<a href="<c:url value='/base/index.jspx.jsp'/>">点击这里回到主页</a>
<br/>
<c:url value='/base/index.jspx.jsp'>
    <c:param name="name" value="张三"/>
</c:url>
<hr/>

<c:if test="${empty param.name }">
    您没有给出名为name的参数
</c:if>

<hr/>

<c:choose>
    <c:when test="${empty param.name }">
        您没有给出名为name的参数！
    </c:when>
    <c:otherwise>
        谁让你给出name参数的！${param.name }
    </c:otherwise>
</c:choose>
<br/>

<c:forEach var="i" begin="1" end="10" step="2">
    ${i }<br/>
</c:forEach>
<br/>

<%
    String[] strs = {"one", "two"};
    request.setAttribute("strs", strs);
%>
<c:forEach items="${strs }" var="str">
    ${str }<br/>
</c:forEach>

<hr/>

<%
    ArrayList<String> list = new ArrayList<>();
    list.add("一");
    list.add("二");
    list.add("三");
    pageContext.setAttribute("list", list);
%>
<c:forEach items="${list }" var="ele" varStatus="vs">
    ${vs.index} ${vs.count } ${vs.first } ${vs.last } ${vs.current }<br/>
</c:forEach>
</body>
</html>