import angular from 'angular';

import userStateConfig from './users.state';
import UsersController from './users.controller';

const MODULE_NAME = 'chl.users';

angular.module(MODULE_NAME, [])
    .config(userStateConfig)
    .controller('chl.UsersController', UsersController);

export default MODULE_NAME;