/*
 * 文章审核器组件
 * */
var QuillAuditor = {
    theme: "snow",
    buttons: {
        publish: {
            selector: ".article-audit .publish.button",
            handler: function (id) {
                new Dialog("发布文章", "确定要发布文章？ 文章发布以后即可在网站访问", function () {
                    showDimmer();
                    var formData = new FormData();
                    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
                    formData.append("id", id);

                    $.ajax({
                        url: "/api/article/publish",
                        type: "post",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (status) {
                            if ("success" == status) {
                                window.location.reload();
                            } else {
                                new Dialog("发布文章", "发布失败，原因:" + status, function () {
                                    hideDimmer();
                                }).error();
                            }
                        },
                        error: function () {
                            new Dialog("发布文章", "发布失败", function () {
                                hideDimmer();
                            }).error();
                        }
                    });
                }).confirm();
            }
        },
        reject: {
            selector: ".article-audit .reject.button",
            handler: function (id) {
                new Dialog("驳回文章", "确定要驳回文章？", function () {
                    showDimmer();
                    var formData = new FormData();
                    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
                    formData.append("id", id);

                    $.ajax({
                        url: "/api/article/reject",
                        type: "post",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (status) {
                            if ("success" == status) {
                                window.location.reload();
                            } else {
                                new Dialog("驳回文章", "驳回失败，原因:" + status, function () {
                                    hideDimmer();
                                }).error();
                            }
                        },
                        error: function () {
                            new Dialog("驳回文章", "驳回失败", function () {
                                hideDimmer();
                            }).error();
                        }
                    });
                }).confirm();
            }
        }
    }
};

$(document).ready(function () {
    if ($(".article-audit .container").length > 0) {
        var quill = new Quill(".article-audit .container", {
            modules: {
                toolbar: false
            },
            theme: QuillAuditor.theme,
            readOnly: true
        });

        var id = $("#article-id").val();

        loadArticleContent(quill, id);

        $(QuillAuditor.buttons.publish.selector).on("click", function () {
            QuillAuditor.buttons.publish.handler(id);
        });

        $(QuillAuditor.buttons.reject.selector).on("click", function () {
            QuillAuditor.buttons.reject.handler(id);
        });
    }
});
