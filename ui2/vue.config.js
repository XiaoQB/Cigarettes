module.exports = {
  // 配置跨域代理
  devServer: {
    proxy: {
      '/api': { // 匹配所有以 '/api'开头的请求路径
        target: 'http://127.0.0.1:8888', // 代理目标的基础路径
        changeOrigin: true, // 支持跨域
        ws: true,
        pathRewrite: {// 重写路径: 去掉路径中开头的'/api'
          '^/api': ''
        }
      }
    },
  }
}