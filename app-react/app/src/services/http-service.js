import axios from './axios-instance';
import {history} from './history';
import AuthService from './AuthService';

const INVLAID_GRANT_STATES = ['invalid_grant', 'unauthorized', 'invalid_token'];

export default {
    setupInterceptors: () => {
        axios.interceptors.response.use(request => request, (error) => {
            if (INVLAID_GRANT_STATES.includes(error.response.data.error)) {
                AuthService.remove();
                history.push('/');
            }
            return Promise.reject(error);
        });

        axios.interceptors.request.use((request) => {
            if (request.headers.Authorization) {
                return;
            }
            let token = AuthService.get();
            if (token) {
                token = JSON.parse(token);
                request.headers.Authorization = `Bearer ${token.access_token}`;
            }
            return request;
        }, error => error)
    }
}
