import _ from 'lodash';
import axios from '../../../services/axios-instance';

export const FETCH_USERS = 'FETCH_USERS';
export const FETCH_USERS_SUCCESS = 'FETCH_USERS_SUCCESS';
export const FETCH_USERS_ERROR = 'FETCH_USERS_ERROR';
export const FETCH_USER = 'FETCH_USER';
export const FETCH_USER_SUCCESS = 'FETCH_USER_SUCCESS';
export const FETCH_USER_ERROR = 'FETCH_USER_ERROR';
export const RESET_USER_EDIT = 'RESET_USER_EDIT';
export const DELETE_USER = 'DELETE_USER';
export const DELETE_USER_OK = 'DELETE_USER_OK';
export const DELETE_USER_FAIL = 'DELETE_USER_FAIL';

export const requestUsers = () => ({type: FETCH_USERS});
export const requestUsersOk = users => ({type: FETCH_USERS_SUCCESS, users});
export const requestUsersError = error => ({type: FETCH_USERS_ERROR, error});
export const requestUser = () => ({type: FETCH_USER});
export const requestUserOk = user => ({type: FETCH_USER_SUCCESS, user});
export const requestUserError = error => ({type: FETCH_USER_ERROR, error});
export const resetUserEdit = error => ({type: RESET_USER_EDIT, error});

export const deleteUserAct = () => ({type: DELETE_USER});
export const deleteUserOk = () => ({type: DELETE_USER_OK});
export const deleteUserFail = error => ({type: DELETE_USER_FAIL, error});

const defaultUser = {
    id: null,
    name: '',
    email: '',
    role: '',
    password: '',
    matchPassword: ''
};

const defaultState = {
    isFetching: false,
    deleting: false,
    users: [],
    user: defaultUser
};

export const fetchUsers = () => (dispatch) => {
    dispatch(requestUsers());
    return axios.get(`/users`).then(
        res => {
            dispatch(requestUsersOk(res.data));
        },
        err => dispatch(requestUsersError(err))
    );
};

export const fetchUser = (id, callback) => (dispatch) => {
    dispatch(requestUser());
    return axios.get(`/users/${id}`).then(
        res => {
            dispatch(requestUserOk(res.data));
            callback();
        },
        err => dispatch(requestUserError(err))
    );
};

export const deleteUser = (id, callback) => (dispatch) => {
    dispatch(deleteUserAct());
    return axios.delete(`/users/${id}`).then(
        () => {
            dispatch(deleteUserOk());
            callback();
        },
        err => dispatch(deleteUserFail(err))
    );
};

export const resetEditUser = () => (dispatch) => {
    dispatch(resetUserEdit());
};

export default (state = defaultState, action) => {
    switch (action.type) {
        case FETCH_USERS:
            return {
                ...state,
                isFetching: true
            };
        case FETCH_USERS_SUCCESS:
            return {
                ...state,
                users: action.users,
                isFetching: false
            };
        case FETCH_USERS_ERROR:
            return {
                ...state,
                users: [],
                error: action.error,
                isFetching: false
            };
        case FETCH_USER: {
            return {
                ...state,
                isFetching: true
            }
        }
        case FETCH_USER_ERROR:
            return {
                ...state,
                userToEdit: null,
                error: action.error,
                isFetching: false
            };
        case FETCH_USER_SUCCESS: {
            return {
                ...state,
                isFetching: false,
                user: action.user,
                error: null
            }
        }
        case RESET_USER_EDIT: {
            return {
                ...state,
                user: _.clone(defaultUser)
            };
        }
        case DELETE_USER: {
            return {
                ...state,
                deleting: true,
                deletingError: null
            };
        }
        case DELETE_USER_OK:
            return {
                ...state,
                deleting: false,
                deletingError: null
            };
        case DELETE_USER_FAIL:
            return {
                ...state,
                deleting: false,
                deletingError: action.error
            };
        default:
            return state;
    }
};
