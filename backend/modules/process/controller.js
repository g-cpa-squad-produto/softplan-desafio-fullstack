const helpers = require('../../helpers');

const model = require('./model');

class ProcessController {
    constructor() {
        this.model = model;
    }

    listPending(req, res, next) {
        this.model.search({ feedback: null })
            .then((result) => {
                res.status(200).json(result);
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    addAccount(req, res, next) {
        const { id, accountId } = req.params;
        this.model.addArrayItem(id, 'accounts', accountId)
            .then((result) => {
                res.status(200).json(result);
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    updateFeedback(req, res, next) {
        const { id } = req.params;
        const { feedback } = req.body;

        const data = { feedback };

        this.model.edit(id, data)
            .then((result) => {
                if (result) res.status(200).json(result);
                else {
                    const err = new Error(`Not Found ${id}`);
                    err.statusCode = 404;
                    next(err);
                }
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    removeAccount(req, res, next) {
        const { id, accountId } = req.params;
        this.model.removeArrayItem(id, 'accounts', accountId)
            .then((result) => {
                res.status(200).json(result);
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    create(req, res, next) {
        const { name } = req.body;
        const data = {
            name,
            feedback: null,
            accounts: [],
            type: 'pending'
        };

        this.model.create(data)
            .then((result) => {
                res.status(200).json(result);
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    show(req, res, next) {
        const { id } = req.params;
        this.model.findById(id)
            .then((result) => {
                if (result) res.status(200).json(result);
                else {
                    const err = new Error(`Not Found ${id}`);
                    err.statusCode = 404;
                    next(err);
                }
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    list(req, res, next) {
        this.model.search()
            .then((result) => {
                res.status(200).json(result);
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    edit(req, res, next) {
        const { id } = req.params;
        const { name } = req.body;
        const data = { name };

        this.model.edit(id, data)
            .then((result) => {
                if (result) res.status(200).json(result);
                else {
                    const err = new Error(`Not Found ${id}`);
                    err.statusCode = 404;
                    next(err);
                }
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    remove(req, res, next) {
        const { id } = req.params;

        this.model.remove(id)
            .then((result) => {
                if (result) res.status(204).end();
                else {
                    const err = new Error(`Not Found ${id}`);
                    err.statusCode = 404;
                    next(err);
                }
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }
}

module.exports = new ProcessController();