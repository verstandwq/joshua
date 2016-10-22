/**
 * 前段导航栏
 */
$(document).ready(function () {
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.username.button').popup({
        position: "bottom center",
        inline: true,
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
    });

    $(".joshua.menu.open").on("click", function () {
        $(".joshua.mobile.menu").toggle();
    })
});

/**
 * 后台导航栏所需
 */
$(document).ready(function () {
    var getAccordionIndex = function () {
        var location = window.location.pathname;

        if (location.startsWith("/admin/static")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".static.item"));
        } else if (location.startsWith("/admin/user/fellowship") || location.startsWith("/admin/fellowship")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".fellowship.item"));
        } else if (location.startsWith("/admin/navigation")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".navigation.item"));
        } else if (location.startsWith("/admin/notification")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".notification.item"));
        } else if (location.startsWith("/admin/message")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".message.item"));
        } else if (location.startsWith("/admin/user")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".user.item"));
        } else if (location.startsWith("/admin/article")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".article.item"));
        } else if (location.startsWith("/admin/info") || location.startsWith("/admin/password")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".info.item"));
        }
    };

    $('.ui.admin.menu .ui.accordion').accordion('open', getAccordionIndex() - 1);
    $('.ui.admin.menu .ui.dropdown').dropdown();
});







