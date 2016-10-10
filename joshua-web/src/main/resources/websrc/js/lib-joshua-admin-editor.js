/**
 * 公共函数定义
 */

var showDimmer = function () {
    $(".ui.dimmer").dimmer("show");
};

var hideDimmer = function () {
    $(".ui.dimmer").dimmer("hide");
};

var saveArticle = function (quill) {
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

    showDimmer();

    var formData = new FormData();
    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
    formData.append("id", $(".article-editor .input.id").val());
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
        success: function (status) {
            if (status) {
                hideDimmer();
                new Dialog("保存文章", "保存成功", function () {
                    window.location = '/admin/article/' + status + '/edit';
                }).message();
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
};

/**
 * 文章编辑器
 */
$(document).ready(function () {

    /**
     * 工具条定义
     */
    var toolbarOptions = [

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
    ];

    /**
     * 初始化编辑器
     */
    if ($(".article-editor .container").length > 0) {
        var quill = new Quill(".article-editor .container", {
            placeholder: '请输入文章内容，文章大小最多为20M，超过以后会保存失败',
            modules: {
                toolbar: toolbarOptions
            },
            theme: 'snow'
        });

        var content = $(".article-editor .article-content").text();

        if (content) {
            var delta = JSON.parse(content);
            quill.setContents(delta.ops);
        }
    }

    /**
     * 保存文章
     */
    $(".article-editor .ui.save.button").on("click", function () {
        saveArticle(quill);
    });

    /**
     * 申请发布文章
     */
    $(".article-editor .ui.audit.button").on("click", function () {

        new Dialog("申请发布", "确定要申请发布文章吗？， 申请发布后将不能再修改文章内容，如果文章被驳回，则可以修改以后继续发布", function () {
            showDimmer();

            var formData = new FormData();
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
            formData.append("id", $(".article-editor .input.id").val());

            $.ajax({
                url: "/api/article/audit",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    if ("success" == status) {
                        new Dialog("申请发布", "申请发布成功", function () {
                            hideDimmer();
                            window.location.reload();
                        }).message();
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
    });

    /**
     * 初始化下拉菜单
     */
    $(".article-editor .ui.fellowship.dropdown").dropdown();
});

/**
 * 文章阅读器
 */
$(document).ready(function () {

    if ($(".article-reader .container").length > 0) {
        var quill = new Quill(".article-reader .container", {
            modules: {
                toolbar: false
            },
            theme: 'snow',
            readOnly: true
        });

        var delta = JSON.parse($(".article-reader .article-content").text());
        quill.setContents(delta.ops);
    }
});

/**
 * 文章审核器
 */
$(document).ready(function () {

    if ($(".article-audit .container").length > 0) {
        var quill = new Quill(".article-audit .container", {
            modules: {
                toolbar: false
            },
            theme: 'snow',
            readOnly: true
        });

        var delta = JSON.parse($(".article-audit .article-content").text());
        quill.setContents(delta.ops);
    }

    $(".article-audit .publish.button").on("click", function () {
        new Dialog("发布文章", "确定要发布文章？ 文章发布以后即可在网站访问", function () {
            showDimmer();
            var formData = new FormData();
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
            formData.append("id", $(".article-audit .input.id").val());

            $.ajax({
                url: "/api/article/publish",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    if ("success" == status) {
                        new Dialog("发布文章", "发布成功", function () {
                            hideDimmer();
                            window.location.reload();
                        }).message();
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
    });

    $(".article-audit .reject.button").on("click", function () {
        new Dialog("驳回文章", "确定要驳回文章？", function () {
            showDimmer();
            var formData = new FormData();
            formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
            formData.append("id", $(".article-audit .input.id").val());

            $.ajax({
                url: "/api/article/reject",
                type: "post",
                data: formData,
                processData: false,
                contentType: false,
                success: function (status) {
                    if ("success" == status) {
                        new Dialog("驳回文章", "驳回成功", function () {
                            hideDimmer();
                            window.location.reload();
                        }).message();
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
    });
});