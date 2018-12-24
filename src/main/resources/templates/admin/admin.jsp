<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>『星月夜』后台管理首页</title>
    <link rel="stylesheet" type="text/css" href="css/admin/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/admin/main.css"/>
    <link rel="stylesheet" type="text/css" href="css/admin/style.css"/>
    <script type="text/javascript" src="./js/admin/libs/modernizr.min.js"></script>

</head>
<body>
<%--左边导航栏--%>
<%@include file="/commonModule/common.jsp" %>
<%--主体--%>
<div class="main-wrap">
    <%--导航横条--%>
    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>『<s:property value="#session.admin.adminname"/>』欢迎回来。</span>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-title">
            <h1>快捷操作</h1>
        </div>
        <div class="result-content">
            <div class="short-wrap">
                <a href="/News_insertInput_news"><i class="icon-font">&#xe001;</i>新增新闻</a>
                <a href="/Admin_insertInput_admin"><i class="icon-font">&#xe005;</i>新增管理员</a>
                <!--&#xe001是图标  -->
            </div>
        </div>
    </div>
    <%--main--%>
    <div class="result-wrap">
        <div class="result-title">
            <h1>系统基本信息</h1>
        </div>
        <div class="result-content">
            <ul class="sys-info-list">
                <li>
                    <label class="res-lab">操作系统</label><span class="res-info">WINNT</span>
                </li>
                <li>
                    <label class="res-lab">运行环境</label><span class="res-info">Apache/2.2.21 (Win64) PHP/5.3.10</span>
                </li>
                <li>
                    <label class="res-lab">PHP运行方式</label><span class="res-info">apache2handler</span>
                </li>
                <li>
                    <label class="res-lab">静静设计-版本</label><span class="res-info">v-0.1</span>
                </li>
                <li>
                    <label class="res-lab">上传附件限制</label><span class="res-info">2M</span>
                </li>
                <li>
                    <label class="res-lab">北京时间</label><span class="res-info">2017年12月6日 21:08:24</span>
                </li>
                <li>
                    <label class="res-lab">服务器域名/IP</label><span class="res-info">localhost [ 127.0.0.1 ]</span>
                </li>
                <li>
                    <label class="res-lab">Host</label><span class="res-info">127.0.0.1</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-title">
            <h1>使用帮助</h1>
        </div>
        <div class="result-content">
            <ul class="sys-info-list">
            </ul>
        </div>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>