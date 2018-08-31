import angular from 'angular';

import loginStateConfig from './login.state';

import LoginService from './login.service';
import LoginController from './login.controller';

const MODULE_NAME = 'chl.login';

angular.module(MODULE_NAME, [])
    .config(loginStateConfig)
    .service('app-login.LoginService', LoginService)
    .controller('app-login.LoginController', LoginController);

export default MODULE_NAME;