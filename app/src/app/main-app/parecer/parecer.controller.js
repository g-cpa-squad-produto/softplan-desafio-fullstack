import angular from 'angular';
import modalTemplate from './cadastro/cadastro-parecer.html';

export default class ParecerController {
    constructor(promiseTracker, $parecerService, $userService, $dialogService) {
        this.promiseTracker = promiseTracker;
        this.$parecerService = $parecerService;
        this.$userService = $userService;
        this.$dialogService = $dialogService;
    }

    $onInit() {
        this.tracker = this.promiseTracker();

        this.fetchPareceres();
    }

    fetchPareceres() {
        const promise = this.$parecerService.getAllByUser(this.$userService.getUserValue())
            .then((pareceres) => {
                this.pareceres = pareceres;
            });
        this.tracker.addPromise(promise);
    }

    onClickInserirParecer($event, parecer) {
        this.$dialogService.showDefault({
            template: modalTemplate,
            controller: 'chl.CadastroParecerController',
            targetEvent: $event,
            locals: {
                parecer: angular.copy(parecer)
            }
        }).then(() => {
            this.fetchPareceres();
        })
    }
}

ParecerController.$inject = [
    'promiseTracker',
    '$parecerService',
    '$userService',
    '$dialogService'
];