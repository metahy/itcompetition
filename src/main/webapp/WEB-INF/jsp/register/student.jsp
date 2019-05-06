<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">IT科技节比赛报名系统</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.id == null}">
                    <li><a href="${ctx}/login/student">登录</a></li>
                    <li><a href="${ctx}/register/student">注册</a></li>
                </c:if>
                <c:if test="${sessionScope.id != null}">
                    <li><a href="#">消息 <span class="badge">42</span></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.studentName}同学<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">个人中心</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">注销登录</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <form action="${ctx}/register/student" method="post">
        <div class="form-group">
            <label for="studentNum">学号</label>
            <input type="text" class="form-control" name="studentNum" id="studentNum" placeholder="2019010101">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="studentName">姓名</label>
            <input type="text" class="form-control" name="studentName" id="studentName" placeholder="张三">
        </div>
        <div class="form-group">
            <label for="boy">性别</label>
            <label class="radio-inline">
                <input type="radio" name="gender" id="boy" value="1"> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="gender" id="girl" value="2"> 女
            </label>
            <label class="radio-inline">
                <input type="radio" name="gender" id="none" value="3"> 保密
            </label>
        </div>
        <div class="form-group">
            <label for="birth">生日</label>
            <input type="text" class="form-control" name="birth" id="birth" placeholder="2019-04-29">
        </div>
        <div class="form-group">
            <label for="phone">电话</label>
            <input type="text" class="form-control" name="phone" id="phone" placeholder="13812341234">
        </div>
        <div class="form-group">
            <label for="college">学院</label>
            <input type="text" class="form-control" name="college" id="college" placeholder="理学院">
        </div>
        <button type="submit" class="btn btn-default">注册</button>
    </form>
</div>
<div style="text-align: center;">
    <span style="margin: auto;"><a href="${ctx}/login/organizer">比赛组织者登录</a> <a href="${ctx}/login/admin">管理人员登录</a></span>
</div>
<script src="${ctx}/resource/js/jquery-3.2.1.min.js"></script>
<script src="${ctx}/resource/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script>
    $(function () {
    })
</script>
</body>
</html>