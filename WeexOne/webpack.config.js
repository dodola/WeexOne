require('webpack')
require('weex-loader')

var path = require('path')
var fs=require('fs')

var entry={};
function walk(dir, root) {
  var directory = path.join(__dirname, root, dir)
  fs.readdirSync(directory)
    .forEach(function (file) {
      var fullpath = path.join(directory, file)
      var stat = fs.statSync(fullpath)

      if (stat.isFile() &&
        path.extname(fullpath) === '.we') {
        var name = path.join( dir, path.basename(file, '.we'))
        entry[name] = fullpath + '?entry=true'
      } else if (stat.isDirectory()) {
        var subdir = path.join(dir, file)
        walk(subdir, root)
      }
    })
}
walk('./', 'src')
module.exports = {
  entry: entry,
  output: {
    path: 'dist',
    filename: '[name].js'
  },
  devtool:'inline-source-map',
  module: {
    loaders: [
      {
        test: /\.we(\?[^?]+)?$/,
        loaders: ['weex-loader']
      }
    ]
  }
}
