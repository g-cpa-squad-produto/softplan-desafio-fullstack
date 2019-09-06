module.exports = {
    devServer: {
        host: '0.0.0.0',
        port: 3001,
        proxy: {
            "/api/*": {
                target: "http://localhost:8080",
                secure: false
            }
        }
    }
}
