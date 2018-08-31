/**
 * Service responsável por manipular o token de autenticação. O token é armazenado
 * nos cookies e recuperado quando necessário. Também possui funções que retorna o header pronto para ser utilizado.
 * Como o token é armazenado, quando efetuar um refresh no navegador, é possível manter a sessão do
 * usuário ativa.
 */

export default function OAuthToken() {
    const self = {
        setTokenProperty: setTokenProperty,
        $get: $get
    };

    self.tokenConfig = {
        name: 'token_name',
        options: {
            secure: false
        }
    };

    self.$get.$inject = ['$cookies'];

    return self;

    function setTokenProperty(tokenProperty) {
        self.tokenConfig.name = tokenProperty;
    }

    function $get($cookies) {

        return {
            isAuthenticated: isAuthenticated,
            getToken: getToken,
            setToken: setToken,
            getAccessToken: getAccessToken,
            getRefreshToken: getRefreshToken,
            getTokenType: getTokenType,
            getAuthorizationHeader: getAuthorizationHeader,
            removeToken: removeToken
        };

        function isAuthenticated() {
            return Boolean(getToken());
        }

        function getToken() {
            return $cookies.getObject(self.tokenConfig.name);
        }

        function setToken(data) {
            const {name, options} = self.tokenConfig;
            $cookies.putObject(name, data, options);
        }

        function getAccessToken() {
            const {access_token} = getToken() || '';
            return access_token;
        }

        function getRefreshToken() {
            const {refresh_token} = getToken() || '';
            return refresh_token;
        }

        function getTokenType() {
            const {token_type} = getToken() || {};

            return token_type;
        }

        function getAuthorizationHeader() {
            const tokenType = getTokenType();
            const accessToken = getAccessToken();

            if (!tokenType || !accessToken) {
                return;
            }

            return `${tokenType.charAt(0).toUpperCase() + tokenType.substr(1)} ${accessToken}`;
        }

        function removeToken() {
            return $cookies.remove(self.tokenConfig.name, self.tokenConfig.options);
        }
    }
}
