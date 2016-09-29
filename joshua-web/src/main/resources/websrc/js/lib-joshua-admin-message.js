var markMessageAsRead = function (id) {
    var formData = new FormData();
    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
    formData.append("id", id);

    $.ajax({
        url: "/message/read",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        success: function (status) {
            if ("success" == status) {
                window.location.reload();
            } else {
                new Dialog("操作失败", "操作失败，原因：" + status, function () {
                }).error();
            }
        },
        error: function () {
            new Dialog("操作失败", "操作失败", function () {
            }).error();
        }
    });
};

var markMessageAsUnRead = function (id) {
    var formData = new FormData();
    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
    formData.append("id", id);

    $.ajax({
        url: "/message/unread",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        success: function (status) {
            if ("success" == status) {
                window.location.reload();
            } else {
                new Dialog("操作失败", "操作失败，原因：" + status, function () {
                }).error();
            }
        },
        error: function () {
            new Dialog("操作失败", "操作失败", function () {
            }).error();
        }
    });
};

$(document).ready(function () {
    $(".ui.admin.mark.read.button").on("click", function () {
        markMessageAsRead($(this).data("id"));
    });
    
    $(".ui.admin.mark.unread.button").on("click", function () {
        markMessageAsUnRead($(this).data("id"));
    });
});







