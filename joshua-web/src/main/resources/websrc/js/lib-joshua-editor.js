$(document).ready(function () {

    /**
     * 工具条定义
     */
    var toolbarOptions = [

        /* 基本控件 */
        [{'header': [1, 2, 3, 4, 5, 6, false]}],
        [{'font': ['Microsoft YaHei', 'Sans Serif', 'Serif', 'Monospace']}],

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

    var quill = new Quill(".article-editor", {
        placeholder: '请输入文章内容',
        modules: {
            toolbar: toolbarOptions
        },
        theme: 'snow'
    });
});