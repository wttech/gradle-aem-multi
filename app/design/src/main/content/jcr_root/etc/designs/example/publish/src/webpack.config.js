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
                                loader: "css-loader"
                            },
                            {
                                loader: "postcss-loader",
                                options: {
                                    plugins: function () {
                                        return [
                                            require("autoprefixer")
                                        ];
                                    }
                                }
                            },
                            {
                                loader: "sass-loader"
                            }
                        ]
                    })
                }
            ]
        },
        plugins: [
            new ExtractTextPlugin({filename: '[name].css', allChunks: true})
        ]
    }
];