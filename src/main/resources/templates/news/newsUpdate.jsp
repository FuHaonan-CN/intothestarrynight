
<%@ page language="java" import="entity.Admin"
		 pageEncoding="utf-8"%>
<%@ page import="entity.NewsInfo" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%--引用struts标签--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>后台管理</title>
	<link rel="stylesheet" type="text/css" href="../../static/news/css/admin/common.css" />
	<link rel="stylesheet" type="text/css" href="../../static/news/css/admin/main.css" />
	<link rel="stylesheet" type="text/css" href="../../static/news/css/admin/style.css"/>
	<script type="text/javascript" src="../../static/news/js/admin/libs/modernizr.min.js"></script>
	<script type="text/javascript" src="../../static/news/js/admin/libs/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="./ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="./ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="./ueditor/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
<%--左边导航栏--%>
<%@include file="/commonModule/common.jsp" %>
<%--<%
	NewsInfo news = (NewsInfo) request.getAttribute("news");
	int id = news.getNewsid();
	int countdown = news.getCountdown();
	String title = news.getTitle();
	String editor = news.getEditor();
	String newsinfo = news.getNews();
	String pic = news.getPic();
%>--%>
<!--/sidebar-->
<div class="main-wrap">

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i>
			<a href="admin/admin.jsp">后台首页</a>
			<span class="crumb-step">&gt;</span>
			<a href="javascript:;" onclick="history.go(-1)">新闻管理</a>
			<span class="crumb-step">&gt;</span>
			<span class="crumb-name">新闻修改</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/News_update_news" method="post" id="update"
				  name="update" onsubmit=" return formcheck(this) " enctype="multipart/form-data" >
				<table class="insert-tab" width="100%">
					<input type="hidden" name="news.newsid" value="<s:property value="news.newsid"/>"/>
					<input type="hidden" name="news.pic" value="<s:property value="news.pic"/>"/>
					<tbody>
					<tr>
						<th width="120"><i class="require-red">*</i>分类：</th>
						<td>
							<%--<select name="news.countdown" id="catid" class="required" required="">
								<option value="">全部</option>
								<option value="1" <%if (countdown==1){%>selected<%}%>>倒计时事件</option>
								<option value="2" <%if (countdown==2){%>selected<%}%>>新闻事件</option>
							</select>--%>
							<s:select name="news.countdown" theme="simple" list="#{'1':'倒计时事件','2':'新闻事件'}"/>
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>标题：</th>
						<td><input class="common-text required" id="title"
								   name="news.title" size="50" value="<s:property value="news.title"/>" type="text" required=""></td>
					</tr>
					<tr>
						<th>作者：</th>
						<td><input class="common-text" name="news.editor" size="16"
								   value="<s:property value="news.editor"/>" type="text" readOnly="true" required=""></td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>缩略图：</th>
						<%--<td>--%>
							<%--<img src="upload/<%=pic%>" width="50" height="50" />--%>
							<%--<input name="pic1" id="update_pic" type="file" required=""/> <!--<input type="submit" onclick="submitForm('/jscss/admin/design/upload')" value="上传图片"/>-->--%>
						<%--</td>--%>
						<td>
							<input name="pic" id="update_pic" type="file" accept="image/*"/>
							<div class="image_container">
								<img id="preview" src="upload/<s:property value="news.pic"/>" style="height:100px;width:100px;border-width:0px;" />
							</div>
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>内容：</th>
						<td width="1000">
							<script id="content" name="news.news" type="text/plain"><s:property value="news.news" escapeHtml="false"/></script>
							<script type="text/javascript">
                                var editor = UE.getEditor('content');
							</script> <!-- <textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea> -->
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input class="btn btn-primary btn6 mr10" value="确认修改" type="submit">
							<input class="btn btn6" onclick="history.go(-1)" value="取消" type="button">
						</td>
					</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

</div>
<!--/main-->
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
