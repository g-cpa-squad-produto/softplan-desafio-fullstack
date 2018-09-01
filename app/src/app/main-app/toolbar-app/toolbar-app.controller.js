/**
 * Controller ainda não utilizada.
 */
export default class ToolbarAppController {
    constructor(LoginService, $state) {
        this.LoginService = LoginService;
        this.$state = $state;
    }

    onClickLogout() {
        this.LoginService.logout()
            .then(() => {
                this.$state.go('app.login');
        });
    }
}

ToolbarAppController.$inject = ['app-login.LoginService', '$state'];