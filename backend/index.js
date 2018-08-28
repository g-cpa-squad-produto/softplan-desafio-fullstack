const express = require('express');
const bodyParser = require('body-parser');
const config = require('./config');
const routes = require('./routes');
const db = require('./db');

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use(routes);

app.listen(config.port, function () {
    console.log(`Servidor rodando na porta ${config.port}`);
    db.connectWithRetry()
        .then(() => {
            console.log(`Conectado ao MongoDB`);
        })
})