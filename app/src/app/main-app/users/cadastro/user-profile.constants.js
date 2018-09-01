import angular from 'angular';

export default angular.module('chl.users.constants', [])
    .constant('USER_PROFILE', {
        ROLE_ADMIN: 'Administador',
        ROLE_TRIADOR: 'Triador',
        ROLE_FINALIZADOR: 'Finalizador'
    })
    .constant('USER_PROFILE_ENUM', [
        {
            id: 'ROLE_ADMIN',
            description: 'Administador'
        },
        {
            id: 'ROLE_TRIADOR',
            description: 'Triador'
        },
        {
            id: 'ROLE_FINALIZADOR',
            description: 'Finalizador'
        }
    ])
    .name;