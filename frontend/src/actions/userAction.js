import axios from 'axios';
import { GET_ERRORS, GET_ACTION, POST_ACTION, PUT_ACTION, DELETE_ACTION } from './types';
import { config, API_URL } from './config';

export const getAction = (role) => dispatch => {
    axios.get(API_URL + getRoute('GET:', role), config)
        .then(res => {
            dispatch({
                type: GET_ACTION,
                payload: res.data
            });
        })
        .catch(err => {
            dispatch({
                type: GET_ERRORS,
                payload: err.response
            });
        });
}

export const postAction = (role, data) => dispatch => {
    axios.post(API_URL + getRoute('POST:', role), data, config)
        .then(json => {
            dispatch({
                type: POST_ACTION,
                payload: json
            });
        })
        .catch(err => {
            dispatch({
                type: GET_ERRORS,
                payload: err.response
            });
        });
}

export const putAction = (role, data, id) => dispatch => {
    axios.put(API_URL + getRoute('PUT:', role, id), data, config)
        .then(res => res.json())
        .then(json => {
            dispatch({
                type: PUT_ACTION,
                payload: json
            });
        })
        .catch(err => {
            dispatch({
                type: GET_ERRORS,
                payload: err.response
            });
        });
}

export const deleteAction = (role, id) => dispatch => {
    axios.delete(API_URL + getRoute('DELETE:', role, id), config)
        .then(res => {
            dispatch({
                type: DELETE_ACTION,
                payload: res
            });
        })
        .catch(err => {
            dispatch({
                type: GET_ERRORS,
                payload: err.response
            });
        });
}

export const getRoute = (type, role, id) => {
    switch (type + role) {
        /* ADMIN */
        case 'GET:ROLE_ADMIN':
            return 'admin/users';
        case 'POST:ROLE_ADMIN':
            return 'admin/users';
        case 'PUT:ROLE_ADMIN':
            return 'admin/users/' + id;
        case 'DELETE:ROLE_ADMIN':
            return 'admin/users/' + id;
        /* EDITORS */
        case 'GET:ROLE_TRIAL':
            return 'trial/lawsuits';
        case 'POST:ROLE_TRIAL':
            return 'trial/lawsuits';
        case 'PUT:ROLE_TRIAL':
            return 'trial/lawsuits/' + id;
        case 'DELETE:ROLE_TRIAL':
            return 'trial/lawsuits/' + id;
        /* END_USER */
        case 'GET:ROLE_EDITOR':
            return 'editor/lawsuits/opened';
        case 'PUT:ROLE_EDITOR':
            return 'editor/lawsuits/opened/' + id;
        default:
            return '';
    }
}
