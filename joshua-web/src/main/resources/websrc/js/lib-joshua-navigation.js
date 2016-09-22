/**
 * 前段导航栏
 */
$(document).ready(function () {
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.username.button').popup({
        position: "bottom center",
        on: "click"
    });

    $('.user.logout.item').on("click", function () {
        new Dialog("注销", "您确定要注销吗？", function () {
            var formData = new FormData();
            formData.append("_csrf", $(".user.logout.item form input[name='_csrf']").val());

            $.ajax({
                url: "/logout",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    new Dialog("注销", "注销成功", function () {
                        window.location = '/login?logout';
                    }).message();
                },
                error: function () {
                    new Dialog("注销", "注销失败").error();
                }
            });
        }).confirm();
    })
});

/**
 * 后台导航栏所需
 */
$(document).ready(function () {
    $('.ui.admin.menu .ui.accordion').accordion('refresh');
    $('.ui.admin.menu .ui.dropdown').dropdown();
});







