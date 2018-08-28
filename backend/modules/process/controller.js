const helpers = require('../../helpers');

const model = require('./model');

class ProcessController {
    constructor() {
        this.model = model;
    }

    addAccount(req, res, next) {
        const { id, accountId } = req.params;
        this.model.addArrayItem(id, 'users', accountId)
            .then((result) => {
                res.status(200).json(result);
            })
            .catch((err) => {
                console.log(err);
                err.statusCode = 500;
                next(err);
            });
    }

    updateStatus(req, res, next) {
        const { id } = req.params;
        const { status, description } = req.body;
        
        const data = {status, description};

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
        this.model.removeArrayItem(id, 'users', accountId)
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
        const { name, description } = req.body;
        const data = {
            name,
            description,
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
        const { name, description} = req.body;
        const data = { name, description};

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