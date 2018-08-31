/**
 * Interceptor que lança toasts para erros que ocorrem na API.
 * No momento, os erros de autorização estão sendo ignorados, para que o refresh do token seja transparente.
 */
import _ from 'lodash';

const DEFAULT_MESSAGE = 'Aconteceu um erro interno no servidor';

export default function errorInterceptor($q, $injector) {
    return {
        responseError: (response) => {
            if (response.status === 401 || response.status === 400) {
                return $q.reject(response);
            }
            const $chlToastService = $injector.get('$chlToastService');

            let message = _.get(response, 'data.message');

            if (response.status === 500 || !message) {
                message = DEFAULT_MESSAGE;
            }

            $chlToastService.showCustom(message);
            return $q.reject(response);
        }
    };

}

errorInterceptor.$inject = [
    '$q',
    '$injector'
];