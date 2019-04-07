<%@ page import="entity.Admin" %>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title>哎呀！页面出错了！</title>
</head>
<body>
<center>
	<h1>哎呀！页面出错了！</h1>
	<hr>
	<br>
	<%
		/* 乱码处理 */
		request.setCharacterEncoding("utf-8");
	%>
	<%
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin==null)
		{
	%>
	<div style="text-align: center">
		<h1>未登录!!!</h1>
		2秒后跳转到登录页面
		<p>如果没有跳转，请点<a href="adminLogin.jsp">这里</a></p>
	</div>
	<%
			response.setHeader("refresh","2;URL=./adminLogin.jsp");
			return;
		}
	%>
	操作中出现错误，两秒后跳转到后台管理首页！！！<br>
	如果没有跳转，请按<a href="admin.html">这里</a>！！！
	<%
		response.setHeader("refresh","2;URL=../admin/admin.html") ;
	%>
</center>
</body>
</html>