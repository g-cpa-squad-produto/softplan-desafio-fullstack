import {createRequest} from "../../helpers/RequestHelper";
import {apiAuth} from "../../services/ApiService";

export function findAll(url, onSuccess, onError) {
    return createRequest({
        api: apiAuth, method: 'GET', path: url, onSuccess, onError
    });
}

export function findById(id, onSuccess, onError) {
    return createRequest({
        api: apiAuth, method: 'GET', path: `/usuarios/${id}`, onSuccess, onError
    });
}

export function create(data, onSuccess, onError) {
    return createRequest({
        api: apiAuth, method: 'POST', path: `/usuarios`, data, onSuccess, onError
    });
}

export function remove(id, onSuccess, onError) {
    return createRequest({
        api: apiAuth, method: 'DELETE', path: `/usuarios/${id}`, onSuccess, onError
    });
}