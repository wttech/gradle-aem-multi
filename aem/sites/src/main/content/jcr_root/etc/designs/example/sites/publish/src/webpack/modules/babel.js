module.exports = (source, isProduction) => ({
  module: {
    rules: [{
      test: /\.js?$/,
      include: source,
      exclude: /node_modules/,
      use: [{
        loader: isProduction ? 'happypack/loader?id=js' : 'babel-loader',
      }],
    }],
  },
});
