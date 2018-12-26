function login() {
    var form = new FormData(document.getElementById("myFrom"));
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/login" ,//${pageContext.request.contextPath}
        data: form,
        processData:false,
        contentType:false,
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