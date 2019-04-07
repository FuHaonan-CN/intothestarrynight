/* 登陆 */
$("#loginForm").submit(function (e) {
    var form = new FormData($("#loginForm")[0]);
    // var form = new FormData();
    // form.append("userName","asd");
    // form.append("userPassWord","asd");
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/login",//${pageContext.request.contextPath}
        data: form,
        processData: false,//必须false才会避开jQuery对 formdata 的默认处理
        contentType: false,//必须false才会自动加上正确的Content-Type
        success: function (result) {
            if (result.rspCode == '000000') {
                //parent.layer.close();//关闭弹出层
                parent.location.reload();//更新父级页面
                /*用于登录后跳转到其他页面*/
                /*window.open(response.data.data, '_self');*/
            } else {
                $("#errorMsg")[0].style.display = "block";
                $("#errorMsg div p").html(result.rspMsg);
                setTimeout(function () {
                    $("#errorMsg")[0].style.display = "none";
                }, 3000);
            }
        },
        error: function (e) {
            alert("错误！！");
            // window.clearInterval(timer);
        }
    });
});

/* 注册 */
$("#registerForm").submit(function (e) {
    var form = new FormData($("#registerForm")[0]);
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/register",//${pageContext.request.contextPath}
        data: form,
        processData: false,//必须false才会避开jQuery对 formdata 的默认处理
        contentType: false,//必须false才会自动加上正确的Content-Type
        success: function (result) {
            if (result.rspCode == '000000') {
                //parent.layer.close();//关闭弹出层
                parent.location.reload();//更新父级页面
                // /*用于登录后跳转到其他页面*/
                // window.location.href="/index";
            } else {
                $("#errorMsg")[0].style.display = "block";
                $("#errorMsg div p").html(result.rspMsg);
                setTimeout(function () {
                    $("#errorMsg")[0].style.display = "none";
                }, 3000);
            }
        },
        error: function (e) {
            alert("错误！！");
            // window.clearInterval(timer);
        }
    });
});

/* 关闭*/
$('.loginbox-close').click(
    function () {
        parent.$('#login-register')[0].style.display = "none";
        parent.$('#login-register *').remove();
    }
);