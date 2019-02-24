<%@ page contentType="text/html; charset=utf-8" language="java"
         import="entity.Admin" errorPage="" %>
<%@ page import="util.Common" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>admin账户修改</title>
    <link rel="stylesheet" type="text/css" href="css/admin/common.css">
    <link rel="stylesheet" type="text/css" href="css/admin/main.css">
    <link rel="stylesheet" type="text/css" href="css/admin/style.css"/>
    <script type="text/javascript" src="./js/admin/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="./js/admin/libs/jquery-1.12.3.min.js"></script>
    <%--表单验证--%>
    <script type="text/javascript">
        function updateCheck(updateForm) {
            if (updateForm.adminPassword.value != updateForm.checkPassword.value) {
                alert("两次密码输入不相同，请重新输入！");
                updateForm.checkPassword.focus();
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
            <i class="icon-font"></i>
            <a href="admin/admin.jsp">后台首页</a>
            <span class="crumb-step">&gt;</span>
            <a class="crumb-name" href="/Admin_selectAll_admnin">admin账户管理</a>
            <span class="crumb-step">&gt; </span>
            <span>修改admin</span>
        </div>
    </div>
    <%
        //实例化公用类Common
        Common cm = new Common();
    %>

    <div class="result-wrap">
        <div class="result-content">
            <form action="/Admin_update_admin" method="post" id="updateForm"
                  name="updateForm" enctype="multipart/form-data" onsubmit="return updateCheck(this);">
                <table class="insert-tab" width="100%">
                    <%--隐藏的属性值--%>
                    <input type="hidden" name="admin.id" value="<s:property value="admin.id"/>"/>
                    <input type="hidden" name="admin.pic" value="<s:property value="admin.pic"/>"/>
                    <input type="hidden" name="admin.privilege" value="<s:property value="admin.privilege"/>"/>
                    <tbody>
                    <tr>
                        <th width="120">权限等级：</th>
                        <%--权限等级不可修改--%>
                        <%--<td>
                    <select name="privilege" id="privilege" class="required" required="">
                        <option value="">请选择</option>
                        <option value="1">超级管理员</option>
                        <option value="2">网页维护员</option>
                    </select>
                </td>--%>
                        <td><%=cm.privilegeToString(admin.getPrivilege())%>
                        </td>
                    </tr>
                    <tr>
                        <th>admin账户：</th>
                        <td><input id="adminname" name="admin.adminname" value="<s:property value="admin.adminname"/>"
                                   size="16" readonly="readonly"></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>修改密码：</th>
                        <td><input class="common-text" id="adminPassword" name="admin.adminpassword"
                                   size="16" placeholder="请输入新密码" required="" type="password"/></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>确认密码：</th>
                        <td><input class="common-text" id="checkPassword" name="checkPassword"
                                   size="16" placeholder="请确认新密码" required="" type="password"/></td>
                    </tr>
                    <tr>
                        <th>头像：</th>
                        <td>
                            <%-- <input name="pic1" id="update_pic" type="file" accept="image/*"/>--%>
                            <div class="image_container">
                                <img id="preview" src="upload/<s:property value="admin.pic"/>"
                                     style="height:100px;width:100px;border-width:0px;"/>
                            </div>
                            <s:file theme="simple" name="pic" id="update_pic" label="上传新头像" accept="image/*"></s:file>
                        </td>
                    </tr>
                    <tr>
                        <th>备注：</th>
                        <td>
                            <input name="admin.remarks" id="remarks" type="text"
                                   value="<s:property value="admin.remarks"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input class="btn btn-primary btn6 mr10" value="确认修改" type="submit"/>
                            <input class="btn btn6 mr10" type="reset" value="重置"/>
                            <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button"/>
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
        $("#update_pic").change(function () {
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

