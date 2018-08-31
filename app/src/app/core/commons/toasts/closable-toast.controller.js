export default class ClosableToastController {
    constructor($mdToast, text) {
        this.$mdToast = $mdToast;
        this.text = text;
    }

    closeToast() {
        this.$mdToast.hide();
    }
}

ClosableToastController.$inject = [
    '$mdToast',
    'text'
];
