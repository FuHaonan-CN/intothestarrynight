<%@ page import="entity.UserBasicInfo" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%
    UserBasicInfo user = (UserBasicInfo) session.getAttribute("user");
    if(user==null)
    {
%>
<script language=javascript>alert('您还没有登录,请先登陆!!!');window.location = './userLogin.jsp'</script>
<%
        return;
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>用户登陆成功</title>
    <link href="#" rel="stylesheet" type="text/css">
</head>

<body>

<div id="pagecell1">

    <div align="center">
        <p>&nbsp;</p>
        <p class="successInf">用户账号登陆成功！</p>
        <p><img src="../images/login/time.gif" width="22" height="16">秒钟后将转回首页</p>
        <p>如果浏览器没有反应请单击<a href="/homepage.action">此处</a></p>
        <p>&nbsp;</p>
    </div>
</div>

<% response.setHeader("Refresh","3; URL=/homepage.action") ; %>
</body>
</html>
