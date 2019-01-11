<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/user/recieveUser.do" method="post">
    姓名：<input type="text" name="username" id="username">
    生日：<input type="text" name="birthday" id="birthday">
    性别：<input type="text" name="sex" id="sex">
    地址：<input type="text" name="address" id="address">
    <input type="submit" value="提交">
</form>


<hr size="12" color="blue"/>
<form action="${pageContext.request.contextPath }/user/recieveInt.do" method="post">
    ID：<input type="text" name="id" id="id">
    <input type="submit" value="提交">
</form>

<hr size="12" color="blue"/>
<form action="${pageContext.request.contextPath }/user/recieveStr.do" method="post">
    姓名：<input type="text" name="username" id="username">
    <input type="submit" value="提交">
</form>


<hr size="12" color="blue"/>
<form action="${pageContext.request.contextPath }/user/recieveArray.do" method="post">
    ID：<input type="checkbox" name="ids" value="1" id="ids">
    ID：<input type="checkbox" name="ids" value="2" id="ids">
    ID：<input type="checkbox" name="ids" value="3" id="ids">
    <input type="submit" value="提交">
</form>

<hr size="12" color="blue"/>
<form action="${pageContext.request.contextPath }/user/recieveUserCustom.do" method="post">
    姓名：<input type="text" name="user.username" id="username">
    生日：<input type="text" name="user.birthday" id="birthday">
    性别：<input type="text" name="user.sex" id="sex">
    地址：<input type="text" name="user.address" id="address">
    <input type="submit" value="提交">
</form>

<hr size="12" color="blue"/>
<form action="${pageContext.request.contextPath }/user/recieveList.do" method="post">
    姓名：<input type="text" name="userList[0].username" id="username">
    地址：<input type="text" name="userList[0].address" id="address">

    姓名：<input type="text" name="userList[1].username" id="username">
    地址：<input type="text" name="userList[1].address" id="address">
    <input type="submit" value="提交">
</form>

<hr size="12" color="blue"/>
<form action="${pageContext.request.contextPath }/user/recieveMap.do" method="post">
    姓名：<input type="text" name="maps['username']" id="username">
    地址：<input type="text" name="maps['address']" id="address">
    <input type="submit" value="提交">
</form>

</body>
</html>