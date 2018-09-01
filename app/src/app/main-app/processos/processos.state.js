import viewTemplate from './processos.html';

export default function processosStateConfig($stateProvider) {
    $stateProvider
        .state('app.main.processos', {
            views: {
                'app-view': {
                    template: viewTemplate,
                    controller: 'chl.ProcessosController as vm'
                }
            },
            url: '/processos'
        });
}

processosStateConfig.$inject = ['$stateProvider'];