export default class CadastroUserController {
    constructor($mdDialog, $usersService, $chlToastService, promiseTracker, USER_PROFILE_ENUM) {
        this.$mdDialog = $mdDialog;
        this.$usersService = $usersService;
        this.$chlToastService = $chlToastService;
        this.promiseTracker = promiseTracker;
        this.USER_PROFILE_ENUM = USER_PROFILE_ENUM;
    }

    $onInit() {
        this.tracker = this.promiseTracker();

        this.user = this.user || {};
    }

    onClickCancel() {
        this.$mdDialog.cancel();
    }

    onClickSave() {
        const promise = this.$usersService.save(this.user)
            .then(() => {
                this.$chlToastService.showCustom('Usuário salvo com sucesso', 'success');
                this.$mdDialog.hide();
            });
        this.tracker.addPromise(promise);
    }
}

CadastroUserController.$inject = [
    '$mdDialog',
    '$usersService',
    '$chlToastService',
    'promiseTracker',
    'USER_PROFILE_ENUM'
];