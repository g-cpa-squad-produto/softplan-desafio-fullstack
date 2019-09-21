module.exports = {
    devServer: {
        open: process.platform === 'darwin',
        host: '0.0.0.0',
        port: 8080,
        https: false,
        hotOnly: false,
        proxy: {
            "api/*": {
                target: "http://localhost:8081",
                secure: false
            }
        }
    },

    transpileDependencies: [
        /\bvue-awesome\b/
    ]
}
