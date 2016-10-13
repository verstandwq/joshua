/**
 * 静态页面首页管理操作
 */

$(document).ready(function () {
    $(".ui.add.slide.picture.button").on("click", function () {
        var file = $("#file")[0].files[0];

        if (file) {
            var formData = new FormData();
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
            formData.append("file", file);

            $.ajax({
                url: "/api/static/gallery",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    if (status == "success") {
                        window.location.reload();
                    } else {
                        new Dialog("上传大图", "上传大图失败，原因：" + status, function () {
                        }).error();
                    }
                },
                error: function () {
                    new Dialog("上传大图", "上传大图失败", function () {
                    }).error();
                }
            });
        } else {
            new Dialog("上传大图", "请选择要上传的图片").message();
        }
    });

    $(".ui.delete.slide.picture.button").on("click", function () {
        var filename = $(this).data("filename");

        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("filename", filename);

        $.ajax({
            url: "/api/static/gallery/delete",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if (status == "success") {
                    window.location.reload();
                } else {
                    new Dialog("删除大图", "删除大图失败，原因：" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("删除大图", "删除大图失败", function () {
                }).error();
            }
        });
    })
});