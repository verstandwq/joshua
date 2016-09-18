/**
 * 后台用户管理操作
 */

$(document).ready(function () {

    var performOperation = function (url, text, username) {
        if (confirm("确定要" + text + "用户 " + username + " 吗？")) {
            var formData = new FormData();
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
            $.ajax({
                url: "/api/user/" + url + "/" + username,
                type: "post",
                data: formData,
                success: function (status) {
                    if (status) {
                        alert(text + "用户成功");
                        location.reload();
                    } else {
                        console.error(text + "用户失败");
                    }
                },
                error: function () {
                    console.error(text + "用户失败");
                }
            })
        }
    };

    $(".ui.admin.user.lock").on("click", function () {
        performOperation("lock", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.unlock").on("click", function () {
        performOperation("unlock", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.disable").on("click", function () {
        performOperation("disable", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.enable").on("click", function () {
        performOperation("enable", $(this).text().trim(), $(this).data("username"));
    });
});