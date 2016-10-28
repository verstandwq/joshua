/*
 * 文章编辑器组件
 * */

var QuillEditor = {
    placeholder: "请输入文章内容，文章大小最多为20M，超过以后会保存失败",
    theme: "snow",

    toolbar: [
        /* 基本控件 */
        [{'header': [1, 2, 3, 4, 5, 6, false]}],

        /* 文字样式控件 */
        ['bold', 'italic', 'underline', 'strike'],
        [{'color': []}, {'background': []}],

        /* 文字对齐控件 */
        [{'align': []}],
        [{'list': 'ordered'}, {'list': 'bullet'}],
        [{'indent': '-1'}, {'indent': '+1'}],

        /* 其他控件 */
        ['link', 'image'],
        [{'script': 'sub'}, {'script': 'super'}],
        ['clean']
    ],

    buttons: {
        save: {
            selector: ".article-editor .ui.save.button",
            handler: function (quill) {
                var id = $("#article-id").val();
                var title = $(".article-editor .input.title").val();
                var fellowship = $(".article-editor .dropdown.fellowship").dropdown('get value');

                if (!title) {
                    new Dialog("保存文章", "文章标题不能为空").message();
                    return;
                }

                if (!fellowship) {
                    new Dialog("保存文章", "文章所属团契不能为空").message();
                    return;
                }

                if (quill.getLength() == 1) {
                    new Dialog("保存文章", "文章内容不能为空").message();
                    return;
                }

                showDimmer();

                var formData = new FormData();
                formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
                formData.append("id", id ? id : -1);
                formData.append("file", $("#file")[0].files[0]);
                formData.append("title", title);
                formData.append("fellowship", fellowship);
                formData.append("description", $(".article-editor .input.description").val());
                formData.append("content",
                    JSON.stringify(quill.getContents())
                        .replace(/\n/g, "\\n")
                );

                $.ajax({
                    url: "/api/article/save",
                    type: "post",
                    data: formData,
                    processData: false,
                    contentType: false,
                    xhr: function () {
                        var myXhr = $.ajaxSettings.xhr();

                        if (myXhr.upload) {
                            myXhr.upload.addEventListener("progress", function (e) {
                                if (e.lengthComputable) {
                                    console.log("上传数据：" + e.loaded + " - " + e.total);
                                }
                            }, false);
                        }

                        return myXhr;
                    },
                    success: function (status) {
                        if (parseInt(status)) {
                            window.location = '/admin/article/' + status + '/edit';
                        } else {
                            new Dialog("保存文章", "保存失败，原因：" + status, function () {
                                hideDimmer();
                            }).error();
                        }
                    },
                    error: function () {
                        new Dialog("保存文章", "保存失败", function () {
                            hideDimmer();
                        }).error();
                    }
                });
            }
        },
        audit: {
            selector: ".article-editor .ui.audit.button",
            handler: function () {
                new Dialog("申请发布", "确定要申请发布文章吗？， 申请发布后将不能再修改文章内容，如果文章被驳回，则可以修改以后继续发布", function () {
                    showDimmer();

                    var formData = new FormData();
                    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
                    formData.append("id", $("#article-id").val());

                    $.ajax({
                        url: "/api/article/audit",
                        type: "post",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (status) {
                            if ("success" == status) {
                                window.location.reload();
                            } else {
                                new Dialog("申请发布", "申请发布失败，原因:" + status, function () {
                                    hideDimmer();
                                }).error();
                            }
                        },
                        error: function () {
                            new Dialog("申请发布", "申请发布失败", function () {
                                hideDimmer();
                            }).error();
                        }
                    });
                }).confirm();
            }
        },
        delete: {
            selector: ".article-editor .ui.delete.button",
            handler: function (id) {
                var formData = new FormData();
                formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
                formData.append("id", id);

                $.ajax({
                    url: "/api/article/delete",
                    type: "post",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (status) {
                        if (status == "success") {
                            new Dialog("删除文章", "删除文章成功", function () {
                                window.location = '/admin/article';
                            }).message();
                        } else {
                            new Dialog("删除文章", "删除文章失败，原因：" + status, function () {
                                hideDimmer();
                            }).error();
                        }
                    },
                    error: function () {
                        new Dialog("删除文章", "删除文章失败", function () {
                            hideDimmer();
                        }).error();
                    }
                });
            }
        }
    }
};

$(document).ready(function () {
    if ($(".article-editor .container").length > 0) {
        var quill = new Quill(".article-editor .container", {
            placeholder: QuillEditor.placeholder,
            modules: {
                toolbar: QuillEditor.toolbar
            },
            theme: QuillEditor.theme
        });

        loadArticleContent(quill, $("#article-id").val());

        $(QuillEditor.buttons.save.selector).on("click", function () {
            QuillEditor.buttons.save.handler(quill);
        });

        $(QuillEditor.buttons.delete.selector).on("click", function () {
            QuillEditor.buttons.delete.handler($("#article-id").val());
        });

        $(QuillEditor.buttons.audit.selector).on("click", function () {
            QuillEditor.buttons.audit.handler();
        });

        $(".article-editor .ui.fellowship.dropdown").dropdown();
    }
});