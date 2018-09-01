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
    'OAuth',
    '$userService'
];

function onEnterLoginState($state, OAuth, $userService) {
    if (OAuth.isAuthenticated()) {
        return $userService.getUserState().then((state) => {
            return $state.go(state);
        })
    }
}

loginStateConfig.$inject = ['$stateProvider'];