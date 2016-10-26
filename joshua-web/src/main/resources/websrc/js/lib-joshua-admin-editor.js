/**
 * 公共函数定义
 */

/**
 * 选择文章封面
 */

function selectCover(fileDom) {
    if (window.FileReader) {
        var reader = new FileReader();

        var file = fileDom.files[0];
        var imageType = /^image\//;
        if (!imageType.test(file.type)) {
            new Dialog("选择封面", "请选择图片").warning();
            return;
        }
        reader.onload = function (e) {
            var img = document.getElementById("cover-picture");
            img.src = e.target.result;
        };
        reader.readAsDataURL(file);
    } else {
        new Dialog("选择封面", "您的设部不支持该功能").error();
    }
}

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

    if (quill.getLength() == 1) {
        new Dialog("保存文章", "文章内容不能为空").message();
        return;
    }

    showDimmer();

    var formData = new FormData();
    formData.append("_csrf", $(".ui.admin.user.form input[name='_csrf']").val());
    formData.append("id", $(".article-editor .input.id").val());
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
};

var loadArticleContent = function (editor, id, onSuccess) {
    $.ajax({
        url: "/article/content/" + id,
        type: "get",
        processData: false,
        contentType: false,
        success: function (content) {
            if (content) {
                var articleContent = JSON.parse(content);
                editor.setContents(articleContent);

                if (onSuccess) {
                    onSuccess.apply(editor);
                }
            }
        }
    });
};


var deleteArticle = function (id) {
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

        var id = $(".article-editor .article-content").text();
        loadArticleContent(quill, id);
    }

    /**
     * 保存文章
     */
    $(".article-editor .ui.save.button").on("click", function () {
        saveArticle(quill);
    });


    /**
     * 删除文章
     */
    $(".article-editor .ui.delete.button").on("click", function () {
        var id = $(this).data("id");
        new Dialog("删除文章", "确定要删除该文章吗？", function () {
            deleteArticle(id);
        }).confirm();
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

        var id = JSON.parse($(".article-reader .article-content").text());
        loadArticleContent(quill, id, function () {
            var reader = $(".article-reader");

            reader.find("img").each(function () {
                var imgSrc = $(this).attr("src");
                $(this).attr("data-src", imgSrc);
            });

            reader.lightGallery({
                selector: 'img'
            });

            reader.removeAttr("style");
            $(".article-reader + .ui.dimmer").removeClass("active");
        });
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
    });
});