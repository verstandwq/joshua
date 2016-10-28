module.exports = {
    devtool: "source-map",

    entry: {
        normal: "./app/page/normal.js",
        home: "./app/page/home.js",
        animation: "./app/page/animation.js",
        article: "./app/page/article.js"
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