/**
 * 公共函数定义
 */
var enableArticle = function (id) {
    new Dialog("激活文章", "激活文章后会重新在网站显示，确认要激活吗？", function () {
        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("id", id);

        $.ajax({
            url: "/api/article/enable",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if ("success" == status) {
                    new Dialog("激活文章", "激活成功", function () {
                        window.location.reload();
                    }).message();
                } else {
                    new Dialog("激活文章", "激活失败，原因：" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("激活文章", "激活失败", function () {
                }).error();
            }
        });
    }).confirm();
};

var disableArticle = function (id) {
    new Dialog("禁用文章", "禁用文章后不会在网站显示，确认要禁用吗？", function () {
        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("id", id);

        $.ajax({
            url: "/api/article/disable",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if ("success" == status) {
                    new Dialog("禁用文章", "禁用成功", function () {
                        window.location.reload();
                    }).message();
                } else {
                    new Dialog("禁用文章", "禁用失败，原因：" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("禁用文章", "禁用失败", function () {
                }).error();
            }
        });
    }).confirm();
};

$(document).ready(function () {
    $(".ui.admin.article.enable.button").on("click", function () {
        enableArticle($(this).data("id"));
    });

    $(".ui.admin.article.disable.button").on("click", function () {
        disableArticle($(this).data("id"));
    });
});
