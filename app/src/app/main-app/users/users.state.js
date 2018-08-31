import usersTemplate from './users.html';

export default function userStateConfig($stateProvider) {
    $stateProvider
        .state('app.main.users', {
            views: {
                'app-view': {
                    template: usersTemplate,
                    contoller: 'chl.UsersController'
                }
            },
            url: '/users'
        });
}

userStateConfig.$inject = ['$stateProvider'];