import angular from 'angular';
import userConstants from './cadastro/user-profile.constants';

import userStateConfig from './users.state';
import UsersController from './users.controller';
import userService from './users.service';
import CadastroUserController from "./cadastro/cadastro-user.controller";

const MODULE_NAME = 'chl.users';

angular.module(MODULE_NAME, [userConstants])
    .config(userStateConfig)
    .service('$usersService', userService)
    .controller('chl.UsersController', UsersController)
    .controller('chl.CadastroUserController', CadastroUserController);

export default MODULE_NAME;