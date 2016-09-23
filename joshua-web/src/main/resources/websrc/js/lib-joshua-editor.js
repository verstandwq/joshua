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
        [{'font': []}],

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

    if ($(".article-editor .container").length > 0) {
        var quill = new Quill(".article-editor .container", {
            placeholder: '请输入文章内容',
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

    $(".article-editor .ui.save.button").on("click", function () {
        var formData = new FormData();
        formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
        formData.append("id", $(".article-editor .input.id").val());
        formData.append("title", $(".article-editor .input.title").val());
        formData.append("fellowship", $(".article-editor .input.fellowship").val());
        formData.append("description", $(".article-editor .input.description").val());
        formData.append("content",
            JSON.stringify(quill.getContents())
                .replace(/\n/g, "\\n")
        );

        $.ajax({
            url: "/admin/article/save",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (status) {
                if (status) {
                    new Dialog("保存文章", "保存成功").message();
                } else {
                    new Dialog("保存文章", "保存失败").error();
                }
            },
            error: function () {
                new Dialog("保存文章", "保存失败").error();
            }
        });
    });
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
                    if (status) {
                        new Dialog("发布文章", "发布成功").message();
                    } else {
                        new Dialog("发布文章", "发布失败").error();
                    }
                },
                error: function () {
                    new Dialog("发布文章", "发布失败").error();
                }
            });
        }).confirm();
    });

    $(".article-audit .reject.button").on("click", function () {
        new Dialog("驳回文章", "确定要驳回文章？", function () {
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
                    if (status) {
                        new Dialog("驳回文章", "驳回成功").message();
                    } else {
                        new Dialog("驳回文章", "驳回失败").error();
                    }
                },
                error: function () {
                    new Dialog("驳回文章", "驳回失败").error();
                }
            });
        }).confirm();
    });
});