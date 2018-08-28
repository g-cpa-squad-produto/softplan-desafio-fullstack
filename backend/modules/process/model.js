const Mongoose = require('mongoose');
const MongooseSchema = Mongoose.Schema;

class ProcessModel {
    constructor() {
        const schema = new MongooseSchema({ name: 'string', feedback: 'string', accounts: Array });
        this.db = Mongoose.model('Process', schema);
    }

    removeArrayItem(id, key, value, returnBoolean) {
        return this.pull(id, key, value);
    }

    addArrayItem(id, key, value, returnBoolean) {
        return this.push(id, key, value);
    }

    push(id, key, value, returnBoolean = true) {
        if (!Array.isArray(value)) value = [value];

        const pushValue = {};
        pushValue[key] = value;

        return this.db.update({ _id: id }, { $push: pushValue })
            .then((result) => {
                if (returnBoolean) return this.returnBoolean(result);
                return result;
            });
    }

    pull(id, key, value, returnBoolean = true) {
        const pullValue = {};
        pullValue[key] = Array.isArray(value) ? { $in: value } : value;

        return this.db.update({ _id: id }, { $pull: pullValue }, { multi: true })
            .then((result) => {
                if (returnBoolean) return this.returnBoolean(result);
                return result;
            });
    }

    getArrayItem(id, field, condition) {
        return this.db.findOne({
            _id: this.id(id)
        }).then((result) => {
            if (result) {
                const item = result[field].find((item) => {
                    return item === condition;
                });

                return item;
            }

            return null;
        });
    }

    create(data) {
        return this.db.create(data);
    }

    edit(id, data, options) {
        if (!options) options = { new: true };
        return this.db.findOneAndUpdate({ _id: id }, { $set: data }, options);
    }

    remove(id, returnBoolean = true) {
        return this.db.deleteOne({ _id: id })
            .then((result) => {
                if (returnBoolean) return this.returnBoolean(result);
                return result;
            });
    }

    findById(id) {
        return this.db.findOne({ _id: id });
    }

    search(criteria = {}) {
        return this.db.find(criteria);
    }

    returnBoolean(result) {
        const nModified = 'nModified' in result ? result.nModified : true;
        return result.ok > 0 && result.n > 0 && nModified;
    }
}

module.exports = new ProcessModel();