const express = require('express');
const controller = require('./controller');
const router = express.Router();

router.get('/', controller.list.bind(controller));
router.post('/', controller.create.bind(controller));
router.get('/:id', controller.show.bind(controller));
router.put('/:id', controller.edit.bind(controller));
router.delete('/:id', controller.remove.bind(controller));
router.post('/login', controller.login.bind(controller));

module.exports = router;