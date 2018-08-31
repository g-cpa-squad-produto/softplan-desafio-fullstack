// Configuração da parte de comunicação web: Restangular, interceptors basicos, etc...

import angular from 'angular';
import restangular from 'restangular';
import 'angular-promise-tracker';

import webConfig from './web.config';
import restangularConfig from './restangular.config';
import httpInterceptorsConfig from './http-interceptors.config';
import errorInterceptor from './error-interceptor';

const MODULE_NAME = 'app.core.web';

angular.module(MODULE_NAME, [
    restangular,
    'ajoslin.promise-tracker'
])
    .factory('ErrorInterceptor', errorInterceptor)
    .config(webConfig)
    .config(restangularConfig)
    .config(httpInterceptorsConfig);

export default MODULE_NAME;