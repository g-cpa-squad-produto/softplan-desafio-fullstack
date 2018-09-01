import modalTemplate from './cadastro/cadastro-processo.html';
import vinculoTemplate from './vinculo/vinculo.html';

export default class ProcessosController {
    constructor($dialogService, $processosService, promiseTracker) {
        this.$dialogService = $dialogService;
        this.$processosService = $processosService;
        this.promiseTracker = promiseTracker;
    }

    $onInit() {
        this.tracker = {
            list: this.promiseTracker()
        };
        this.fetchProcessos();
    }

    fetchProcessos() {
        const promise = this.$processosService.getAll()
            .then(processos => {
                this.processos = processos;
            });
        this.tracker.list.addPromise(promise);
    }

    onClickCriar($event) {
        this.$dialogService.showDefault({
            template: modalTemplate,
            controller: 'chl.CadastroProcesso',
            targetEvent: $event
        }).then(() => {
            this.fetchProcessos();
        })
    }

    onClickVinculo($event, processo) {
        this.$dialogService.showDefault({
            template: vinculoTemplate,
            controller: 'chl.VinculoController',
            targetEvent: $event,
            locals: {
                processo: processo
            }
        }).then(() => {
            this.fetchProcessos();
        })
    }
}

ProcessosController.$inject = [
    '$dialogService',
    '$processosService',
    'promiseTracker'
];