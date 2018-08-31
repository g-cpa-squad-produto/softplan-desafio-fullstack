const INVALID_GRANT_STATES = ['invalid_grant', 'unauthorized'];

export default class LoginController {
    constructor($state, promiseTracker, LoginService) {
        this.$state = $state;
        this.promiseTracker = promiseTracker;
        this.LoginSevice = LoginService;

        this.tracker = {
            login: promiseTracker()
        };
    }

    onClickLogin() {
        const promise = this.LoginSevice.login(this.login)
            .then((data) => {
                //Ao logar, sempre manda o usuário para a tela de usuários. Este comportamento deve ser revisto.
                this.$state.go('app.main.users');
                this.errorMessage = '';
            }, this.handleError.bind(this));

        this.tracker.login.addPromise(promise);
    }

    handleError(error) {
        if (!error) {
            return;
        }

        const errorData = error.data;

        if (INVALID_GRANT_STATES.includes(errorData.error)) {
            this.errorMessage = 'Usuário ou senha inválidos'
        }

        this.login.senha = '';
    }
}

LoginController.$inject = [
    '$state',
    'promiseTracker',
    'app-login.LoginService'
];