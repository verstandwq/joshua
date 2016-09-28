$(document).ready(function () {

    $(".ui.user.password.submit.button").on("click", function () {
        var newPassword = $("#new-password").val();
        var confirmPassword = $("#confirm-password").val();

        if (newPassword.length < 8) {
            new Dialog("修改密码", "新的密码长度不能少于8位").warning();
            return;
        }

        if (newPassword != confirmPassword) {
            new Dialog("修改密码", "两次密码输入不一致").warning();
            return;
        }

        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("password", newPassword);

        $.ajax({
            url: "/api/user/password",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if ("success" == status) {
                    new Dialog("修改密码", "修改密码成功", function () {
                        window.location.reload();
                    }).message();
                } else {
                    new Dialog("修改密码", "修改密码失败，原因:" + status, function () {
                    }).error();
                }
            },
            error: function () {
                new Dialog("修改密码", "修改密码失败", function () {
                }).error();
            }
        });

    });
});