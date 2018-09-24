const stylelint = require('stylelint');
const reporter = require('postcss-reporter');
const autoprefixer = require('autoprefixer');
const focus = require('postcss-focus');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const prodConfig = source => ({
  module: {
    rules: [
      {
        test: /\.scss$/,
        include: source,
        use: [
          MiniCssExtractPlugin.loader,
          'css-loader',
          {
            loader: 'postcss-loader',
            options: {
              plugins: () => [
                autoprefixer,
                stylelint,
                focus,
                reporter({ clearReportedMessages: true }),
              ],
            },
          },
          {
            loader: 'sass-loader',
          }
        ],
      },
    ],
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: '[name].css',
    }),
  ],
});

const devConfig = source => ({
  module: {
    rules: [
      {
        test: /\.scss$/,
        include: source,
        use: [
          'style-loader',
          'css-loader',
          {
            loader: 'postcss-loader',
            options: {
              plugins: () => [
                autoprefixer,
                stylelint,
                focus,
                reporter({ clearReportedMessages: true }),
              ],
            },
          },
          {
            loader: 'sass-loader',
          }
        ]
      },
    ],
  }
});

module.exports = (source, isProduction) => isProduction ? prodConfig(source) : devConfig(source);
