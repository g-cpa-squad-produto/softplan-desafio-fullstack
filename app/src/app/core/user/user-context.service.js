/**
 * Provê funções para se comunicar com a API de usuários.
 * Também armazeda o usuário logado no momento. Como as Services são Singleton, sempre teremos o valor
 * sem precisar buscar novamente na API.
 */

export default class UserContextService {
    constructor(Restangular, $q) {
        this.Restangular = Restangular;
        this.$q = $q;

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

    getUserState() {
        let current = this.getUserValue();
        if (!current) {
            return this.getUser().then((user) => {
                return STATE_ROLE_MAP[user.role];
            })
        }
        return this.$q.resolve(STATE_ROLE_MAP[current.role]);
    }
}

const STATE_ROLE_MAP = {
    'ROLE_ADMIN': 'app.main.users',
    'ROLE_TRIADOR': 'app.main.processos',
    'ROLE_FINALIZADOR': 'app.main.parecer',
};

UserContextService.$inject = [
    'Restangular', '$q'
];