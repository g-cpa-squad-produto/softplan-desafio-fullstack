import loginTemplate from './login.html';
import toolbarInitialTemplate from '../toolbar-initial/toolbar-initial.html';

export default function loginStateConfig($stateProvider) {
    $stateProvider
        .state('app.login', {
            url: '/login',
            views: {
                '': {
                    template: loginTemplate,
                    controller: 'app-login.LoginController as vm'
                },
                'toolbar@': {
                    template: toolbarInitialTemplate
                }
            },
            resolve: {
                initialize: onEnterLoginState
            }
        });
}

onEnterLoginState.$inject = [
    '$state',
    'OAuth'
];

function onEnterLoginState($state, OAuth) {
    if (OAuth.isAuthenticated()) {
        return $state.go('app.main.users');
    }
}

loginStateConfig.$inject = ['$stateProvider'];