<%--
  Created by IntelliJ IDEA.
  User: FHN
  Date: 2017/10/29
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"
         import="entity.Admin" errorPage="" %>
<%@ page import="util.Common" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>新闻管理</title>
    <link rel="stylesheet" type="text/css" href="../../static/news/css/admin/common.css" />
    <link rel="stylesheet" type="text/css" href="../../static/news/css/admin/main.css" />
    <link rel="stylesheet" type="text/css" href="../../static/news/css/admin/style.css"/>
    <script type="text/javascript" src="../../static/news/js/admin/libs/modernizr.min.js"></script>
    <script language='javascript'>
        function delcfm() {
            if(!confirm('危险操作！确定要继续删除记录吗？')){ window.event.returnValue = false;}
        }
        //判断是否有选项被选择（未完成！）
//        function delMore(){
//            var e =document.myform.newsId;
//            var flag = false;
//            if(e==null)
//                alert("1至少选择一条新闻才能批量删除哦。");
//            for(var i=0 ; i<e.length ; i++) {
//                if(e[i].checked){
//                    flag = true;
//                    if(flag){
////                        confirm("确定批量删除这些数据吗？")
//                        document.myform.action="News?status=deleteMore";
//                        document.myform.submit();
//                    }else{
//                        alert("2至少选择一条新闻才能批量删除哦。");
//                    }
//                }
//                alert("3至少选择一条新闻才能批量删除哦。");
//            }
//        }
        //全选或清空
//        function setChecked(boxname) {
//            var boxs =document.getElementsByName(newsId);
//            var chkbox = document.getElementById("chkall");
//            if(chkbox.checked == true){
//                for( i=0 ; i<boxs.length ; i++) {
//                    boxs[i].checked = true;
//                }
//            }else{
//                for( i=0 ; i<boxs.length ; i++) {
//                    boxs[i].checked = false;
//                }
//            }
//        }
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
                    class="crumb-step">&gt;</span><span class="crumb-name">新闻管理</span>
            </div>
        </div>
        <%--模糊查询--%>
        <div class="search-wrap">
            <div class="search-content">
                <form action="/News_selectAll_news" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <s:select name="countdown" theme="simple" list="#{'0':'全部','1':'倒计时事件','2':'新闻事件'}"/>
                            </td>
                            <th width="80">新闻标题:</th>
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
        <%--新闻列表--%>
        <div class="result-wrap">
            <form action="/News_deleteMore_news" name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/News_insertInput_news"><i class="icon-font"></i>添加新闻</a>
                        <a href="javascript:;" id="batchDel" onclick="document.getElementById('myform').submit();delcfm()" > <i class="icon-font"></i>批量删除 </a>
                        <a id="updateOrd" href="javascript:void(0)"> <i class="icon-font"> </i>更新排序 </a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="83%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="chkall"
                                                             type="checkbox">全选</th>
                            <th width="5%">ID</th>
                            <th width="10%">类别</th>
                            <th width="15%">标题</th>
                            <th width="15%">图片</th>
                            <th width="10%">编者</th>
                            <th width="13%">发表时间</th>
                            <th width="5%">操作</th>
                        </tr>
                        <%
                            Common cm = new Common();
                        %>
                        <s:iterator value="#request.all" var="news">
                            <tr>
                                <td class="tc"><input name="newsId" value="<s:property value="newsid"/>" type="checkbox"></td>
                                <td><s:property value="#news.newsid"/></td>
                                <td>
                                    <s:if test="%{#news.countdown==1}">
                                        <%=cm.countdownToString(1)%>
                                    </s:if>
                                    <s:else>
                                        <%=cm.countdownToString(2)%>
                                    </s:else>
                                </td>
                                <td><a target="_blank" href="/News_queryById_news?id=<s:property value="newsid"/>" title=""><s:property value="#news.title"/></a></td>
                                <td><img src="upload/<s:property value="#news.pic"/>" width="50" height="50"/></td>
                                <td><s:property value="#news.editor"/></td>
                                <td><s:date name="#news.newstime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>
                                    <a class="link-update" href='/News_updateInput_news?id=<s:property value="newsid"/>'>修改</a>
                                    <a class="link-del"
                                       href='/News_delete_news?id=<s:property value="newsid"/>' onclick="delcfm()">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <%--翻页--%>
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
                            <a href="/News_selectAll_news?countdown=<s:property value="countdown"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page-1"/>">上一页</a>
                            </s:else>
                            </s:if>
                            <s:elseif test="page==1">
                            <a href="/News_selectAll_news?countdown=<s:property value="countdown"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page+1"/>">下一页</a>
                            </s:elseif>
                            <s:else>
                            <a href="/News_selectAll_news?countdown=<s:property value="countdown"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page-1"/>">上一页</a>
                            <a href="/News_selectAll_news?countdown=<s:property value="countdown"/>&keywords=<s:property value="keywords"/>&page=<s:property value="page+1"/>">下一页</a>
                            </s:else>
                            &nbsp;&nbsp;共<s:property value="#request.sumPage"/>页&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/主体-->
</div>
</body>
</html>