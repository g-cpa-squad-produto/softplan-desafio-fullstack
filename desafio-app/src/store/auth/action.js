import {authService} from '../../services/index';

export const actionTypes = {
    LOAD: 'LOAD',
    LOGOUT: 'LOGOUT'
};

export function login(username, password, onSuccess, onError) {
    authService.login(username, password, onSuccess, onError);
    return load(onSuccess, onError);
}

export function logout(onSuccess) {
    return dispatch => {
        authService.logout();
        dispatch({type: actionTypes.LOGOUT});
        onSuccess();
    };
}

export function load(onSuccess, onError) {
    try {
        let auth = authService.getUserDetails();
        return dispatch => {
            dispatch({type: actionTypes.LOAD, auth: auth});
            onSuccess();
        };
    } catch (e) {
        onError(e);
    }
}