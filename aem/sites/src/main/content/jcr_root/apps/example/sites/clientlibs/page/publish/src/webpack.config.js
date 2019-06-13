const path = require('path');
const { isProduction } = require('webpack-mode');
const merge = require('webpack-merge');
const modules = require('./webpack/index');
const glob = require('glob');

const entries = [
  ...glob.sync('./js/**/*.js'),
  ...glob.sync('./styles/**/*.scss'),
];

const config = {
  js: path.join(__dirname, 'js'),
  sass: path.join(__dirname, 'styles'),
  startPath: '',
  devServerPort: '3000'
};

const common = {
  devtool: isProduction ? 'none' : 'source-map',
  entry: entries,
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(__dirname, '../dist'),
  },
};

/* eslint-disable comma-dangle */

module.exports = merge(
  common,
  modules.production,
  modules.styles(config.sass, isProduction),
  modules.babel(config.js, isProduction)
);

/* eslint-enable */
