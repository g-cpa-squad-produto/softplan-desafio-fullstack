import angular from 'angular';
import ngCookies from 'angular-cookies';

import webModule from './web/web.module';
import oauthModule from './oauth/oauth.module';
import commonsModule from './commons/commons.module';
import layoutModule from './layout/layout.module';

import UserService from './user/user-context.service';

const MODULE_NAME = 'app.core';

angular.module(MODULE_NAME, [
    ngCookies,
    oauthModule,
    webModule,
    commonsModule,
    layoutModule
])
    .service('$userService', UserService);

export default MODULE_NAME;