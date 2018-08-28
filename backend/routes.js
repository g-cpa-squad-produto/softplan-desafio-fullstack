const express = require('express');
const router = express.Router();

const accountRoutes = require('./modules/account/routes');

router.get('/healthcheck', (req, res, next) => {
    res.status(200).send('Recebido');
});

router.use((err, req, res, next) => {
    console.log(err);
    res.status(err.statusCode || 500).end();
})

router.use('/account', accountRoutes);

module.exports = router;