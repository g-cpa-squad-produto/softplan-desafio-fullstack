import axios from 'axios';
import cookie from 'react-cookie';
import {ACCESS_TOKEN, URL_API} from '../constants';

export function createRequest({api, type, method = 'GET', path = '', params, data, onSuccess, onError}) {
    return api({method, url: path, data, responseType: type, params})
        .then(onSuccess)
        .catch(onError);
}

export function createPublicRequest() {
    return axios.create({
        baseURL: URL_API
    });
}

export function createAuthRequest() {
    const api = axios.create({
        baseURL: URL_API
    });

    api.interceptors.request.use(function (config) {

        let token = cookie.load(ACCESS_TOKEN) || null;

        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token;
        }

        return config;

    }, function (error) {
        return Promise.reject(error); // Do something with request error
    });

    return api;
}

