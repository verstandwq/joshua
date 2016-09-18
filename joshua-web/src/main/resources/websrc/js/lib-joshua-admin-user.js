/**
 * 后台用户管理操作
 */

$(document).ready(function () {

    var performUserOperation = function (url, text, username) {
        if (confirm("确定要" + text + "用户 " + username + " 吗？")) {
            var formData = new FormData();
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
            $.ajax({
                url: "/api/user/" + url + "/" + username,
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
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
            });
        }
    };

    var removeUserRole = function (username, role) {
        if (confirm("确定要角色吗？")) {
            var formData = new FormData();
            formData.append("username", username);
            formData.append("role", role);
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());

            $.ajax({
                url: "/api/role/remove",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    if (status) {
                        alert("删除角色成功");
                        location.reload();
                    } else {
                        console.error("删除角色失败");
                    }
                },
                error: function () {
                    console.error("删除角色失败");
                }
            });
        }
    };

    $(".ui.admin.user.lock").on("click", function () {
        performUserOperation("lock", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.unlock").on("click", function () {
        performUserOperation("unlock", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.disable").on("click", function () {
        performUserOperation("disable", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.enable").on("click", function () {
        performUserOperation("enable", $(this).text().trim(), $(this).data("username"));
    });

    $(".ui.admin.user.remove.role").on("click", function () {
        removeUserRole($(this).data("username"), $(this).data("role"));
    });
});