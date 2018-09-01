import directiveTemplate from './chl-dialog-header.directive.html';

class DirectiveController {
    constructor($mdDialog) {
        this.$mdDialog = $mdDialog;
    }

    cancel() {
        this.$mdDialog.cancel();
    }
}

DirectiveController.$inject = [
    '$mdDialog'
];

export default {
    template: directiveTemplate,
    bindings: {
        title: '<'
    },
    controllerAs: 'vm',
    controller: DirectiveController
};