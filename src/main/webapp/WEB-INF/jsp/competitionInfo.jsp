<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li><a href="${ctx}/student/message">消息 <span class="badge">${sessionScope.msg}</span></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.name}同学<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${ctx}/student/index">个人中心</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${ctx}/logout">注销登录</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h1>科技改变世界</h1>
    </div>
    <div class="row">
        <h2>${competition.title}</h2>
        <p>${competition.content}</p>
        <p>人数：${competition.studentsNum}</p>
        <p>开始时间：<fmt:formatDate value="${competition.startTime}" pattern="yyyy-MM-dd"/></p>
        <p>结束时间：<fmt:formatDate value="${competition.endTime}" pattern="yyyy-MM-dd"/></p>
        <p>组织者：${competition.organizerName}</p>
        <p>参考资料：
            <c:forEach items="${competition.referenceData.split(',')}" var="item">
                <a href="${ctx}/competition/downloadData?id=${item}">${item}</a>
            </c:forEach>
        </p>
    </div>
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