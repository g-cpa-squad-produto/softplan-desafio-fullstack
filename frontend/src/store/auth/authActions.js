import {
    AUTH_LOGIN_FAILURE,
    AUTH_LOGIN_SUCCESS,
    AUTH_LOGIN,
    AUTH_LOGOUT,
    AUTH_RESET,    
} from "./authTypes";

export const login = (email, password) => ({
    type: AUTH_LOGIN,
    payload: {
        email,
        password
    }
});

export const logout = () => ({
    type: AUTH_LOGOUT
});

export const loginFailure = error => ({
    type: AUTH_LOGIN_FAILURE,
    payload: {
        error
    }
});

export const loginSuccess = user => ({
    type: AUTH_LOGIN_SUCCESS,
    payload: {
        user
    }
});

export const reset = () => ({
    type: AUTH_RESET
});