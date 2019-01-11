<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC 表单操作</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<!--  
		1. 使用 form 标签可以更快速的开发出表单页面, 而且可以更方便的进行表单值的回显。
		step1 导入标签 taglib prefix="form" uri="http://www.springframework.org/tags/form" 
		step2 和普通的form用法差不多。path 相当于 普通的form的name，form:hidden 隐藏域，form:errors 提示错误信息。
		2. 使用form 标签需要注意:
		通过 modelAttribute 属性指定绑定的模型属性, 该数据模型必须是实例化过的。
		若没有 modelAttribute 指定该属性，则默认从 request 域对象中读取 command 的表单 bean (如果该属性值也不存在，则会发生错误)。
		java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'command' available as request attribute
	-->
	<div class="container">
	   <div class="row">
	      <div class="col-sm-6">
			<div class="panel panel-info" style="margin-top:10px;">
			    <div class="panel-heading">
			        <h3 class="panel-title">修改或创建用户信息</h3>
			    </div>
			    <div class="panel-body">
					<form:form action="${pageContext.request.contextPath }/user" method="POST" 
						modelAttribute="user" class="form-horizontal" role="form">
						<c:if test="${user.id == null }">
							<!-- path 属性对应 html 表单标签的 name 属性值 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">Account</label>
								<div class="col-sm-10">
									<form:input class="form-control" path="account"/>
									<form:errors style="color:red" path="account"></form:errors>
								</div>
							</div>
						</c:if>
						<c:if test="${user.id != null }">
							<form:hidden path="id"/>
							<input type="hidden" name="_method" value="PUT"/>
							<%-- 对于 _method 不能使用 form:hidden 标签, 因为 modelAttribute 对应的 bean 中没有 _method 这个属性 --%>
							<%-- 
							<form:hidden path="_method" value="PUT"/>
							--%>
						</c:if>
						<div class="form-group">
							<label class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<form:input class="form-control" path="email"/>
								<form:errors style="color:red" path="email"></form:errors>
							</div>
						</div>
						<!-- 这是SpringMVC 不足之处 -->
						<% 
							Map<String, String> genders = new HashMap();
							genders.put("1", "Male");
							genders.put("0", "Female");
							request.setAttribute("genders", genders);
						%>
						<div class="form-group">
							<label class="col-sm-2 control-label">Sex</label>
							<div class="col-sm-10">
								<form:radiobuttons path="sex" items="${genders }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Position</label>
							<div class="col-sm-10">
								<form:select class="form-control" path="position.id" items="${positions}" itemLabel="level" itemValue="id">
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Date</label>
							<div class="col-sm-10">
								<form:input class="form-control" path="createdDate"/>
								<form:errors style="color:red" path="createdDate"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Salary</label>
							<div class="col-sm-10">
								<form:input class="form-control" path="salary"/>
								<form:errors style="color:red" path="salary"></form:errors>
							</div>
						</div>
						<input class="btn btn-success" type="submit" value="Submit"/>
					</form:form>
			    </div>
			</div>
	      </div>
	   </div>
	</div>
	
</body>
</html>