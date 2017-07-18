const Webpack = require('webpack');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = [
    {
        entry: [
            __dirname + '/js/main.js',
        ],
        output: {
            path: __dirname + '/../dist',
            filename: 'main.bundle.js'
        },
        plugins: [
            new Webpack.optimize.UglifyJsPlugin(),
        ]
    },
    {
        entry: [
            __dirname + '/css/main.scss',
        ],
        output: {
            path: __dirname + '/../dist',
            filename: 'main.css'
        },
        module: {
            rules: [
                {
                    test: /(\.css|\.scss)$/,
                    use: ExtractTextPlugin.extract({
                        fallback: "style-loader",
                        use: [
                            {
                                loader: "css-loader",
                                options: {
                                    sourceMap: true,
                                    modules: true,
                                    importLoaders: true,
                                    localIdentName: "[name]__[local]___[hash:base64:5]"
                                }
                            },
                            // {
                            //     loader: "postcss-loader",
                            //     options: {
                            //         plugins: function () {
                            //             return [
                            //                 require("autoprefixer")
                            //             ];
                            //         }
                            //     }
                            // },
                            {
                                loader: "sass-loader",
                                options: {
                                    sourceMap: true
                                }
                            }
                        ]
                    })
                }
            ]
        },
        plugins: [
            new ExtractTextPlugin('[name].css')
        ]
    }
];