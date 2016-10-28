/**
 * 选择文章封面，供全局使用
 */
function selectCover(fileDom) {
    var file = fileDom.files[0];
    var imageType = /^image\//;
    if (!imageType.test(file.type)) {
        new Dialog("选择封面", "请选择正确的图片").warning();
        return;
    }

    if (window.FileReader) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var img = document.getElementById("cover-picture");
            img.src = e.target.result;
        };
        reader.readAsDataURL(file);
    } else {
        new Dialog("选择封面", "您的设部不支持该功能").error();
    }
}

function showDimmer() {
    $(".ui.dimmer").dimmer("show");
}

function hideDimmer() {
    $(".ui.dimmer").dimmer("hide");
}

function loadArticleContent(editor, id, onSuccess) {
    if (id) {
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
    }
}