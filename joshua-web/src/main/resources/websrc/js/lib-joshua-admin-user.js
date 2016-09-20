/**
 * 后台用户管理操作
 */

$(document).ready(function () {

    var performUserOperation = function (url, text, username) {
        new Dialog(text + "用户", "确定要" + text + "用户 " + username + " 吗？", function () {
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
                        new Dialog("消息", text + "用户成功", function () {
                            location.reload();
                        }).message();
                    } else {
                        new Dialog("消息", text + "用户失败").error();
                    }
                },
                error: function () {
                    new Dialog("消息", text + "用户失败").error();
                }
            });
        }).confirm();
    };

    var removeUserRole = function (username, role) {
        new Dialog("移除角色", "确定要移除角色吗？", function () {
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
                        new Dialog("移除角色", "移除角色成功", function () {
                            location.reload();
                        }).message();
                    } else {
                        new Dialog("移除角色", "移除角色失败").error();
                    }
                },
                error: function () {
                    new Dialog("移除角色", "移除角色失败").error();
                }
            });
        }).confirm();
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
                        new Dialog("添加角色", "添加角色成功", function () {
                            location.reload();
                        }).message();
                    } else {
                        new Dialog("添加角色", "添加角色失败").error();
                    }
                },
                error: function () {
                    new Dialog("添加角色", "添加角色失败").error();
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