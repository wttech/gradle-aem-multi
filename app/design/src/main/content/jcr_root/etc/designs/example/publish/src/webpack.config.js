const ExtractTextPlugin = require('extract-text-webpack-plugin');
const StyleLintPlugin = require('stylelint-webpack-plugin');

const config = {
  sourceJs: __dirname + '/js/main.js',
  sourceScss: __dirname + '/css/main.scss',
  distPath: __dirname + '/../dist',
  distJs: 'main.bundle.js',
  distCss: 'main.css'
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
    module: {
      rules: [
        {
          test: /\.js$/,
          use: [
            {
              loader: 'babel-loader',
              options: {
                presets: ['env']
              }
            },
          ]
        },
        {
          test: /\.js$/,
          use: [
            {
              loader: 'eslint-loader',
            },
          ]
        },
      ]
    }
  },
  {
    entry: [
      config.sourceScss
    ],
    output: {
      path: config.distPath,
      filename: config.distCss
    },
    module: {
      rules: [
        {
          test: /\.scss$/,
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
                      require("autoprefixer"),
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
      new ExtractTextPlugin({filename: '[name].css', allChunks: true}),
      new StyleLintPlugin(),
    ]
  }
];
