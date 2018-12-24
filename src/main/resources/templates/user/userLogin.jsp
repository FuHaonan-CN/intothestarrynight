<%@ page language="java" import="entity.UserBasicInfo" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	/* 乱码处理 */
	request.setCharacterEncoding("utf-8");
%>
<%
	UserBasicInfo user = (UserBasicInfo) session.getAttribute("user");
	if(user!=null)
	{
%>
<%
		response.setHeader("refresh","0;URL=./user/userLoginSuccess.jsp");
		return;
	}
%>
<html>
<!-- Head -->
<head>
	<base href="<%=basePath%>">
	<title>Through The Universe</title>

	<!-- Meta-Tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script type="application/x-javascript">
        addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
	</script>
	<%--表单验证--%>
	<script type="text/javascript">
        function loginCheck(registerForm) {
           if(registerForm.userPassword.value!=registerForm.checkPassword.value){
                alert("两次密码输入不相同，请重新输入！");
               registerForm.checkPassword.focus();
                return false;
            }
            else {
                return true;
            }
        }
	</script>
	<!-- //Meta-Tags -->

	<!-- Style -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
	<style>
		.errInf{
			text-align: center;
			color: red;
		}
	</style>

</head>
<!-- //Head -->

<!-- Body -->
<body>

<!-- STARS -->
<div class="stars large"></div>
<div class="stars small"></div>
<!-- /STARS -->

<!--<h1>Through The Universe</h1>-->

<!-- LOGO -->
<img src="./images/logo.png" alt="Through The Universe" style="margin-top: 8%">
<!-- /LOGO -->

<div class="container w3layouts agileits" style="margin-top: 50px;height: 420px">
	<%
		if (request.getAttribute("errInf") != null) {
	%>
	<p class="errInf">
		<%=(String) request.getAttribute("errInf")%>
	</p>
	<br>
	<%
		}
	%>
<%--登录--%>
	<div class="login w3layouts agileits">
		<h2>登 录</h2>
		<div class="send-button w3layouts agileits">
			<form method="post" action="/User_login_user">
				<input type="text" name="user.username" placeholder="用户名" required="">
				<input type="password" name="user.userpassword" placeholder="密码" required="">
				<ul class="tick w3layouts agileits">
					<li>
						<input type="checkbox" id="brand1" value="">
						<label for="brand1">
							<span></span>记住我
						</label>
					</li>
				</ul>
				<input type="submit" value="登 录" />
			</form>
		</div>
		<a href="#">忘记密码?</a>
		<a href="/admin/adminLogin.jsp" style="float: right">管理员?</a>
		<div class="social-icons w3layouts agileits">
			<p>- 其他方式登录 -</p>
			<ul>
				<li class="qq">
					<a href="#">
						<span class="icons w3layouts agileits"></span> <span class="text w3layouts agileits">QQ</span>
					</a>
				</li>
				<li class="weixin w3ls">
					<a href="#">
						<span class="icons w3layouts"></span> <span class="text w3layouts agileits">微信</span>
					</a>
				</li>
				<li class="weibo aits">
					<a href="#">
						<span class="icons agileits"></span> <span class="text w3layouts agileits">微博</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<%--注册--%>
	<div class="register w3layouts agileits send-button">
		<h2>注 册</h2>
		<form action="/User_insert_user" name="registerForm" method="post" onsubmit=" return loginCheck(this) ">
			<input type="text" Name="user.username" placeholder="用户名" required="">
			<input type="password" id="userPassword" Name="user.userpassword" placeholder="密码" required="">
			<input type="password" id="checkPassword" Name="checkPassword" placeholder="确认密码" required="">
			<input type="text" Name="user.useremail" placeholder="邮箱"
				   pattern="^[0-9a-zA-Z_]{0,19}@[0-9a-zA-Z]{1,13}\.[com,cn,net]{1,3}$" required="">
			<input type="text" Name="user.usertel" placeholder="手机号码" pattern="1[0-9]{10}" required="">
			<input type="submit" value="免费注册">
		</form>
	</div>

</div>
<%--<script src="js/jquery-1.8.3.js"></script>--%>
<script src="./js/register.js"></script>

</body>
<!-- //Body -->

</html>