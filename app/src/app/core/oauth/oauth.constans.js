/**
 * Criar algumas constantes para o oauth, apenas para não deixar fixo no código.
 */

import angular from 'angular';

export default angular.module('app.oauth.constants', [])
    .constant('TOKEN_PROPERTY', 'challenge_token')
    .constant('GRANT_PATH', '/oauth/token')
    .constant('REVOKE_PATH', '/tokens/revoke')
    .constant('CLIENT_ID', 'challenge_client')
    .constant('CLIENT_SECRET', 'secret')
    .constant('REFRESH_TOKEN_URL', '/api/oauth/token?grant_type=refresh_token')
    .name;