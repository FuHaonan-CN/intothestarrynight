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

    <title>我发布的帖子</title>

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
    <script language='javascript'>
        function delcfm() {
            if (!confirm('危险操作！确定要继续删除记录吗？')) {
                window.event.returnValue = false;
            }
        }
    </script>

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
                        <h4 class="mb"><i class="fa fa-angle-right"></i> 我发布的帖子</h4>
                        <form action="/User_deleteMore_user" name="myform" id="myform" method="post">
                            <div class="result-title">
                                <div class="result-list">
                                    <%--<a href="user/userInsert.jsp"> <i class="icon-font"></i>新增用户 </a>--%>
                                    <a href="javascript:;" id="batchDel"
                                       onclick="document.getElementById('myform').submit();delcfm()"> <i
                                            class="icon-font"></i>批量删除 </a>
                                    <a id="updateOrd" href="javascript:void(0)"> <i class="icon-font"> </i>更新排序 </a>
                                </div>
                            </div>

                            <div class="result-content">
                                <table class="result-tab" width="83%">
                                    <tr>
                                        <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox">全选
                                        </th>
                                    </tr>
                                    <s:iterator value="#request.all" var="post">
                                        <div class="row">
                                            <div class="form-group">
                                                <div class="col-sm-1">
                                                    <img src="../upload/<s:property value="pic"/>" width="50"
                                                         height="50"/>
                                                </div>
                                                <label class="col-sm-1 control-label"><s:property
                                                        value="rightname"/></label>
                                                <label class="col-sm-1 control-label"><s:property
                                                        value="posttime"/></label>
                                                <label class="col-sm-1 control-label">
                                                    <a target="_blank" href="/Post_queryById_post?id=<s:property value="postid"/>">
                                                        <s:property value="posttitle"/>
                                                    </a>
                                                </label>
                                                <label class="col-sm-1 control-label"><s:property
                                                        value="postinfo" escapeHtml="false"/></label>
                                                <div class="col-sm-1 control-label">
                                                    <a class="link-update"
                                                       href='/Post_updateInput_post?id=<s:property value="postid"/>'>修改</a>&nbsp;
                                                    <a class="link-del"
                                                       href='/Post_delete_post?id=<s:property value="postid"/>' onclick="delcfm()">删除</a>
                                                </div>
                                            </div>
                                        </div>
                                    </s:iterator>
                                </table>

                                <%--翻页--%>
                                <%--<%
                                    String currentPage = request.getAttribute("currentPage").toString();
                                    String sum = request.getAttribute("page").toString();
                                    int Rowcount = Integer.parseInt(sum);
                                    int pagesize = 6;
                                    int sumPage = (Rowcount+pagesize-1)/pagesize; //计算总页数
                                %>--%>
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
