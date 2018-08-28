import { post, get, del, put } from '../../libs/api';

import { CREATE_SESSION } from './session';

export const CREATE_ACCOUNT = 'CREATE_ACCOUNT';
export const EDIT_ACCOUNT = 'EDIT_ACCOUNT';
export const DELETE_ACCOUNT = 'DELETE_ACCOUNT';
export const SHOW_ACCOUNT = 'SHOW_ACCOUNT';
export const LIST_ACCOUNTS = 'LIST_ACCOUNTS';

export const login = (data) => {
    return (dispatch) => {
        return post(`/account/login`, data).exec()
            .then((response) => {
                return dispatch([
                    {
                        type: SHOW_ACCOUNT,
                        payload: {
                            account: response.data
                        }
                    },
                    {
                        type: CREATE_SESSION,
                        payload: {
                            data: response.data
                        }
                    }
                ]);
            });
    }
};

export const showAccount = (id) => {
    return (dispatch) => {
        return get(`/account/${id}`).exec()
            .then((response) => {
                return dispatch({
                    type: SHOW_ACCOUNT,
                    payload: {
                        account: response.data
                    }
                });
            });
    }
};

export const deleteAccount = (id) => {
    return (dispatch) => {
        return del(`/account/${id}`).exec()
            .then((response) => {
                return dispatch({
                    type: DELETE_ACCOUNT,
                    payload: {
                        id
                    }
                });
            });
    }
};

export const createAccount = (data) => {
    return (dispatch) => {
        return post('/account', data).exec()
            .then((response) => {
                return dispatch([
                    {
                        type: CREATE_ACCOUNT,
                        payload: {
                            account: response.data
                        }
                    },
                    {
                        type: CREATE_SESSION,
                        payload: {
                            data: response.data
                        }
                    }
                ]);
            });
    }
};

export const editAccount = (id, data) => {
    return (dispatch) => {
        return put(`/account/${id}`, data).exec()
            .then((response) => {
                return dispatch({
                    type: EDIT_ACCOUNT,
                    payload: {
                        id,
                        account: response.data
                    }
                });
            });
    }
};

export const listAccounts = () => {
    return (dispatch) => {
        return get('/account').exec()
            .then((response) => {
                return dispatch({
                    type: LIST_ACCOUNTS,
                    payload: {
                        list: response.data
                    }
                });
            });
    }
};