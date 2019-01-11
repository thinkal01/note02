<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SpringMVC 快速入门</title>
</head>
<body>

<h2>@RequestMapping 注解基本用法</h2>
<a href="helloworld">史上最丑的HelloWorld</a>
<br/><br/>
<a href="apiStudy/testMapResult">Map,Model,ModelMap的使用方法</a>
<br/><br/>
<a href="apiStudy/testRequestMethod">用GET请求方式测试POST方法</a>
<form action="apiStudy/testRequestMethod" method="POST">
    <input type="submit" value="用POST请求方式测试POST方法"/>
</form>
<br/>
<a href="apiStudy/testPathVariable/itdragon">@PathVariable获取占位数据</a>
<br/><br/>
<a href="apiStudy/testRequestParam?account=itdragon&password=123456">@RequestParam获取请求参数值</a>
<br/><br/>
<form action="apiStudy/testPojo" method="post">
    account: <input type="text" name="account" value="itdragon"/> <br>
    level: <input type="text" name="position.level" value="架构师"/> <br>
    salary: <input type="text" name="position.salary" value="88888.88"/> <br>
    <input type="submit" value="测试方法参数是POJO"/>
</form>
<br/>
<hr/>
<a href="apiStudy/testParamsAndHeaders?itdragon=great">params 和 headers用法</a>
<br/><br/>
<a href="apiStudy/itdragon/testAntUrl">Ant风格URL请求</a>
<br/><br/>
<a href="apiStudy/testRequestHeader">@RequestHeader 注解获取请求头数据</a>
<br/><br/>
<a href="apiStudy/testCookieValue">@CookieValue 注解获取 Cookie值</a>
<br/>
<br/>
<hr/>
<hr/>
<h2>@RequestMapping 注解提升用法</h2>
<a href="apiStudy/testModelAndView">ModelAndView的使用方法</a>
<br/><br/>
<form action="apiStudy/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1"/><!-- 隐藏域 id -->
    account: <input type="text" name="account" value="itdragon"/> <br>
    <input type="submit" value="testModelAttribute"/>
</form>
<br/>
<a href="apiStudy/testServletAPI">使用原生的Servlet API</a>
<br/><br/>
</body>
</html>