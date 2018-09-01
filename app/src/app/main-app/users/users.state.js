import usersTemplate from './users.html';

export default function userStateConfig($stateProvider) {
    $stateProvider
        .state('app.main.users', {
            views: {
                'app-view': {
                    template: usersTemplate,
                    controller: 'chl.UsersController as vm'
                }
            },
            url: '/users'
        });
}

userStateConfig.$inject = ['$stateProvider'];