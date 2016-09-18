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
        if (confirm("确定要移除角色吗？")) {
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
                        alert("移除角色成功");
                        location.reload();
                    } else {
                        console.error("移除角色失败");
                    }
                },
                error: function () {
                    console.error("移除角色失败");
                }
            });
        }
    };

    var addUserRole = function (role) {
        if (role) {
            var formData = new FormData();
            formData.append("username", $(".ui.admin.user.form input[name='username']").val());
            formData.append("role", role);
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());

            $.ajax({
                url: "/api/role/add",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    if (status) {
                        alert("添加角色成功");
                        location.reload();
                    } else {
                        console.error("添加角色失败");
                    }
                },
                error: function () {
                    console.error("添加角色失败");
                }
            });
        } else {
            alert("请选择角色");
        }
    };

    $(".ui.admin.user.role.add.modal .dropdown").dropdown();

    $(".ui.admin.user.role.add.modal").modal({
        closeable: false,
        onApprove: function () {
            addUserRole($(".ui.admin.user.role.add.modal .ui.dropdown").dropdown("get value"));
        },
        onDeny: function () {
            return true;
        }
    });

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

    $(".ui.admin.user.add.role.button").on("click", function () {
        $(".ui.admin.user.role.add.modal").modal("show");
    });
});