export default class CadastroParecerController {
    constructor(promiseTracker, $mdDialog, $parecerService, $chlToastService) {
        this.promiseTracker = promiseTracker;
        this.$mdDialog = $mdDialog;
        this.$parecerService = $parecerService;
        this.$chlToastService = $chlToastService;
    }

    $onInit() {
        this.tracker = this.promiseTracker();

        this.parecer = this.parecer || {};
    }

    onClickSave() {
        const promise = this.$parecerService.save(this.parecer)
            .then(() => {
                this.$chlToastService.showCustom('Parecer salvo com sucesso', 'success');
                this.$mdDialog.hide();
            });
        this.tracker.addPromise(promise);
    }

    onClickCancel() {
        this.$mdDialog.cancel();
    }
}

CadastroParecerController.$inject = [
    'promiseTracker',
    '$mdDialog',
    '$parecerService',
    '$chlToastService'
];