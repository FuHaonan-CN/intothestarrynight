/* 登陆 */
function login() {
    var form = new FormData($("#loginForm")[0]);
    // var form = new FormData();
    // form.append("userName","asd");
    // form.append("userPassWord","asd");
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/login" ,//${pageContext.request.contextPath}
        data: form,
        processData:false,//必须false才会避开jQuery对 formdata 的默认处理
        contentType:false,//必须false才会自动加上正确的Content-Type
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
        },
        error : function(e) {
            alert("错误！！");
            window.clearInterval(timer);
        }
    });
}
/* 注册 */
function register() {
    var form = new FormData($("#registerForm")[0]);
    // var form = new FormData();
    // form.append("userName","asd");
    // form.append("userPassWord","asd");
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/register" ,//${pageContext.request.contextPath}
        data: form,
        processData:false,//必须false才会避开jQuery对 formdata 的默认处理
        contentType:false,//必须false才会自动加上正确的Content-Type
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
        },
        error : function(e) {
            alert("错误！！");
            window.clearInterval(timer);
        }
    });
}