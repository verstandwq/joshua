module.exports = {
    devtool: "source-map",

    entry: {
        animation: "./app/page/animation.js",
        normal: "./app/page/normal.js",
        home: "./app/page/home.js",
        article: "./app/page/article.js",
        contact: "./app/page/contact.js",
        logon: "./app/page/logon.js",
        forget: "./app/page/forget.js",
        userinfo: "./app/page/user-info.js",
        adminHome: "./app/page/admin/admin-home.js",
        adminArticleTable: "./app/page/admin/admin-article-table.js",
        adminArticleReader: "./app/page/admin/admin-article-reader.js",
        adminArticleEditor: "./app/page/admin/admin-article-editor.js"
    },
    output: {
        path: "../public/assets/js",
        filename: "[name].js"
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel',
                query: {
                    presets: ["es2015"]
                }
            },
            {
                test: /\.css$/,
                loader: 'style!css'
            }
        ]
    }
};