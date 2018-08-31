import erroToastTemplate from './toast-error-template.html';

export default class ToastService {
    constructor($mdToast) {
        this.$mdToast = $mdToast;
    }

    showSimple(text, delay = 5000, position = 'bottom left') {
        this.$mdToast.show(
            this.$mdToast.simple()
                .textContent(text)
                .position(position)
                .hideDelay(delay)
        );
    }

    showCustom(text, type = 'error', delay = 5000, position = 'bottom left') {
        this.$mdToast.show({
            hideDelay: delay,
            position: position,
            locals: {
                text
            },
            controller: '$chlClosableToastController',
            controllerAs: 'vm',
            template: erroToastTemplate,
            toastClass: type
        });
    }
}

ToastService.$inject = [
    '$mdToast'
];