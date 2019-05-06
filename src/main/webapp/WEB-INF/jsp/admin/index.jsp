<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ff" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>管理系统</title>
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
                    <a href="${ctx}/admin/index"><i class="menu-icon fa fa-calendar"></i>比赛信息</a>
                </li>
                <li>
                    <a href="${ctx}/admin/apply"><i class="menu-icon fa fa-calendar"></i>报名信息</a>
                </li>
                <li>
                    <a href="${ctx}/admin/student"><i class="menu-icon fa fa-calendar"></i>学生信息</a>
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
                                <a href="${ctx}/organizer/create" class="btn btn-success btn-sm">添加</a>
                            </div>
                            <div class="table-stats order-table ov-h">
                                <table class="table" style="margin-bottom: 0;">
                                    <thead>
                                    <tr>
                                        <th class="serial">#</th>
                                        <th class="avatar">标题</th>
                                        <th>人数</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>组织者</th>
                                        <th>参考资料</th>
                                        <th>比赛结果</th>
                                        <th>通知结果</th>
                                        <th>比赛状态</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${competitions}" var="competition" varStatus="index">
                                        <tr>
                                            <td class="serial">${index.index + 1}</td>
                                            <td>${competition.title}</td>
                                            <td>${competition.studentsNum}</td>
                                            <td><f:formatDate value="${competition.startTime}" pattern="yyyy-MM-dd"/></td>
                                            <td><f:formatDate value="${competition.endTime}" pattern="yyyy-MM-dd"/></td>
                                            <td>${competition.organizerName}</td>
                                            <td><a href="${ctx}/competition/downloadData?id=${competition.id}">${ff:substring(competition.referenceData, 0, 8)}...</a></td>
                                            <td>
                                                <c:if test="${competition.result != null}">
                                                    <a href="${ctx}/competition/downloadResult?id=${competition.id}">${ff:substring(competition.result, 0 , 8)}...</a>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${competition.result != null && competition.sendResult}">
                                                    已发送
                                                </c:if>
                                                <c:if test="${competition.result != null && !competition.sendResult}">
                                                    <a href="${ctx}/competition/sendResult?id=${competition.id}" class="btn btn-info btn-sm">发送</a>
                                                </c:if>
                                            </td>
                                            <td>${competition.state ? "已通过" : "待审核"}</td>
                                            <td>
                                                <a href="${ctx}/admin/applyInfo?id=${competition.id}" class="btn btn-dark btn-sm">报名状态</a>
                                                <a href="${ctx}/admin/passCompetition?id=${competition.id}" class="btn btn-info btn-sm">通过</a>
                                                <a href="${ctx}/organizer/delete?id=${competition.id}" class="btn btn-danger btn-sm">删除</a>
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
