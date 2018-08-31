/*
* Config para adicionar os interceptors na aplicação
*/
export default function httpInterceptorsConfig($httpProvider) {
    $httpProvider.interceptors.push('OAuthInterceptor');
    $httpProvider.interceptors.push('ErrorInterceptor');
}

httpInterceptorsConfig.$inject = ['$httpProvider'];