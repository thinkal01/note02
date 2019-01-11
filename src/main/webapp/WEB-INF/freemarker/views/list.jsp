<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC 表单操作</title>
<!--  
	SpringMVC 处理静态资源:
	1. 为什么会有这样的问题:
	优雅的 REST 风格的资源URL 不希望带 .html 或 .do 等后缀
	若将 DispatcherServlet 请求映射配置为 /, 
	则 Spring MVC 将捕获 WEB 容器的所有请求, 包括静态资源的请求, SpringMVC 会将他们当成一个普通请求处理, 
	因找不到对应处理器将导致错误。
	2. 解决: 在 SpringMVC 的配置文件中配置 <mvc:default-servlet-handler/>
-->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var msg = confirm("确定要删除这条数据？");
			if (true == msg) {
				$(this).onclick();
			} else {
				return false;
			}
		});
	})
</script>
</head>
<body>

	<!-- 用于删除的form -->
	<form action="" method="POST" id="deleteForm">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	
	<div class="container">
	   <div class="row">
	      <div class="col-sm-9">
			<c:if test="${empty requestScope.users }">
				没有任何员工信息.
			</c:if>
			<c:if test="${!empty requestScope.users }">
				<div class="table-responsive">
				  <table class="table table-bordered">
				    <caption>用户信息表 <a href="user" class="btn btn-default" >Add Account</a></caption>
				    <thead>
				    	<tr>
							<th>用户编码</th>
							<th>账号名</th>
							<th>邮箱</th>
							<th>性别</th>
							<th>职位</th>
							<th>薪水</th>
							<th>时间</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
				    </thead>
				    <tbody>
				    	<c:forEach items="${requestScope.users }" var="user">
							<tr>
								<td>${user.id }</td>
								<td>${user.account }</td>
								<td>${user.email }</td>
								<td>${user.sex == 0 ? 'Female' : 'Male' }</td>
								<td>${user.position.level }</td>
								<td>${user.salary }</td>
								<td><fmt:formatDate value="${user.createdDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><a href="user/${user.id}">Edit</a></td>
								<td><a class="delete" href="delete/${user.id}">Delete</a></td>
							</tr>
						</c:forEach>
				    </tbody>
				  </table>
				</div>
			</c:if>
	      </div>
	   </div>
	</div>
	
</body>
</html>