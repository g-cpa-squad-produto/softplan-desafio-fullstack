/**
 * Provê funções para se comunicar com a API de usuários.
 * Também armazeda o usuário logado no momento. Como as Services são Singleton, sempre teremos o valor
 * sem precisar buscar novamente na API.
 */

export default class UserContextService {
    constructor(Restangular) {
        this.Restangular = Restangular;

        this.user = null;
    }

    getUser() {
        return this.Restangular.all('/users').get('current', {})
            .then(this.Restangular.stripRestangular);
    }

    hasUser() {
        return Boolean(this.user);
    }

    getUserValue() {
        return this.user;
    }

    setUserValue(value) {
        this.user = value;
    }
}

UserContextService.$inject = [
    'Restangular'
];