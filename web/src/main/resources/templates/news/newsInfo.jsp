<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->
<head>

    <base href="<%=basePath%>">
    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>新闻详情页</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- CSS
  ================================================== -->
    <link rel="stylesheet" href="../css/background/base.css">
    <link rel="stylesheet" href="../css/background/main.css">
    <link rel="stylesheet" href="../css/background/vendor.css">

    <!-- script
    ================================================== -->
    <script src="../js/background/modernizr.js"></script>

    <!-- favicons
     ================================================== -->
    <link rel="icon" type="image/png" href="favicon.png">

    <style>
        .box_pic {
            text-align: center;
            font-size: 14px;
        }
    </style>
</head>

<body>

<!-- header
================================================== -->
<header class="main-header">
    <div class="row">
        <div class="main-content">
            <br>
            <h1><s:property value="#request.news.title"/></h1>
            <p>作者：<s:property value="#request.news.editor"/><br>
            时间：<s:date name="#request.news.newstime" format="yyyy-MM-dd"/></p>
        </div>
    </div>
    <a class="menu-toggle" href="#"><span>Menu</span></a>
</header> <!-- /header -->

<!-- navigation
================================================== -->
<nav id="menu-nav-wrap">

    <h5>工具栏</h5>
    <ul class="nav-list">
        <li><a href="#" title="">分享到</a></li>
        <li><a href="#" title="">收藏</a></li>
        <li><a href="#" title="">评论</a></li>
        <li><a href="#" title="">转发</a></li>
        <li><a href="#" title="">我的主页</a></li>
    </ul>

    <h5>备注</h5>
    <p>我是谁？？？我在哪？？？干什么？？？</p>

</nav>

<!-- main content
================================================== -->
<main id="main-404-content" class="main-content-particle-js">

    <div class="content-wrap">

        <%-- <div class="shadow-overlay"></div>--%>

        <div class="main-content">
            <div class="row">
                <%--图片--%>
                <div class="box_pic">
                    <table width="450" cellspacing="0" cellpadding="3">
                        <tbody>
                        <tr>
                            <td align="center"><img src="/upload/<s:property value="#request.news.pic"/>"
                                                    alt="<s:property value="#request.news.title"/>"></td>
                        </tr>
                        <tr>
                            <td align="center"><font color="#ffffff"><s:property value="#request.news.title"/></font></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <p><s:property value="#request.news.news" escapeHtml="false"/></p>
            </div> <!-- /row -->
        </div> <!-- /main-content -->

        <footer>
            <div class="row">
                <div class="col-five tab-full bottom-links">
                    <ul class="links">
                        <li><a href="/homepage.action">返回首页</a></li>
                        <li><a href="#">关于</a></li>
                        <li><a href="mailto:798313710@qq.com">向我反馈</a></li>
                    </ul>
                </div>

            </div> <!-- /row -->
        </footer>

    </div> <!-- /content-wrap -->

</main> <!-- /main-404-content -->

<div id="preloader">
    <div id="loader"></div>
</div>

<!-- Java Script
================================================== -->
<script src="../js/background/jquery-2.1.3.min.js"></script>
<script src="../js/background/plugins.js"></script>
<script src="../js/background/main.js"></script>

</body>

</html>