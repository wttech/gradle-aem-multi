const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const devMode = process.env.NODE_ENV !== 'production'
const path = require('path');

const config = {
  sourceJs: __dirname + '/js/main.js',
  sourceScss: __dirname + '/styles/main.scss',
  distPath: __dirname + '/../dist'
};

module.exports = [
  {
    entry: [
      config.sourceJs
    ],
    output: {
      path: config.distPath,
    },
    entry: [
      config.sourceScss
    ],
    output: {
      path: config.distPath,
    },
    module: {
      rules: [{
        test: /\.s?css$/,
        use: [
          MiniCssExtractPlugin.loader,
          "css-loader",
          "sass-loader"
        ]
      }]
    },
    plugins: [
      new MiniCssExtractPlugin({
        filename: "[name].css",
        chunkFilename: "[id].css"
      })
    ]
  }
];
