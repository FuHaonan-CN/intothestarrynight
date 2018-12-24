<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%--根目录的配置及session判断--%>
<%@include file="/commonModule/preloading_user.jsp" %>
<%--引用struts标签--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Dashboard">
	<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

	<title>发布帖子</title>

	<!-- Bootstrap core CSS -->
	<link href="../../static/user/assets/css/bootstrap.css" rel="stylesheet">
	<!--external css-->
	<link href="../../static/user/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="../../static/user/assets/css/zabuto_calendar.css">
	<link rel="stylesheet" type="text/css" href="../../static/user/assets/js/gritter/css/jquery.gritter.css"/>
	<link rel="stylesheet" type="text/css" href="../../static/user/assets/lineicons/style.css">

	<!-- Custom styles for this template -->
	<link href="../../static/user/assets/css/style.css" rel="stylesheet">
	<link href="../../static/user/assets/css/style-responsive.css" rel="stylesheet">

	<script src="../../static/user/assets/js/chart-master/Chart.js"></script>

	<%--在线编辑器--%>
	<script type="text/javascript" charset="utf-8"
			src="../ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="../ueditor/ueditor.all.min.js">
	</script>
	<script type="text/javascript" charset="utf-8"
			src="../ueditor/lang/zh-cn/zh-cn.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>

<section id="container">
	<!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
	<!--header start-->
	<%@include file="/user/commonModule/userCommonHeader.jsp" %>
	<!--header end-->

	<!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
	<!--sidebar start-->
	<%@include file="/user/commonModule/userCommonSidebar.jsp" %>
	<!--sidebar end-->

	<!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!-- BASIC FORM ELELEMNTS -->
			<div class="row mt">
				<div class="col-lg-12">
					<div class="form-panel">
						<h4 class="mb"><i class="fa fa-angle-right"></i> 发布帖子</h4>
						<form action="/Post_insert_post" class="form-horizontal style-form" method="post"
							  id="updateForm"
							  name="updateForm" enctype="multipart/form-data" onsubmit="return updateCheck(this);">
							<%--隐藏id值--%>
							<input type="hidden" name="post.postid" value="<s:property value="post.postid"/>"/>
							<div class="form-group">
								<label class="col-sm-1 col-sm-offset-1 control-label">标题：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="post.posttitle" value="<s:property value="post.posttitle"/>">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-1 col-sm-offset-1 control-label">内容：</label>
								<div class="col-sm-7">
									<script id="content" name="news.news" type="text/plain" style="height: 400px">
										<s:property value="post.postinfo" escapeHtml="false"/>
									</script>
                                    <script type="text/javascript">
                                    var editor = UE.getEditor('content');
									</script>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-2 col-sm-4">
									<input class="btn btn-primary btn6 mr10" value="确认修改" type="submit">
									<input class="btn btn6" onclick="history.go(-1)" value="取消" type="button">
								</div>
							</div>
						</form>
					</div>
				</div><!-- col-lg-12-->
			</div><!-- /row -->
		</section>
	</section>
	<!--main content end-->

	<!--footer start-->
	<%@include file="/user/commonModule/userCommonFooter.jsp" %>
	<!--footer end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="../../static/user/assets/js/jquery.js"></script>
<script src="../../static/user/assets/js/jquery-1.8.3.min.js"></script>
<script src="../../static/user/assets/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="../../static/user/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="../../static/user/assets/js/jquery.scrollTo.min.js"></script>
<script src="../../static/user/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="../../static/user/assets/js/jquery.sparkline.js"></script>


<!--common script for all pages-->
<script src="../../static/user/assets/js/common-scripts.js"></script>

<script type="text/javascript" src="../../static/user/assets/js/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="../../static/user/assets/js/gritter-conf.js"></script>

<!--script for this page-->
<script src="../../static/user/assets/js/sparkline-chart.js"></script>
<script src="../../static/user/assets/js/zabuto_calendar.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        var unique_id = $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: 'Welcome to Dashgum!',
            // (string | mandatory) the text inside the notification
            text: 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo. Free version for <a href="" target="_blank" style="color:#ffd777">BlackTie.co</a>.',
            // (string | optional) the image to display on the left
            image: 'assets/img/ui-sam.jpg',
            // (bool | optional) if you want it to fade out on its own or just sit there
            sticky: true,
            // (int | optional) the time you want it to be alive for before fading out
            time: '',
            // (string | optional) the class name you want to apply to that specific message
            class_name: 'my-sticky-class'
        });

        return false;
    });
</script>

<script type="application/javascript">
    $(document).ready(function () {
        $("#date-popover").popover({html: true, trigger: "manual"});
        $("#date-popover").hide();
        $("#date-popover").click(function (e) {
            $(this).hide();
        });

        $("#my-calendar").zabuto_calendar({
            action: function () {
                return myDateFunction(this.id, false);
            },
            action_nav: function () {
                return myNavFunction(this.id);
            },
            ajax: {
                url: "show_data.php?action=1",
                modal: true
            },
            legend: [
                {type: "text", label: "Special event", badge: "00"},
                {type: "block", label: "Regular event",}
            ]
        });
    });


    function myNavFunction(id) {
        $("#date-popover").hide();
        var nav = $("#" + id).data("navigation");
        var to = $("#" + id).data("to");
        console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
</script>


</body>
</html>
