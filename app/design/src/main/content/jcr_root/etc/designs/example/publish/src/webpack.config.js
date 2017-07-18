const webpack = require('webpack');
const path = require('path');

module.exports = {
    entry: './js/main.js',
    output: {
        path: './../dist',
        filename: 'main.bundle.js'
    },
    plugins: [
        new webpack.optimize.UglifyJsPlugin()
    ]
};