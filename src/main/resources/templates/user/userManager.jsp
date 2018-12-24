<%@page import="entity.Admin" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>用户账号管理</title>
    <link rel="stylesheet" type="text/css" href="css/admin/common.css">
    <link rel="stylesheet" type="text/css" href="css/admin/main.css">
    <link rel="stylesheet" type="text/css" href="css/admin/style.css"/>
    <script type="text/javascript" src="./js/admin/libs/modernizr.min.js"></script>
    <script language='javascript'>
        function delcfm() {
//            if(!confirm('确定要删除该条记录吗？')){ window.location.href='userManager.jsp';return;}
            if(!confirm('确定要删除该条记录吗？')){ window.event.returnValue = false;}
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
                <span class="crumb-name">用户账号管理</span>
            </div>
        </div>
        <%--模糊查询--%>
        <div class="search-wrap">
            <div class="search-content">
                <form action="/User_selectAll_user" method="post">
                    <table class="search-tab">
                        <tr>
                                <%
                                    String k = request.getAttribute("currentKeywords").toString();
                                %>
                            <th width="70">用户名:</th>
                            <td><input class="common-text" placeholder="关键字"
                                       name="keywords" value="<%=k%>" id="keywords" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub"
                                       value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <%--用户列表--%>
        <div class="result-wrap">
            <form action="/User_deleteMore_user" name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <%--<a href="user/userInsert.jsp"> <i class="icon-font"></i>新增用户 </a>--%>
                        <a href="javascript:;" id="batchDel" onclick="document.getElementById('myform').submit();delcfm()" > <i class="icon-font"></i>批量删除 </a>
                        <a id="updateOrd" href="javascript:void(0)"> <i class="icon-font"> </i>更新排序 </a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="83%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox">全选</th>
                            <th width="10%">ID</th>
                            <th width="10%">图片</th>
                            <th width="15%">用户名</th>
                            <th width="15%">密码</th>
                            <th width="12%">邮箱</th>
                            <th width="12%">手机号</th>
                            <th width="10%">操作</th>
                        </tr>
                        <s:iterator value="#request.all" var="user">
                            <tr>
                                <td class="tc"><input name="userId" value="<s:property value="id"/>" type="checkbox"></td>
                                <td><s:property value="#user.id"/></td>
                                <td><img src="upload/<s:property value="#user.pic"/>" width="30" height="30"/></td>
                                <td><s:property value="#user.username"/></td>
                                <td><s:property value="#user.userpassword"/></td>
                                <td><s:property value="#user.useremail"/></td>
                                <td><s:property value="#user.usertel"/></td>
                                <td>
                                    <a class="link-del" href='/User_delete_user?id=<s:property value="id"/>' onclick="delcfm()">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>

                    <%--翻页--%>
                    <%
                        String currentPage = request.getAttribute("currentPage").toString();
                        String sum = request.getAttribute("page").toString();
                        int Rowcount = Integer.parseInt(sum);
                        int pagesize = 6;
                        int sumPage = (Rowcount+pagesize-1)/pagesize; //计算总页数
                    %>
                    <div class="list-page">
                        <p>当前第<%= Integer.parseInt(currentPage) %>页&nbsp;&nbsp;
                                <%if(Integer.parseInt(currentPage)==sumPage){
							if(currentPage.equals("1")){
						%>
                            <a>下一页</a>
                                <%	} else {
						%>
                            <a href="/User_selectAll_user?keywords=<%=k%>&page=<%= Integer.parseInt(currentPage)-1%>">上一页</a>
                                <%	}
						} else if(currentPage.equals("1")){
						%>
                            <a href="/User_selectAll_user?keywords=<%=k%>&page=<%= Integer.parseInt(currentPage)+1%>">下一页</a>
                                <%} else {
						%>
                            <a href="/User_selectAll_user?keywords=<%=k%>&page=<%= Integer.parseInt(currentPage)-1%>">上一页</a>
                            <a href="/User_selectAll_user?keywords=<%=k%>&page=<%= Integer.parseInt(currentPage)+1%>">下一页</a>
                                <%
						}
						%>
                            &nbsp;&nbsp;共<%=sumPage %>页&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>