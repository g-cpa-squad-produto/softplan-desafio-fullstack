export default class LoginSevice {
    constructor($rootScope, OAuth, $userService) {
        this.$rootScope = $rootScope;
        this.OAuth = OAuth;
        this.$userService = $userService;

        this.onTokenExpire = this.onTokenExpire.bind(this);
        this.clearContextsValues = this.clearContextsValues.bind(this);

        this.$rootScope.$on('user:tokenRevoked', this.onTokenExpire);
    }

    login(data) {
        return this.OAuth.getToken({
            user: data.usuario,
            password: data.senha
        });
    }

    //todo: funcao de logout implementada, mas hoje não é chamada. Arrumar meio de deslogar usuário.
    logout() {
        return this.OAuth.revokeTokens()
            .then(() => {
                this.clearContextsValues()
            });
    }

    onTokenExpire() {
        this.clearContextsValues();
    }

    clearContextsValues() {
        this.$userService.setUserValue(null);
    }
}

LoginSevice.$inject = [
    '$rootScope',
    'OAuth',
    '$userService'
];
