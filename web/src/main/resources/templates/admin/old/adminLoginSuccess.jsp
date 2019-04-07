<%@ page language="java" import="entity.Admin" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	/* 乱码处理 */
	request.setCharacterEncoding("utf-8");
%>
<%
	Admin admin = (Admin) session.getAttribute("admin");
	if(admin==null)
	{
%>
<%--<div style="text-align: center">
	<h1>未登录!!!</h1>
	2秒后跳转到登录页面
	<p>如果没有跳转，请点<a href="/admin/adminLogin.jsp">这里</a></p>
</div>--%>
<!-- Response.Write("<script language=javascript>alert('您还没有登录,请先登录');window.location = 'adminLogin.jsp'</script>"); -->
<script language=javascript>alert('您还没有登录,请先登陆!!!');window.location = 'adminLogin.jsp'</script>
<%
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>admin登陆成功</title>
</head>
<body>
<div>
	<div align="center">
		<p>&nbsp;</p>
		<p class="successInf">管理员登陆成功！</p>
		<p>
			<img src="./images/admin/time.gif" width="22" height="16">秒钟后将转回首页
		</p>
		<p>
			如果浏览器没有反应请单击
			<a href="admin/admin.jsp">此处</a>
		</p>
		<p>&nbsp;</p>
	</div>
</div>
<% response.setHeader("Refresh","3; URL=./admin/admin.html") ; %>
</body>
</html>
