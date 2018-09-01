export default class CadastroProcesso {
    constructor(promiseTracker, $mdDialog, $processosService, $chlToastService) {
        this.promiseTracker = promiseTracker;
        this.$mdDialog = $mdDialog;
        this.$processosService = $processosService;
        this.$chlToastService = $chlToastService;
    }

    $onInit() {
        this.tracker = this.promiseTracker();

        this.processo = {};
    }

    onClickCancel() {
        this.$mdDialog.cancel();
    }

    onClickSave() {
        const promise = this.$processosService.save(this.processo)
            .then(() => {
                this.$chlToastService.showCustom('Processo salvo com sucesso', 'success');
                this.$mdDialog.hide();
            });
        this.tracker.addPromise(promise);
    }
}

CadastroProcesso.$inject = [
    'promiseTracker',
    '$mdDialog',
    '$processosService',
    '$chlToastService'
];
