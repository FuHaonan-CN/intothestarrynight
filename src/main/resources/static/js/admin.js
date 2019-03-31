
$("#sub_nav_left > li").click(
    function(){
        var current = $(this);
        target = current.find('a').attr('target');   //找到a的链接
        set_iframe_url(target);
    }
);
 /*使用jquery替换main方法*/
function set_iframe_url(message) {
    var curHost = window.location.host;
    var curUrl = 'http://' + curHost + '/admin/main/' + message ;
    $("#main").load(curUrl);
}
/* 使用iframe方法 */
/*function set_iframe_url(message) {
    var curHost = window.location.host;
    var curUrl = 'http://' + curHost + '/admin/' + message ;
    $('#main-iframe').attr('src', curUrl);
}*/

/*模糊查询*/
function query() {
    /*获取数据*/
    initRecord(1);
    /*初始化分页*/
    initPage();
}
/*格式化显示ZonedDateTime时间*/
function formatDate(v) {
    if (v === 0||v === null){
        return null;
    }
    if (/^(-)?\d{1,10}$/.test(v)) {
        v = v * 1000;
    } else if (/^(-)?\d{1,13}$/.test(v)) {
        v = v * 1;
    }
    var dateObj = new Date(v);
    var month = dateObj.getMonth() + 1;
    var day = dateObj.getDate();
    var hours = dateObj.getHours();
    var minutes = dateObj.getMinutes();
    var seconds = dateObj.getSeconds();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hours < 10) {
        hours = "0" + hours;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    return dateObj.getFullYear() + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
//js获取地址栏参数的值, name为参数名
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = location.search.substr(1).match(reg);
    if (r !== null) return (r[2]);
    return null;
}

var total = -1;
var totalRecords = -1;

function initPageModel() {
    /*获取数据*/
    initRecord(1);
    /*初始化分页*/
    initPage();
}

/*初始化分页插件*/
function initPage() {
    var pageNo = getParameter('pno');
    if (!pageNo) {
        pageNo = 1;
    }

    //initRecord(pageNo);//页面加载完先把第一页的记录展示出来
    kkpager.generPageHtml({
        pno: pageNo,// 当前页码
        total: total,//总页码
        totalRecords: totalRecords,//总数据条数
        mode: 'click',//默认值是link，可选link或者click
        click: function (pageNo) {
            this.selectPage(pageNo);
            initRecord(pageNo);//点击页码的时候，显示对应页的记录
            return false;
        }
    },true);//搜索查询必须加上true-fhn
}