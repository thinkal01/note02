<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC 快速入门</title>
</head>
<body>

	<h2>@RequestMapping 注解基本用法</h2>
	request 作用域 : <br/>
	${requestScope.result} <br/>
	${requestScope.model} <br/>
	${requestScope.modelMap} <br/>
	
	<hr/>
	
	session 作用域 : <br/>
	${sessionScope.result}
</body>
</html>