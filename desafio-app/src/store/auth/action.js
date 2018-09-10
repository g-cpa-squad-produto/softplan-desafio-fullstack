import {authService} from '../../services/index';

export const actionTypes = {
    LOAD: 'LOAD',
    LOGOUT: 'LOGOUT'
};

export function login(username, password, onSuccess, onError) {
    authService.login(username, password, onSuccess, onError);
    return load(null, onError);
}

export function logout(onSuccess) {
    authService.logout(onSuccess); // remove user details do cookie
    return dispatch => {
        dispatch({type: actionTypes.LOGOUT}); // "reseta" user details do redux
    };
}

export function load(onSuccess, onError) {
    try {
        let auth = authService.getUserDetails();
        return dispatch => {
            dispatch({type: actionTypes.LOAD, auth: auth});
            if (onSuccess) onSuccess();
        };
    } catch (e) {
        onError(e);
    }
}