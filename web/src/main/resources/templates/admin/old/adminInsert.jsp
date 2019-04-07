<%@ page contentType="text/html; charset=utf-8" language="java"
         import="entity.Admin" errorPage="" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <style>
        .errInf {
            text-align: center;
            color: red;
        }
    </style>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>新增admin</title>
    <link rel="stylesheet" type="text/css" href="css/admin/common.css">
    <link rel="stylesheet" type="text/css" href="css/admin/main.css">
    <link rel="stylesheet" type="text/css" href="css/admin/style.css"/>
    <script type="text/javascript" src="./js/admin/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="./js/admin/libs/jquery-1.12.3.min.js"></script>
    <%--表单验证--%>
    <script type="text/javascript">
        function insertCheck(insertForm) {
            if (insertForm.adminName.value == "") {
                alert("用户名不能为空，请输入用户名！");
                insertForm.adminName.focus();
                return false;
            }
            else if (insertForm.adminPassword.value == "") {
                alert("密码不能为空，请输入密码！");
                insertForm.adminPassword.focus();
                return false;
            } else if (insertForm.adminPassword.value != insertForm.checkPassword.value) {
                alert("两次密码输入不相同，请重新输入！");
                insertForm.checkPassword.focus();
                return false;
            }
            else {
                return true;
            }
        }
    </script>
</head>
<body>
<%--左边导航栏--%>
<%@include file="/commonModule/common.jsp" %>
<%--主体--%>
<div class="main-wrap">
    <%--导航横条--%>
    <div class="crumb-wrap">
        <div class="crumb-list">
            <i class="icon-font"></i> <a href="admin/admin.jsp">后台首页</a> <span
                class="crumb-step">&gt;</span> <a class="crumb-name"
                                                  href="/Admin_selectAll_admin">admin账户管理</a> <span
                class="crumb-step">&gt;</span> <span>新增管理员</span>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <%--错误提示--%>
            <%
                if (request.getAttribute("errInf") != null) {
            %>
            <p class="errInf">
                <%=(String) request.getAttribute("errInf")%>
            </p>
            <%
                }
            %>
            <%--表单--%>
            <form action="/Admin_insert_admin" method="post" id="insertForm"
                  name="insertForm" enctype="multipart/form-data" onsubmit="return insertCheck(this);">
                <%-- <input type="hidden" name="status" value="insert" />--%>
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th width="120"><i class="require-red">*</i>权限等级：</th>
                        <td><select name="admin.privilege" id="privilege"
                                    class="required" required="">
                            <option value="">请选择</option>
                            <option value="1">超级管理员</option>
                            <option value="2">网页维护员</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>admin账户：</th>
                        <%--struts数据验证--%>
                        <%--<td>--%>
                            <%--<s:textfield name="admin.adminname" ></s:textfield>--%>
                        <%--</td>--%>
                        <td><input class="common-text required" id="adminName"
                                   name="admin.adminname" size="16" required="" type="text"></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>admin密码：</th>
                        <td>
                            <input class="common-text" id="adminPassword" name="admin.adminpassword"
                                   size="16" required="" type="password">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>再次输入密码：</th>
                        <td><input class="common-text" id="checkPassword" name="checkPassword"
                                   size="16" required="" type="password"></td>
                    </tr>
                    <tr>
                        <th>上传头像：</th>
                        <td>
                            <div class="image_container">
                                <img id="preview" src="./upload/null.jpg"
                                     style="height:100px;width:100px;border-width:0px;"/>
                            </div>
                            <s:file theme="simple" id="file_upload" name="pic" label="上传头像" accept="image/*"></s:file>
                            <%--<input name="pic" id="file_upload" type="file" accept="image/*"/>
                            <div class="image_container">
                                <img id="preview" src="./upload/null.jpg"
                                     style="height:100px;width:100px;border-width:0px;"/>
                            </div>--%>
                        </td>
                    </tr>
                    <tr>
                        <th>备注：</th>
                        <td>
                            <%--<textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea>--%>
                            <input name="admin.remarks" id="remarks" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                            <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</div>
</body>
<%--图片预览--%>
<script type="text/javascript">
    $(function () {
        $("#file_upload").change(function () {
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            var $img = $("#preview");

            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                $img.attr('src', dataURL);
            } else {
                dataURL = $file.val();
                var imgObj = document.getElementById("preview");
                // 两个坑:
                // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
                // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
                imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

            }
        });
    });
</script>
</html>

