import axios from 'axios';
import config from '../config';
import { getToken } from '../redux/store';

const CancelToken = axios.CancelToken;

const factory = (promise, cancel) => {
    return {
        exec: () => promise,
        cancel,
        isCancel: axios.isCancel
    };
};

const resolveUrl = (url) => {
    if(url.charAt(0) === '/') url = url.substr(1);
    return `${config.server}/${url}`;
}

export const post = (url, data, headers) => {
    const source = CancelToken.source();
    headers = resolveHeaders(headers);
    return factory(axios.post(resolveUrl(url), data, { headers, cancelToken: source.token }), source.cancel);
};

export const get = (url, headers) => {
    const source = CancelToken.source();
    headers = resolveHeaders(headers);
    return factory(axios.get(resolveUrl(url), { headers, cancelToken: source.token }), source.cancel);
};

export const del = (url, headers) => {
    const source = CancelToken.source();
    headers = resolveHeaders(headers);
    return factory(axios.delete(resolveUrl(url), { headers, cancelToken: source.token }), source.cancel);
};

export const put = (url, data, headers) => {
    const source = CancelToken.source();
    headers = resolveHeaders(headers);
    return factory(axios.put(resolveUrl(url), data, { headers, cancelToken: source.token }), source.cancel);
};

function resolveHeaders(headers = {}) {
    const token = getToken();

    if (token) headers['Authorization'] = `Bearer ${token}`;
    if (!('Content-Type' in headers)) headers['Content-Type'] = 'application/json';

    return headers;
}