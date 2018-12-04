const cssnano = require('cssnano');
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const HappyPack = require('happypack');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const HardSourceWebpackPlugin = require('hard-source-webpack-plugin');

module.exports = ({
  plugins: [
    new HappyPack({
      id: 'js',
      threads: 4,
      loaders: ['babel-loader'],
    }),
    new HardSourceWebpackPlugin(),
  ],
  optimization: {
    mangleWasmImports: true,
    minimizer: [
      new UglifyJsPlugin({
        sourceMap: true,
        uglifyOptions: {
          ecma: 6,
          mangle: true,
        },
      }),
      new OptimizeCssAssetsPlugin({
        assetNameRegExp: /\.css$/g,
        cssProcessor: cssnano,
        cssProcessorOptions: {
          discardComments: {
            removeAll: false,
          },
        },
        canPrint: true,
      }),
    ],
  },
});
