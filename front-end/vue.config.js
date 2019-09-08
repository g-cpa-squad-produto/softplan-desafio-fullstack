module.exports = {
    devServer: {
        host: '0.0.0.0',
        port: 3001,
        disableHostCheck: true,
        proxy: {
            "/api/*": {
                target: process.env.SOFTPLAN_BACKEND_URL,
                secure: false
            }
        }
    }
}
