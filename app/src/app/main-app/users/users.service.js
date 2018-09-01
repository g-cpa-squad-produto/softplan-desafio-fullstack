export default class UserService {
    constructor(Restangular) {
        this.Restangular = Restangular;
    }

    getAll() {
        return this.Restangular.all('/users').get('', {});
    }

    get(id) {
        return this.Restangular.all('/users').get(id);
    }

    save(user) {
        if (user.id) {

            return this.Restangular.one('/users', user.id).customPUT(user);
        }
        return this.Restangular.all('/users').customPOST(user);
    }

    remove(id) {
        return this.Restangular.one('/users', id).remove();
    }
}

UserService.$inject = ['Restangular'];