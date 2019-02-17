<%@ page language="java" import="entity.Admin" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%--根目录的配置及session判断--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    /* 乱码处理 */
    request.setCharacterEncoding("utf-8");
%>
<base href="<%=basePath%>">
<%
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin != null) {
        response.setHeader("refresh", "0;URL=./admin.html");
        return;
    }
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>『星月夜』后台管理</title>
    <link href="css/admin/admin_login.css" rel="stylesheet" type="text/css"/>
    <!-- 直接使用jquery.js库 -->
    <%--${pageContext.request.contextPath }/js/admin/libs/jquery-1.12.3.min.js--%>
    <script type="text/javascript" src="./js/admin/libs/jquery-1.12.3.min.js"></script>

    <!-- 引入封装了failback的接口--initGeetest -->
    <script src="./js/admin/libs/gt.js"></script>

    <style>
        .lable {
            color: #ffffff;
            font-size: larger
        }

        .errInf {
            text-align: center;
            color: red;
        }

        body {
            margin: 50px 0;
            text-align: center;
        }

        .btn {
            border: 1px solid gray;
            width: 100px;
            height: 30px;
            font-size: 18px;
            cursor: pointer;
        }

        #embed-captcha {
            width: 300px;
            margin: 0 auto;
        }

        .show {
            display: block;
        }

        .hide {
            display: none;
        }

        #notice {
            color: red;
        }
    </style>

</head>
<body background="../images/admin/adminLoginBg.jpg">
<div class="admin_login_wrap">
    <h1 style="color: #ffffff;font-size: 40px">后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <s:if test="%{#request.errInf != null}">
                <p class="errInf"><s:property value="#request.errInf"/></p>
            </s:if>
            <form class="popup" action="/Admin_login_admin" method="post"
                    onsubmit="return formcheck(this)">
                <ul class="admin_items">
                    <li>
                        <s:label theme="simple" for="user" cssClass="lable" key="adminname"/>
                        <s:textfield name="admin.adminname" id="user" placeholder="请输入账户名" required="true" size="35"
                                     class="admin_input_style"/>
                    </li>
                    <li>
                        <s:label theme="simple" for="pwd" cssClass="lable" key="adminpass"/>
                        <s:password name="admin.adminpassword" id="pwd" placeholder="请输入密码" required="true"
                                    size="35" class="admin_input_style"/>
                    </li>
                    <li>
                        <s:label theme="simple" cssClass="lable" key="verification"/>
                    </li>
                    <div id="embed-captcha"></div>
                    <p id="wait" class="show">正在加载验证码......</p>
                    <p id="notice" class="hide">请先拖动验证码到相应位置</p>
                    <br>
                    <li>
                        <s:submit id="embed-submit" tabindex="3" class="btn btn-primary"
                                  cssStyle="height: 50px" align="center" key="loginSubmit"/>
                    </li>
                </ul>
            </form>

            <script>
                var handlerEmbed = function (captchaObj) {
                    $("#embed-submit").click(function (e) {
                        var validate = captchaObj.getValidate();
                        if (!validate) {
                            $("#notice")[0].className = "show";
                            setTimeout(function () {
                                $("#notice")[0].className = "hide";
                            }, 2000);
                            e.preventDefault();
                        }
                    });
                    // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
                    captchaObj.appendTo("#embed-captcha");
                    captchaObj.onReady(function () {
                        $("#wait")[0].className = "hide";
                    });
                    // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
                };
                $.ajax({
                    // 获取id，challenge，success（是否启用failback）
                    url: "pc-geetest/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        // 使用initGeetest接口
                        // 参数1：配置参数
                        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
                        initGeetest({
                            gt: data.gt,
                            challenge: data.challenge,
                            product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                            offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
                        }, handlerEmbed);
                    }
                });
            </script>

        </div>
    </div>
    <p class="admin_copyright"><a tabindex="5" href="/homepage.action">返回首页</a></p>
</div>
</body>
</html>