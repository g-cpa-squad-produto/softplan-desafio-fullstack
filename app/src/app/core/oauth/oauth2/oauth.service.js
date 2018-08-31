/**
 * Service responsável por se comunicar com a API de autenticação.
 * Esta service já possui os métodos necessários para se comunicar com as APIS fornecidas pelo Spring;
 */

export default function OAuth() {

    const self = {
        setApiUrl: setApiUrl,
        setTokenConfig: setTokenConfig,
        setLoginState: setLoginState,
        $get: $get
    };

    self.API_URL = '';
    self.loginState = '';
    self.tokenConfig = {
        client_id: 'CLIENT_ID',
        client_secret: 'CLIENT_SECRET',
        grantPath: 'GRANT_PATH',
        revokePath: 'REVOKE_PATH'
    };

    self.$get.$inject = [
        '$rootScope',
        '$http',
        'API_URL',
        'OAuthToken',
        '$state'
    ];

    return self;

    function setApiUrl(apiUrl) {
        self.API_URL = apiUrl;
    }

    function setTokenConfig(tokenConfig) {
        self.tokenConfig = tokenConfig;
    }

    function setLoginState(loginState) {
        self.loginState = loginState;
    }

    function $get($rootScope,
                  $http,
                  API_URL,
                  OAuthToken,
                  $state) {

        return {
            isAuthenticated: isAuthenticated,
            getAuthorizationHeaderOAuth: getAuthorizationHeaderOAuth,
            sendToLogin: sendToLogin,
            getToken: getToken,
            refreshToken: refreshToken,
            revokeTokens: revokeTokens,
            onReceiveNewToken: onReceiveNewToken,
            buildRefreshTokenUrl: buildRefreshTokenUrl,
            buildLoginTokenUrl: buildLoginTokenUrl,
            buildRevokeTokenUrl: buildRevokeTokenUrl,
            getAuthorizationHeader: getAuthorizationHeader
        };

        function isAuthenticated() {
            return OAuthToken.isAuthenticated();
        }

        function getAuthorizationHeaderOAuth() {
            return OAuthToken.getAuthorizationHeader();
        }

        function sendToLogin() {
            if ($state.current.name === self.loginState) {
                return;
            }
            $rootScope.$broadcast('user:tokenRevoked');
            OAuthToken.removeToken();
            $state.go(self.loginState);
        }

        function getToken(data, options) {
            data = angular.extend({
                grant_type: 'password'
            }, data);

            options = angular.extend({
                headers: {
                    'Authorization': `Basic ${getAuthorizationHeader()}`
                }
            }, options);

            return $http.post(buildLoginTokenUrl(data), {}, options)
                .then(onReceiveNewToken);
        }

        function refreshToken(data, options) {
            data = angular.extend({
                grant_type: 'refresh_token'
            }, data);

            options = angular.extend({
                headers: {
                    'Authorization': `Basic ${getAuthorizationHeader()}`
                }
            }, options);

            const refreshToken = OAuthToken.getRefreshToken();

            return $http.post(buildRefreshTokenUrl(data, refreshToken), {}, options)
                .then(onReceiveNewToken);
        }

        function revokeTokens(options) {
            options = angular.extend({
                headers: {
                    'Authorization': `Basic ${getAuthorizationHeader()}`
                }
            }, options);

            const token = OAuthToken.getAccessToken();
            const refreshToken = OAuthToken.getRefreshToken();

            return $http.post(buildRevokeTokenUrl(token, refreshToken), {}, options)
                .then((data) => {
                    OAuthToken.removeToken();
                });
        }

        function onReceiveNewToken(response) {
            OAuthToken.setToken(response.data);
            return response;
        }

        function buildRefreshTokenUrl(data, refreshToken) {
            return `${self.API_URL}${self.tokenConfig.grantPath}?grant_type=${data.grant_type}&refresh_token=${refreshToken}`;
        }

        function buildLoginTokenUrl(data) {
            return `${self.API_URL}${self.tokenConfig.grantPath}?grant_type=${data.grant_type}&username=${data.user}&password=${data.password}`;
        }

        function buildRevokeTokenUrl(token, refreshToken) {
            return `${self.API_URL}${self.tokenConfig.revokePath}?token=${token}&refresh_token=${refreshToken}`;
        }

        function getAuthorizationHeader() {
            const authContent = `${self.tokenConfig.client_id}:${self.tokenConfig.client_secret}`;
            return window.btoa(authContent);
        }
    }
}
