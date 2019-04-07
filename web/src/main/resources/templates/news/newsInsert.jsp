<%@ page language="java" import="entity.Admin" pageEncoding="utf-8"%>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_admin.jsp" %>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<%--在线编辑器--%>
	<script type="text/javascript" charset="utf-8"
			src="./ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="./ueditor/ueditor.all.min.js">
	</script>
	<script type="text/javascript" charset="utf-8"
			src="./ueditor/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
<%--左边导航栏--%>
<%@include file="/commonModule/common.jsp" %>
<!--/sidebar-->
<div class="main-wrap">
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i>
			<a href="admin/admin.jsp">后台首页</a>
			<span class="crumb-step">&gt;</span>
			<a href="/News_selectAll_news">新闻管理</a>
			<span class="crumb-step">&gt;</span>
			<span class="crumb-name">添加新闻</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/News_insert_news" method="post" id="myform"
				  name="myform" enctype="multipart/form-data" >
				<table class="insert-tab" width="100%">
					<tbody>
					<tr>
						<th width="120">分类：</th>
						<td><select name="news.countdown" id="countdown" class="required" required="">
							<option value="">请选择</option>
							<option value="2">新闻事件</option>
							<option value="1">倒计时事件</option>
						</select></td>
					</tr>
					<tr>
						<th>标题：</th>
						<td><input class="common-text required" id="title"
								   name="news.title" size="50" type="text" required=""></td>
					</tr>
					<tr>
						<th>作者：</th>
						<%--不可修改--%>
						<td><input class="common-text" name="news.editor" size="50"
								   value="<s:property value="#session.admin.adminname"/>" type="text" readOnly="true" /></td>
					</tr>
					<tr>
						<th>缩略图：</th>
						<td>
							<div class="image_container">
								<img id="preview" src="./upload/null.jpg"
									 style="height:100px;width:100px;border-width:0px;"/>
							</div>
							<s:file theme="simple" id="file_upload" name="pic" label="上传缩略图" accept="image/*"></s:file>
						</td>
						<%--<td>
							<s:file  name="pic"  label="上传缩略图"></s:file>
							&lt;%&ndash;<input name="pic" id="file_upload" type="file" accept="image/*"/>
							<div class="image_container">
								<img id="preview" src="./upload/null.jpg" style="height:100px;width:100px;border-width:0px;" />
							</div>&ndash;%&gt;
						</td>--%>
					</tr>
					<tr>
						<th>内容：</th>
						<td width="1000">
							<%
								String content = "请输入内容:（不能为空！）";
							%>
							<script id="content" name="news.news" type="text/plain">
 											<%=content%>
							</script>
							<script type="text/javascript">
                                var editor = UE.getEditor('content');
							</script>
							<!-- <textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea> -->
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
<!--/main-->
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