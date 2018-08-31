/**
 * Interceptos que solicita o refresh do token caso a requisição tenha falhado por expiração de token.
 * O interceptor também armazena todas as requisições para posterior envio, ao conseguir um novo token;
 * Se o refesh token for inválido, o usuário é mandado novamente para o login.
 *
 * Também possui o interceptor de request, que adicionar o header Authorization, caso o mesmo não esteja já nos headers
 * */
import _ from 'lodash';

const INVLAID_GRANT_STATES = ['invalid_grant', 'unauthorized'];
const INVALID_TOKEN = 'invalid_token';
const AUTHORIZATION_HEADER = 'Authorization';

export default function oauthInterceptor($q, OAuthToken, REFRESH_TOKEN_URL, $injector) {
    let isGettingToken = false;
    let requestsToExecute = [];

    return {
        request: (config) => {
            const authorizationHeader = OAuthToken.getAuthorizationHeader();

            if (!config.headers[AUTHORIZATION_HEADER] && authorizationHeader) {
                config.headers[AUTHORIZATION_HEADER] = authorizationHeader;
            }

            return config;
        },
        responseError: (response) => {
            if (response.status !== 401 && response.status !== 400) {
                return $q.reject(response);
            }

            const OAuth = $injector.get('OAuth');
            const $http = $injector.get('$http');

            const error = _.get(response, 'data.error');

            if (!error) {
                return;
            }

            if (INVLAID_GRANT_STATES.includes(error) || (error === INVALID_TOKEN && isRefreshTokenRequest(response.config.url))) {
                isGettingToken = false;
                requestsToExecute = [];
                OAuth.sendToLogin();
                return $q.reject(response);
            }

            let deferred = $q.defer();
            requestsToExecute.push({
                request: response.config,
                promise: deferred
            });

            if (!isGettingToken) {
                OAuth.refreshToken(null, null)
                    .then(() => {
                        executeRequests(requestsToExecute);
                        requestsToExecute = [];
                        isGettingToken = false;
                    });

                isGettingToken = true;
            }

            return deferred.promise;

            function executeRequests(requests) {
                _.forEach(requests, req => {
                    req.request.headers[AUTHORIZATION_HEADER] = null;
                    $http(req.request)
                        .then((resp) => req.promise.resolve(resp));
                });
            }
        }
    };

    function isRefreshTokenRequest(url) {
        return _.includes(url, REFRESH_TOKEN_URL);
    }
}

oauthInterceptor.$inject = [
    '$q',
    'OAuthToken',
    'REFRESH_TOKEN_URL',
    '$injector'
];