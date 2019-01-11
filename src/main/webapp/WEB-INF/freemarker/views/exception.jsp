<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC 快速入门</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	
	<div class="container">
	   <div class="row">
	   		<h2>SpringMVC 异常处理</h2>
			<hr/>
			@ExceptionHandler : <a href="testExceptionHandler?num=0" class="btn" >testExceptionHandler?num=0</a>
			<hr/>
			SimpleMappingExceptionResolver : <a href="simpleMappingExceptionResolver?num=20" class="btn" >simpleMappingExceptionResolver?num=20</a>
			<hr/>
			@ResponseStatus : <a href="testResponseStatus?num=0" class="btn" >testResponseStatus?num=0</a>
			<hr/>
			${exception}
	   </div>
	</div>
</body>
</html>