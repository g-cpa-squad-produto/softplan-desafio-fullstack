export default class DialogService {
    constructor($mdDialog) {
        this.$mdDialog = $mdDialog;
    }

    showDefault({template, controller, targetEvent, locals}) {
        return this.$mdDialog.show({
            template: template,
            controller: controller,
            controllerAs: 'vm',
            targetEvent: targetEvent,
            locals: locals,
            clickOutsideToClose: true,
            disableParentScroll: true,
            bindToController: true,
            multiple: true
        });
    }

    showConfirm({title, text, targetEvent}) {
        return this.$mdDialog.show(this.$mdDialog.confirm()
            .title(title || 'Você deseja excluir o registro?')
            .textContent(text)
            .ariaLabel('Deseja excluir o registro?')
            .targetEvent(targetEvent)
            .ok('Sim')
            .cancel('Cancelar'));
    }
}

DialogService.$inject = [
    '$mdDialog'
];