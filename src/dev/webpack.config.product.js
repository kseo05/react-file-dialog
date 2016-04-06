var path = require("path");
var webpack = require("webpack");
var ExtractTextPlugin = require("extract-text-webpack-plugin");

// es6 & css-loader 사용시 아래와 같은 이슈가 발생. 아래의 코드로 해결.
// https://github.com/webpack/css-loader/issues/145
require("es6-promise").polyfill();


module.exports = {
  "entry": [
    "./js/react-bootstrap-table.js",
    "./js/file-dialog.js"
  ],
  "output": {
    "path" : path.join(__dirname, "build"),
    "filename" : "react-file-dialog.js",
    "chunkFileName" : "react-file-dialog-chunk.js"
  },
  externals: {
    // Use external version of React, ReactDOM
    "react": "React",
    "react-dom": "ReactDOM"
  },
  plugins: [
    // https://webpack.github.io/docs/stylesheets.html
    new ExtractTextPlugin("react-file-dialog.css", { "allChunks" : true }),
    new webpack.optimize.UglifyJsPlugin({
      compressor : {
        warnings : true
      }
    }),
    new webpack.optimize.OccurenceOrderPlugin()
  ],
  "module" : {
    "loaders" : [
      {
        "test" : /\.css$/,
        "loader" : ExtractTextPlugin.extract("style-loader", "css-loader")
      },
      {
        "test" : /\.js$/,
        "loader" : "babel-loader",
        "query" : {
          // "plugins" : ["transform-runtime"], // TODO
          "presets" : ["es2015", "react"]
        }
      }
    ]
  }
}
