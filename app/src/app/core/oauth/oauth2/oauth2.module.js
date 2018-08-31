/**
 * Módulo responsável por implementar a comunicação com a API de autenticação, bem como manipular
 * os tokens recebidos.
 */

import angular from 'angular';

import OAuthInterceptor from './oauth-interceptor';
import OAuthToken from './oauth-token.service';
import OAuth from './oauth.service';

const MODULE_NAME = 'oauth2-front';

angular.module(MODULE_NAME, [])
    .factory('OAuthInterceptor', OAuthInterceptor)
    .provider('OAuthToken', OAuthToken())
    .provider('OAuth', OAuth());

export default MODULE_NAME;