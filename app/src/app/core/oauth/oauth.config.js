/**
 * Efetua as configurações necessárias para os providers do oauth
 */

export default function oauthConfig(OAuthTokenProvider,
                                    OAuthProvider,
                                    TOKEN_PROPERTY,
                                    GRANT_PATH,
                                    REVOKE_PATH,
                                    CLIENT_ID,
                                    CLIENT_SECRET,
                                    API_URL) {
    OAuthProvider.setLoginState('app.login');
    OAuthTokenProvider.setTokenProperty(TOKEN_PROPERTY);
    OAuthProvider.setApiUrl(API_URL);
    OAuthProvider.setTokenConfig({
        client_id: CLIENT_ID,
        client_secret: CLIENT_SECRET,
        grantPath: GRANT_PATH,
        revokePath: REVOKE_PATH
    });
}

oauthConfig.$inject = [
    'OAuthTokenProvider',
    'OAuthProvider',
    'TOKEN_PROPERTY',
    'GRANT_PATH',
    'REVOKE_PATH',
    'CLIENT_ID',
    'CLIENT_SECRET',
    'API_URL'
];
