const config = {
    port: 8000,
    db: {
        host: "localhost",
        port: 27017,
        name: "softplan",
        options: {
            autoReconnect: true,
            reconnectTries: 10000,
            reconnectInterval: 1000
        }
    },
    passwordSalt: 'softplan'
};

module.exports = config;