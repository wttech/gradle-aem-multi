const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const devMode = process.env.NODE_ENV !== 'production'
const path = require('path');

const config = {
  sourceJs: __dirname + '/js/main.js',
  sourceScss: __dirname + '/styles/main.scss',
  distPath: __dirname + '/../dist',
  distJs: 'main.bundle.js',
  distCss: 'main-new.css'
};

module.exports = [
  {
    entry: [
      config.sourceJs
    ],
    output: {
      path: config.distPath,
      filename: config.distJs
    },
    entry: [
      config.sourceScss
    ],
    output: {
      path: config.distPath,
      filename: config.distCss
    },
    module: {
      rules: [{
          test: /\.scss$/,
          use: [
              "style-loader", // creates style nodes from JS strings
              "css-loader", // translates CSS into CommonJS
              "sass-loader" // compiles Sass to CSS, using Node Sass by default
          ]
      }]
    },
    plugins: [
      new MiniCssExtractPlugin({
        filename: "main-new.css"
      })
    ]
  }
];
