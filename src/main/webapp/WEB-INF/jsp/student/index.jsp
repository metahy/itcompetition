<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/style.css">
    <style>
    @page { margin: 0; }
    </style>
</head>

<body>
<!-- Left Panel -->
<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">
        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="${ctx}/student/index"><i class="menu-icon fa fa-calendar"></i>比赛信息</a>
                </li>
                <li>
                    <a href="${ctx}/student/message"><i class="menu-icon fa fa-calendar"></i>我的消息</a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside>
<!-- /#left-panel -->
<!-- Right Panel -->
<div id="right-panel" class="right-panel">
    <!-- Header-->
    <header id="header" class="header">
        <div class="top-left">
            <div class="navbar-header">
                <a class="navbar-brand" href="./"><img src="${ctx}/resource/admin/images/logo.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="./"><img src="${ctx}/resource/admin/images/logo2.png"
                                                              alt="Logo"></a>
                <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
            </div>
        </div>
        <div class="top-right">
            <div class="header-menu">
                <div class="user-area dropdown float-right">
                    <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        <img class="user-avatar rounded-circle" src="${ctx}/resource/admin/images/admin.jpg"
                             alt="User Avatar">
                    </a>
                    <div class="user-menu dropdown-menu">
                        <a class="nav-link" href="${ctx}/logout"><i class="fa fa-power -off"></i>Logout</a>
                    </div>
                </div>

            </div>
        </div>
    </header>
    <!-- /#header -->
    <!-- Content -->
    <div class="content">
        <div class="animated fadeIn">
            <div class="clearfix"></div>
            <div class="orders">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="card">
                            <div class="card-header">
                                <strong class="card-title">比赛信息</strong>
                            </div>
                            <div class="table-stats order-table ov-h">
                                <table class="table" style="margin-bottom: 0;">
                                    <thead>
                                    <tr>
                                        <th class="serial">#</th>
                                        <th class="avatar">标题</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>比赛人数</th>
                                        <th>队名</th>
                                        <th>是否为组长</th>
                                        <th>报名状态</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${applyInfos}" var="applyInfo" varStatus="index">
                                        <tr>
                                            <td class="serial">${index.index + 1}</td>
                                            <td><a href="${ctx}/competition/info?id=${applyInfo.competitionId}">${applyInfo.competitionTitle}</a></td>
                                            <td><f:formatDate value="${applyInfo.competitionStartTime}" pattern="yyyy-MM-dd"/></td>
                                            <td><f:formatDate value="${applyInfo.competitionEndTime}" pattern="yyyy-MM-dd"/></td>
                                            <td>${applyInfo.studentsNum}</td>
                                            <td>${applyInfo.teamName}</td>
                                            <td>${applyInfo.teamLeader ? "是" : "否"}</td>
                                            <td>${applyInfo.state ? "已通过" : "待审核"}</td>
                                            <td>
                                                <c:if test="${applyInfo.studentsNum > 1 && applyInfo.teamLeader && (applyInfo.result == null || applyInfo.result.equals(''))}">
                                                    <a href="${ctx}/competition/upload?id=${applyInfo.id}" class="btn btn-success btn-sm">上传作品</a>
                                                </c:if>
                                                <%--<c:if test="${applyInfo.state}">--%>
                                                    <%--<a href="${ctx}/competition/print?id=${applyInfo.id}" class="btn btn-success btn-sm">打印准考证</a>--%>
                                                <%--</c:if>--%>
                                                <a href="${ctx}/organizer/delete?id=${applyInfo.id}" class="btn btn-danger btn-sm">退出</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/resource/admin/assets/js/vendor/jquery-2.1.4.min.js"></script>
<script src="${ctx}/resource/admin/assets/js/popper.min.js"></script>
<script src="${ctx}/resource/admin/assets/js/plugins.js"></script>
<script src="${ctx}/resource/admin/assets/js/main.js"></script>
</body>
</html>
