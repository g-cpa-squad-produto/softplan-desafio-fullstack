/**
 * A aplicação foi dividade em duas grandes views. Uma que representa o header, ou toolbar, da aplicação, onde
 * ficam os menus (ou tabs) e o toolbar da aplicação. A outra view é app-view, onde fica realmente o conteúdo de
 * cada tela.
 */

import toolbarAppTemplate from './toolbar-app/toolbar-app.html';
import mainTemplate from './main.template.html';

export default function mainStateConfig($stateProvider) {
    $stateProvider
        .state('app.main', {
            url: '/app',
            abstract: true,
            views: {
                '': {
                    template: mainTemplate,
                    controller: 'chl.TabsViewController as vm'
                },
                'toolbar@': {
                    template: toolbarAppTemplate,
                    controller: 'chl.ToolbarAppController as vm'
                }
            },
            resolve: {
                initialize: onEnterMainState
            }
        });
}

onEnterMainState.$inject = [
    '$q',
    '$state',
    '$userService',
    'OAuthToken',
    'OAuth'
];

function onEnterMainState($q, $state, $userService, OAuthToken, OAuth) {
    if (!OAuth.isAuthenticated()) {
        return $state.go('app.login');
    }

    return $userService.getUser()
        .then((response) => {
            $userService.setUserValue(response);
        })
        .catch(() => {
            OAuthToken.removeToken();
            $state.go('app.login');
        });
}

mainStateConfig.$inject = ['$stateProvider'];
