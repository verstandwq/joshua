/*
 * 文章阅读器组件
 * */
var QuillReader = {
    theme: "snow",
    onLoad: function () {
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
    }
};

$(document).ready(function () {
    if ($(".article-reader .container").length > 0) {
        var quill = new Quill(".article-reader .container", {
            modules: {
                toolbar: false
            },
            theme: QuillReader.theme,
            readOnly: true
        });

        loadArticleContent(quill, $("#article-id").val(), QuillReader.onLoad);
    }
});
