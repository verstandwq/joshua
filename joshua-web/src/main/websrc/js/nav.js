// 登录页面的弹出和关闭
$(document).ready(function () {
    $("#login").click(function () {
        $(".overlay").css({display: "block"});
        $(".login-content").css({display: "block"});
    });
    $("#close").click(function () {
        $(".overlay").css({display: "none"});
        $(".login-content").css({display: "none"});
    });
    $(".ui.dropdown").dropdown();
});