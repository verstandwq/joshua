/**
 * Created by Administrator on 16-9-24.
 */

var enableFellowship = function (name) {
    new Dialog("激活团契", "激活团契后团契和团契所属的文章会重新在网站显示，确认要激活吗？", function () {
        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("name", name);

        $.ajax({
            url: "/api/fellowship/enable",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if ("success" == status) {
                    window.location.reload();
                } else {
                    new Dialog("激活团契", "激活失败，原因：" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("激活团契", "激活失败", function () {
                }).error();
            }
        });
    }).confirm();
};

var disableFellowship = function (name) {
    new Dialog("禁用团契", "禁用团契后团契和团契所属的文章将不会在网站显示，确认要禁用吗？", function () {
        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("name", name);

        $.ajax({
            url: "/api/fellowship/disable",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if ("success" == status) {
                    window.location.reload();
                } else {
                    new Dialog("禁用团契", "禁用失败，原因：" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("禁用团契", "禁用失败", function () {
                }).error();
            }
        });
    }).confirm();
};

var transferFellowship = function (name, username) {
    var formData = new FormData();
    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
    formData.append("name", name);
    formData.append("username", username);

    $.ajax({
        url: "/api/fellowship/transfer",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        success: function (status) {
            if ("success" == status) {
                window.location.reload();
            } else {
                new Dialog("转移团契", "转移失败，原因：" + status, function () {
                }).error();
            }
        },
        error: function () {
            new Dialog("转移团契", "转移失败", function () {
            }).error();
        }
    });
};

var addAdminFellowship = function (name, username) {
    var formData = new FormData();
    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
    formData.append("name", name);
    formData.append("username", username);

    $.ajax({
        url: "/api/fellowship/add",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        success: function (status) {
            if ("success" == status) {
                window.location.reload();
            } else {
                new Dialog("添加管理员", "添加管理员失败，原因：" + status, function () {
                }).error();
            }
        },
        error: function () {
            new Dialog("添加管理员", "添加管理员失败", function () {
            }).error();
        }
    });
};

var removeAdminFellowship = function (name, username) {
    new Dialog("移除管理员", "确定要移除管理员" + username + "?", function () {
        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("name", name);
        formData.append("username", username);

        $.ajax({
            url: "/api/fellowship/remove",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if ("success" == status) {
                    window.location.reload();
                } else {
                    new Dialog("移除管理员", "移除管理员失败，原因：" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("移除管理员", "移除管理员失败", function () {
                }).error();
            }
        });
    }).confirm();
};

$(document).ready(function () {
    $(".ui.admin.fellowship.enable.button").on("click", function () {
        enableFellowship($(this).data("name"));
    });

    $(".ui.admin.fellowship.disable.button").on("click", function () {
        disableFellowship($(this).data("name"));
    });

    $(".ui.admin.fellowship.remove.button").on("click", function () {
        removeAdminFellowship($("#fellowship-id").text().trim(), $(this).data("username"));
    });

    $(".ui.admin.fellowship.transfer.owner.button").on("click", function () {
        $("#owner-username").val("");
        $(".ui.admin.fellowship.transfer.modal").modal("show");
    });

    $(".ui.admin.fellowship.add.button").on("click", function () {
        $("#admin-username").val("");
        $(".ui.admin.fellowship.add.modal").modal("show");
    });

    $(".ui.admin.fellowship.transfer.modal").modal({
        closeable: false,
        onApprove: function () {
            var input = $("#owner-username");
            transferFellowship($("#fellowship-id").text().trim(), input.val());
        },
        onDeny: function () {
            return true;
        }
    });

    $(".ui.admin.fellowship.add.modal").modal({
        closeable: false,
        onApprove: function () {
            var input = $("#admin-username");
            addAdminFellowship($("#fellowship-id").text().trim(), input.val());
        },
        onDeny: function () {
            return true;
        }
    });
});