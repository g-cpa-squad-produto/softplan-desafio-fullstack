const mongoose = require('mongoose');
mongoose.Promise = Promise;
const config = require('./config');

class Database {
    constructor() {
        this.config = config.db;
        this.timer = null;
    }

    connect() {
        return mongoose.connect(`mongodb://${this.config.host}:${this.config.port}/${this.config.name}`, this.config.options)
            .then(result => {
                result.connection.on('error', (error) => {
                    this.logger.error(error);
                });

                return result;
            }, (err) => {
                throw err;
            });
    }

    connectWithRetry() {
        return this.connect()
            .catch((err) => {
                this.timer = setTimeout(() => {
                    this.connectWithRetry()
                }, this.config.options.reconnectInterval);
            });
    }
}

module.exports = new Database();