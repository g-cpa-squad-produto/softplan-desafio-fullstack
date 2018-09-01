const INVALID_GRANT_STATES = ['invalid_grant', 'unauthorized'];

export default class LoginController {
    constructor($state, $userService, promiseTracker, LoginService) {
        this.$state = $state;
        this.$userService = $userService;
        this.promiseTracker = promiseTracker;
        this.LoginSevice = LoginService;

        this.tracker = {
            login: promiseTracker()
        };
    }

    onClickLogin() {
        const promise = this.LoginSevice.login(this.login)
            .then((data) => {
                this.errorMessage = '';
                return this.$userService.getUserState().then((state) => {
                    this.$state.go(state);
                });
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
    '$userService',
    'promiseTracker',
    'app-login.LoginService'
];