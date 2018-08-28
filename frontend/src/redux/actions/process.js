import { post, get, del, put } from '../../libs/api';

export const CREATE_PROCESS = 'CREATE_PROCESS';
export const EDIT_PROCESS = 'EDIT_PROCESS';
export const DELETE_PROCESS = 'DELETE_PROCESS';
export const SHOW_PROCESS = 'SHOW_PROCESS';
export const LIST_PROCESSES = 'LIST_PROCESSES';

export const showProcess = (id) => {
    return (dispatch) => {
        return get(`/process/${id}`).exec()
            .then((response) => {
                return dispatch({
                    type: SHOW_PROCESS,
                    payload: {
                        PROCESS: response.data
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
                        PROCESS: response.data
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
                        PROCESS: response.data
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