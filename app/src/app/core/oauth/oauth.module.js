import angular from 'angular';

import oauth2Front from './oauth2/oauth2.module';

import oauthConstantsModule from './oauth.constans';
import oAuthConfig from './oauth.config';

const MODULE_NAME = 'app.oauth';

angular.module(MODULE_NAME, [
    oauthConstantsModule,
    oauth2Front
])
    .config(oAuthConfig);

export default MODULE_NAME;