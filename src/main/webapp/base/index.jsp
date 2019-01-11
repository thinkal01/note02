<%@ page pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
${cookie.JSESSIONID.value }
${header['User-Agent']}
<a href="${pageContext.request.contextPath }/el/a.jsp">点击这里</a>
</body>
</html>