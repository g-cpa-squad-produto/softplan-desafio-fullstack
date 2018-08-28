const helpers = require('../../helpers');
const { hashPassword } = helpers;

const model = require('./model');

class AccountController {
    constructor() {
        this.model = model;
    }

    login(req, res, next) {
        const { email, password } = req.body;
        const hashedPassword = hashPassword(password);
        const data = {
            email,
            password: hashedPassword
        };

        this.model.search(data)
            .then((result) => {
                if (result.length) {
                    res.status(200).json(result[0]);
                } else {
                    const err = new Error(`Not Found ${email}`);
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

    create(req, res, next) {
        const { email, password, type } = req.body;
        const hashedPassword = hashPassword(password);
        const data = {
            email,
            password: hashedPassword,
            type
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

        this.model.edit(id, req.body)
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

module.exports = new AccountController();