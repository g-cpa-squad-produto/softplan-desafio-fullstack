import viewTemplate from './parecer.html';

export default function parecerStateConfig($stateProvider) {
    $stateProvider
        .state('app.main.parecer', {
            views: {
                'app-view': {
                    template: viewTemplate,
                    controller: 'chl.ParecerController as vm'
                }
            },
            url: '/parecer'
        });
}

parecerStateConfig.$inject = ['$stateProvider'];