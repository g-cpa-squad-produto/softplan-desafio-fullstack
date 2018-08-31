/**
 * Cria state principal(raiz) da aplicação.
 */
export default function appStateConfig($stateProvider, $urlRouterProvider, $locationProvider) {
    $urlRouterProvider.otherwise('/login');

    $stateProvider.state('app', {
        url: '',
        abstract: true
    });

    $locationProvider.html5Mode(true);
}

appStateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider'];
