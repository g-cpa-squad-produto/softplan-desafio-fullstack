import PubSub from 'pubsub-js';

export default class ErrorTreatment {

    publish(errorJSON) {
        errorJSON.errors.forEach(function(err) {
            PubSub.publish('errors-form-autores', err);
        });
    }
}