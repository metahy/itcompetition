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
    <h2>${competition.title}</h2>
    <form action="${ctx}/competition/apply" method="post">
        <input type="hidden" name="competitionId" value="${competition.id}">
        <input type="hidden" name="studentsNum" value="${competition.studentsNum}">
        <c:if test="${competition.studentsNum > 1}">
            <div class="form-group">
                <label for="teamName">队名</label>
                <input type="text" class="form-control" name="teamName" id="teamName">
            </div>
            <div class="form-group">
                <label for="teamLeaderNum">队长学号</label>
                <input type="text" class="form-control" name="teamLeaderNum" id="teamLeaderNum">
            </div>
            <c:forEach begin="1" end="${competition.studentsNum}" step="1" varStatus="index">
                <div class="form-group">
                    <label for="studentNum">队员${index.index}学号</label>
                    <input type="text" class="form-control" name="studentNum${index.index}" id="studentNum">
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${competition.studentsNum <= 1}">
            <div class="form-group">
                <label for="studentNum2">学号</label>
                <input type="text" class="form-control" name="studentNum1" id="studentNum2">
            </div>
        </c:if>
        <button type="submit" class="btn btn-default">报名</button>
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