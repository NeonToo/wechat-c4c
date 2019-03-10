const path = require('path');
const rootPath = path.resolve(__dirname, './');

module.exports = function(env) {
    console.log(`The current environment is ${env}`);
    return require(`${rootPath}/build/webpack.${env}.js`);
};
// const path = require("path");
// const webpack = require("webpack");
//
// module.exports = {
//     devtool: "source-map",
//     entry: "./modules/index.js",
//     output: {
//         path: path.resolve(__dirname, 'dist'),
//         publicPath: "dist/",
//         filename: "[name].js"
//     },
//     resolve: {
//         extensions: ['.js', '.json', '.css', '.less', '.vue', '.ts', '.tsx'],
//         alias: {
//             'vue$': 'vue/dist/vue.common.js'
//         }
//     },
//     devServer: {
//         hot: true,
//         contentBase: path.resolve(__dirname, 'dist'),
//         compress: true,
//         port: 9000,
//         hotOnly: true,
//         inline: false,
//         proxy: {
//             "/": "http://localhost:9001"
//         }
//     },
//     watchOptions: {
//     	aggregateTimeout: 10,
//         ignored: /node_modules/
//     },
//     module: {
//         noParse: /node_modules\/(jquey)/,
//         rules: [{
//             test: /\.css$/,
//             use: ["style-loader", "css-loader"]
//         }, {
//             test: /\.js$/,
//             loader: "babel-loader",
//             exclude: /node_modules/,
//             query: {
//                 presets: ["es2015", "vue-app"]
//             }
//         }, {
//             test: /\.(png|jpg|jpeg)$/,
//             loader: "url-loader",
//             query: {
//                 limit: 10000
//             }
//         }, {
//             test: /\.vue$/,
//             loader: "vue-loader"
//         }]
//     },
//     plugins: [
//         new webpack.HotModuleReplacementPlugin(),
//         new webpack.LoaderOptionsPlugin({
//         	debug: true
//         }),
//         new webpack.ProvidePlugin({
//             _: 'lodash',
//             $: 'jquery',
//             jQuery: 'jquery'
//         })
//     ]
// };