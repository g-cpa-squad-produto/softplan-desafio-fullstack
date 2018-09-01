export default class ParecerService {
    constructor(Restangular) {
        this.Restangular = Restangular;
    }

    getAllByUser(user) {
        return this.Restangular.all('/pareceres').get('', {user: user.id});
    }

    get(id) {
        return this.Restangular.all('/pareceres').get(id);
    }

    save(parecer) {
        if (parecer.id) {
            return this.Restangular.one('/pareceres', parecer.id).customPUT(parecer);
        }
        return this.Restangular.all('/pareceres').customPOST(parecer);
    }
}

ParecerService.$inject = ['Restangular'];