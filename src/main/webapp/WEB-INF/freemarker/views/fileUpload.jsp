<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <div class="col-sm-6">
            <div class="panel panel-info" style="margin-top:10px;">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="file.upload"/></h3>
                </div>
                <div class="panel-body">
                    <!-- 缺少  enctype="multipart/form-data" 会提示
                        org.springframework.web.multipart.MultipartException: The current request is not a multipart request
                    -->
                    <form action="${pageContext.request.contextPath }/testFileUpload" method="POST"
                          enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><fmt:message key="file"/></label>
                            <div class="col-sm-10">
                                <input type="file" name="file" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><fmt:message key="desc"/></label>
                            <div class="col-sm-10">
                                <input type="text" name="desc" class="form-control"/>
                            </div>
                        </div>
                        <input type="submit" value="Submit" class="btn btn-success"/>
                    </form>
                    <a href="fileUpload?locale=zh_CN" class="btn">中文</a>
                    <a href="fileUpload?locale=en_US" class="btn">English</a>
                </div>
            </div>
        </div>
        <hr/>
        <a href="downLoadFile?fileName=${fileName}">${fileName}</a>
        <hr/>
    </div>
</div>
</body>
</html>