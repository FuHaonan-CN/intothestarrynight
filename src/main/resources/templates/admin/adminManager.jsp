<%@ page import="util.Common" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>admin账号管理</title>
    <link rel="stylesheet" type="text/css" href="css/admin/common.css">
    <link rel="stylesheet" type="text/css" href="css/admin/main.css">
    <link rel="stylesheet" type="text/css" href="css/admin/style.css"/>
    <script type="text/javascript" src="./js/admin/libs/modernizr.min.js"></script>
    <script language='javascript'>
        function delcfm() {
//            if(!confirm('确定要删除该条记录吗？')){ window.location.href='userManager.jsp';return;}
            if (!confirm('危险操作！确定要继续删除记录吗？')) {
                window.event.returnValue = false;
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
            <span class="crumb-name">账号管理</span>
        </div>
    </div>
    <%--模糊查询--%>
    <div class="search-wrap">
        <div class="search-content">
            <form action="/Admin_selectAll_admin" method="post">
                <table class="search-tab">
                    <tr>
                        <th width="120">选择分类:</th>
                        <td>
                            <s:select name="privilege" theme="simple" list="#{'0':'全部','1':'超级管理员','2':'网页维护员'}"/>
                        </td>
                        <th width="80">管理员账号:</th>
                        <td>
                            <input class="common-text" placeholder="关键字"
                                   name="keywords" value="<s:property value="keywords"/>" id="keywords" type="text">
                        </td>
                        <td>
                            <input class="btn btn-primary btn2" name="sub"
                                   value="查询" type="submit">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <%--admin列表--%>
    <div class="result-wrap">
        <form action="/Admin_deleteMore_admin" name="myform" id="myform" method="post">
            <div class="result-title">
                <div class="result-list">
                    <a href="/Admin_insertInput_admin"> <i class="icon-font"></i>新增admin </a>
                    <a href="javascript:;" id="batchDel" onclick="document.getElementById('myform').submit();delcfm()">
                        <i class="icon-font"></i>批量删除 </a>
                    <a id="updateOrd" href="javascript:void(0)"> <i class="icon-font"> </i>更新排序 </a>
                </div>
            </div>
            <div class="result-content">
                <table class="result-tab" width="83%">
                    <tr>
                        <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox">全选</th>
                        <th width="5%">ID</th>
                        <th width="10%">权限等级</th>
                        <th width="10%">图片</th>
                        <th width="15%">管理员账号</th>
                        <th width="15%">密码</th>
                        <th width="12%">备注</th>
                        <th width="10%">操作</th>
                    </tr>
                    <%
                        Common cm = new Common();
                    %>
                    <s:iterator value="#request.all" var="admin">
                        <tr>
                            <td class="tc"><input name="adminId" value="<s:property value="id"/>" type="checkbox"></td>
                            <td><s:property value="#admin.id"/></td>
                            <td>
                                <s:if test="%{#admin.privilege==1}">
                                    <%=cm.privilegeToString(1)%>
                                </s:if>
                                <s:else>
                                    <%=cm.privilegeToString(2)%>
                                </s:else>
                            </td>
                            <td><img src="./upload/<s:property value="#admin.pic"/>" width="30" height="30"/></td>
                            <td><s:property value="#admin.adminname"/></td>
                            <td><s:property value="#admin.adminpassword"/></td>
                            <td>
                                    <%--另一种if写法--%>
                                <s:if test="#admin.remarks==null||#admin.remarks==''">
                                    无
                                </s:if>
                                <s:else>
                                    <s:property value="#admin.remarks"/>
                                </s:else>
                            </td>
                            <td>
                                <a class="link-update"
                                   href='/Admin_updateInput_admin?id=<s:property value="id"/>'>修改</a>
                                <a class="link-del"
                                   href='/Admin_delete_admin?id=<s:property value="id"/>' onclick="delcfm()">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
                <%--翻页模块--%>
                <%
                    String sum = request.getAttribute("count").toString();
                    int Rowcount = Integer.parseInt(sum);
                    int pagesize = 6;
                    int sumPage = (Rowcount + pagesize - 1) / pagesize; //计算总页数
                    request.setAttribute("sumPage", sumPage);
                %>

                <div class="list-page">
                    <p>当前第<s:property value="page"/>页&nbsp;&nbsp;
                        <s:if test="page==#request.sumPage">
                        <s:if test="page==1">
                        <a>下一页</a>
                        </s:if>
                        <s:else>
                        <a href="/Admin_selectAll_admin?privilege=<s:property value="privilege"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page-1"/>">上一页</a>
                        </s:else>
                        </s:if>
                        <s:elseif test="page==1">
                        <a href="/Admin_selectAll_admin?privilege=<s:property value="privilege"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page+1"/>">下一页</a>
                        </s:elseif>
                        <s:else>
                        <a href="/Admin_selectAll_admin?privilege=<s:property value="privilege"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page-1"/>">上一页</a>
                        <a href="/Admin_selectAll_admin?privilege=<s:property value="privilege"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page+1"/>">下一页</a>
                        </s:else>
                        &nbsp;&nbsp;共<s:property value="#request.sumPage"/>页&nbsp;
                </div>
            </div>
        </form>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>