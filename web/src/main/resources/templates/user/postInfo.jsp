<html lang="en">
<head>
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
                        <h4 class="mb"><i class="fa fa-angle-right"></i> 帖子详情</h4>
                        <div class="row">
                            <div class="col-sm-2 col-sm-offset-1">
                                <ul class="p_author">
                                    <%--评论者头像--%>
                                    <li class="icon">
                                        <div>
                                            <a style="" target="_blank" class="p_author_face " href="">
                                                <img username="" class="" src="../upload/null.jpg" height="65px"
                                                     width="65px"/>
                                            </a>
                                        </div>
                                    </li>
                                        <%--评论者用户名--%>
                                    <li class="d_name">
                                        <a alog-group="p_author" class="p_author_name j_user_card" href="" target="_blank">
                                            <s:property value="post.rightname"/>
                                            <img src="" class="nicknameEmoji" style="width:13px;height:13px">
                                        </a>
                                    </li>
                                    <li><span class="tail-info">1楼</span></li>
                                    <li> <span class="tail-info">2018-03-26 09:10</span></li>

                                </ul>
                            </div>
                            <div class="col-sm-8">
                                <lable>11111111</lable>
                               <%-- <div class="core_reply j_lzl_wrapper">
                                    <div class="core_reply_tail clearfix">
                                        <div class="j_lzl_r p_reply">
                                            <a rel="noopener" href="#" class="lzl_link_unfold" style="display:;">回复</a>
                                            <span class="lzl_link_fold" style="display:none">收起回复</span>
                                        </div>
                                        <div class="post-tail-wrap">
                                            <span class="j_jb_ele">
                                                <a rel="noopener" href="#" class="tail-info" data-checkun="un">
                                                    举报
                                                    <i class="icon-jubao pb_list_triangle_down"></i>
                                                </a>|
                                            </span>
                                            <span class="tail-info">2楼</span>
                                            <span class="tail-info">2018-03-26 09:10</span></div>
                                        <ul class="p_props_tail props_appraise_wrap"></ul>
                                    </div>
                                    &lt;%&ndash;<div class="j_lzl_container core_reply_wrapper"
                                         style="min-height: 0px; display: none;"
                                         data-field="{&quot;pid&quot;:118842532968,&quot;floor_num&quot;:2,&quot;total_num&quot;:0}">
                                        <div class="core_reply_border_top"></div>
                                        <div class="j_lzl_c_b_a core_reply_content">
                                            <ul class="j_lzl_m_w" style="display:none">
                                                <li class="lzl_li_pager j_lzl_l_p lzl_li_pager_s"
                                                    data-field="{total_num:0,total_page:0}"><a rel="noopener"
                                                                                               class="j_lzl_p btn-sub btn-small pull-right"
                                                                                               href="##"><i
                                                        class="icon-reply"></i>我也说一句</a>
                                                    <p>&nbsp;</p></li>
                                            </ul>
                                            <div class="lzl_editor_container j_lzl_e_c lzl_editor_container_s"
                                                 style="display:none;"></div>
                                            <input type="text" class="j_lzl_e_f_h" style="display:none;"></div>
                                        <div class="core_reply_border_bottom"></div>
                                    </div>&ndash;%&gt;
                                </div>--%>
                            </div>
                        </div>
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

<%--右上角弹窗--%>
<%--<script type="text/javascript">
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
</script>--%>

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
