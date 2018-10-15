import axios from 'axios';
import {destroyAuth, getAuth, getUrl, messageAlert} from './Methods';

axios.defaults.baseURL = getUrl();

class Http {
    constructor() {
        this.queryString = [];
        this.url = '';
        this.method = '';
        this.data = {};
    }

    get(url) {
        this.url = url;
        this.method = 'get';
        return this;
    }

    post(url, data) {
        this.url = url;
        this.data = data;
        this.method = 'post';
        return this;
    }

    put(url, data) {
        this.url = url;
        this.data = data;
        this.method = 'put';
        return this;
    }

    patch(url, data) {
        this.url = url;
        this.data = data;
        this.method = 'patch';
        return this;
    }

    delete(url) {
        this.url = url;
        this.method = 'delete';
        return this;
    }

    qs(queryString) {
        this.queryString.push(queryString);
        return this;
    }

    catchMessage(res) {
        console.log(res);
        if (res) {
            if (res.status === 401 || res.status === 403) {
                messageAlert("Acesso negado!", 'error', '/login')
            } else {
                messageAlert(res.data.message, 'error')
            }
        }
    }

    send(res) {
        if (this.queryString) this.url += '?' + this.queryString.map((e, k) => Object.keys(e)[0] + '=' + e[Object.keys(e)[0]]).join('&');
        const token = getAuth();
        if (token) {
            axios.defaults.headers.common['Authorization'] = token;
        }

        switch (this.method) {
            case 'get': axios.get(this.url).then(response => res(response)).catch(error => this.catchMessage(error.response)); break;
            case 'post': axios.post(this.url, this.data).then(response => res(response)).catch(error => this.catchMessage(error.response)); break;
            case 'put': axios.put(this.url, this.data).then(response => res(response)).catch(error => this.catchMessage(error.response)); break;
            case 'delete': axios.delete(this.url).then(response => res(response)).catch(error => this.catchMessage(error.response)); break;
            case 'patch': axios.patch(this.url, this.data).then(response => res(response)).catch(error => this.catchMessage(error.response)); break;
            default: break;
        }
    }
}

export default Http