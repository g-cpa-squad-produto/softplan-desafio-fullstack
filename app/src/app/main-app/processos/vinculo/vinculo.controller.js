import _ from 'lodash';

export default class VinculoUsuario {
    constructor(promiseTracker, $processosService, $mdDialog, $parecerService, $chlToastService) {
        this.$processosService = $processosService;
        this.promiseTracker = promiseTracker;
        this.$mdDialog = $mdDialog;
        this.$parecerService = $parecerService;
        this.$chlToastService = $chlToastService;
    }

    $onInit() {
        this.tracker = {
            list: this.promiseTracker(),
            save: this.promiseTracker()
        };

        this.fetchUsers();
    }

    fetchUsers() {
        const promise = this.$processosService.vinculaveis(this.processo.id)
            .then((users) => {
                this.users = users;
            })
            .catch(() => {
                this.$mdDialog.close();
            });
        this.tracker.list.addPromise(promise);
    }

    onClickCancel() {
        this.$mdDialog.cancel();
    }

    onClickSave() {
        const selected = _.filter(this.users, {$$selected: true});
        const pareceres = _.map(selected, (user) => {
            return {
              user,
              processo: this.processo
            };
        });
        const promise = this.$parecerService.save(pareceres)
            .then(() => {
                this.$mdDialog.hide();
                this.$chlToastService.showCustom('Vínculo do(s) usuário(s) criado com sucesso', 'success');
            });
        this.tracker.save.addPromise(promise);
    }

    hasSelected() {
        return _.find(this.users, {$$selected: true});
    }
}

VinculoUsuario.$inject = [
    'promiseTracker',
    '$processosService',
    '$mdDialog',
    '$parecerService',
    '$chlToastService'
];