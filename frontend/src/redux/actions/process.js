import { post, get, del, put, patch } from '../../libs/api';
import { EDIT_ACCOUNT } from './account';

export const CREATE_PROCESS = 'CREATE_PROCESS';
export const EDIT_PROCESS = 'EDIT_PROCESS';
export const DELETE_PROCESS = 'DELETE_PROCESS';
export const SHOW_PROCESS = 'SHOW_PROCESS';
export const LIST_PROCESSES = 'LIST_PROCESSES';
export const LIST_PENDING_PROCESSES = 'LIST_PENDING_PROCESSES';
export const ADD_ACCOUNT_TO_PROCESSES = 'ADD_ACCOUNT_TO_PROCESSES';
export const REMOVE_ACCOUNT_FROM_PROCESSES = 'REMOVE_ACCOUNT_FROM_PROCESSES';
export const UPDATE_FEEDBACK = 'UPDATE_FEEDBACK';

export const showProcess = (id) => {
    return (dispatch) => {
        return get(`/process/${id}`).exec()
            .then((response) => {
                return dispatch({
                    type: SHOW_PROCESS,
                    payload: {
                        process: response.data
                    }
                });
            });
    }
};

export const deleteProcess = (id) => {
    return (dispatch) => {
        return del(`/process/${id}`).exec()
            .then((response) => {
                return dispatch({
                    type: DELETE_PROCESS,
                    payload: {
                        id
                    }
                });
            });
    }
};

export const createProcess = (data) => {
    return (dispatch) => {
        return post('/process', data).exec()
            .then((response) => {
                return dispatch({
                    type: CREATE_PROCESS,
                    payload: {
                        process: response.data
                    }
                });
            });
    }
};

export const editProcess = (id, data) => {
    return (dispatch) => {
        return put(`/process/${id}`, data).exec()
            .then((response) => {
                return dispatch({
                    type: EDIT_PROCESS,
                    payload: {
                        id,
                        process: response.data
                    }
                });
            });
    }
};

export const listProcesses = () => {
    return (dispatch) => {
        return get('/process').exec()
            .then((response) => {
                return dispatch({
                    type: LIST_PROCESSES,
                    payload: {
                        list: response.data
                    }
                });
            });
    }
};

export const listPendingProcesses = () => {
    return (dispatch) => {
        return get('/process/pending').exec()
            .then((response) => {
                return dispatch({
                    type: LIST_PENDING_PROCESSES,
                    payload: {
                        list: response.data
                    }
                });
            });
    }
};

export const addAccountToProcess = (id, accountId) => {
    return (dispatch) => {
        return patch(`/process/${id}/account/${accountId}`).exec()
            .then((response) => {
                return dispatch({
                    type: ADD_ACCOUNT_TO_PROCESSES,
                    payload: {
                        list: response.data
                    }
                });
            });
    }
};

export const removeAccountFromProcess = (id, accountId) => {
    return (dispatch) => {
        return patch(`/process/${id}/account/${accountId}/remove`).exec()
            .then((response) => {
                return dispatch({
                    type: REMOVE_ACCOUNT_FROM_PROCESSES,
                    payload: {
                        list: response.data
                    }
                });
            });
    }
};

export const updateFeedback = (id, data) => {
    return (dispatch) => {
        return put(`/process/${id}/feedback`, data).exec()
            .then((response) => {
                return dispatch({
                    type: EDIT_PROCESS,
                    payload: {
                        id,
                        process: response.data
                    }
                });
            });
    }
};