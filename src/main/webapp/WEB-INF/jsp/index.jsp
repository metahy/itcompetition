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
        <%--<p>...</p>--%>
        <%--<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>--%>
    </div>
    <div class="row">
        <c:forEach items="${competitions}" var="competition">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <div class="caption">
                        <h3>${competition.title}(${competition.studentsNum}人)</h3>
                        <p>${competition.content}</p>
                        <p><a href="${ctx}/competition/info?id=${competition.id}" class="btn btn-primary" role="button">详情</a> <a href="${ctx}/competition/apply?id=${competition.id}" class="btn btn-default" role="button">报名</a></p>
                    </div>
                </div>
            </div>
        </c:forEach>
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