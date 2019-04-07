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
                        <h4 class="mb"><i class="fa fa-angle-right"></i> 基本信息</h4>
                        <form action="/Admin_update_admin" class="form-horizontal style-form" method="post"
                              id="updateForm"
                              name="updateForm" enctype="multipart/form-data" onsubmit="return updateCheck(this);">
                            <input type="hidden" name="id" value="123"/>
                            <input type="hidden" name="pic" value="123"/>
                            <div class="form-group">
                                <label class="col-sm-1 col-md-offset-1 control-label">用户名：</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" placeholder="Disabled input here...">
                                    <span class="help-block">ajax局部刷新</span>
                                </div>
                                <label class="col-sm-1 col-md-offset-1 control-label">图片：</label>
                                <div class="col-sm-4">
                                    <img src="upload/123" width="50" height="50"/>
                                    <input name="pic1" id="update_pic" type="file"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 col-md-offset-1 control-label">姓名：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control">
                                    <span class="help-block">one line.</span>
                                </div>
                                <label class="col-sm-1 col-md-offset-1 control-label">性别：</label>
                                <div class="col-sm-4">
                                    <select name="countdown" id="catid" class="required" required="">
                                        <option value="">请选择</option>
                                        <option value="2">新闻事件</option>
                                        <option value="1">倒计时事件</option>
                                    </select>
                                    <span class="help-block">one line.</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 col-md-offset-1 control-label">籍贯：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control">
                                    <span class="help-block">one line.</span>
                                </div>
                                <label class="col-sm-1 col-md-offset-1 control-label">民族：</label>
                                <div class="col-sm-1">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 col-md-offset-1 control-label">联系地址：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control">
                                    <span class="help-block">one line.</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 col-md-offset-1 control-label">手机号：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control">
                                    <span class="help-block">one line.</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 col-md-offset-1 control-label">邮箱：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control">
                                    <span class="help-block">one line.</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-2 col-sm-4">
                                    <input class="btn btn-primary btn6 mr10" value="确认修改" type="submit">
                                    <input class="btn btn6 mr10" type="reset" value="重置"/>
                                    <input class="btn btn6" onclick="history.go(-1)" value="取消" type="button">
                                </div>
                            </div>

                            <%--<div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Disabled</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control">
                                </div>
                            </div>--%>
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
