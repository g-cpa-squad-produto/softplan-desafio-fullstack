import axios from '../../services/axios-instance';
import AuthService from '../../services/AuthService';

const BASE_URL = `http://localhost:8080/api`;

export const CLICK_LOGIN = 'CLICK_LOGIN';
export const LOGIN_OK = 'LOGIN_OK';
export const LOGIN_ERROR = 'LOGIN_ERROR';

export const LOGOUT = 'LOGOUT';
export const LOGOUT_OK = 'LOGOUT_OK';

export const requestLogin = () => ({type: CLICK_LOGIN});
export const loginOk = tokens => ({type: LOGIN_OK, tokens});
export const loginFail = error => ({type: LOGIN_ERROR, error});

export const logoutAct = () => ({type: LOGOUT});
export const logoutOk = () => ({type: LOGOUT_OK});

export const login = (values, callback) => (dispatch) => {
    dispatch(requestLogin());
    return axios.post(`${BASE_URL}/oauth/token?grant_type=password&username=${values.username}&password=${values.password}`,
        {},
        {
            auth: {
                username: 'challenge_client',
                password: 'secret'
            }
        }).then(
        res => {
            AuthService.set(res.data);
            return axios.get(`${BASE_URL}/users/current`).then(user => {
                AuthService.setUser(user.data);
                dispatch(loginOk(res.data));
                callback({user: user.data, token: res.data});
            });
        },
        err => dispatch(loginFail(err))
    );
};

export const logout = (callback) => (dispatch) => {
    dispatch(logoutAct());
    const token = JSON.parse(AuthService.get());
    return axios.post(`${BASE_URL}/token/revoke?token=${token.access_token}`, {}).then(
        () => {
            AuthService.remove();
            dispatch(logoutOk());
            callback();
        }
    );
};

const defaultState = {
    isFetching: false
};

export default (state = defaultState, action) => {
    switch (action.type) {
        case CLICK_LOGIN:
            return {
                ...state,
                isFetching: true,
                logged: false
            };
        case LOGIN_OK:
            return {
                ...state,
                tokens: action.tokens,
                isFetching: false,
                error: null,
                logged: true
            };
        case LOGIN_ERROR:
            return {
                ...state,
                error: action.error,
                isFetching: false,
                logged: false
            };
        case LOGOUT:
            return {
                ...state,
                logout: true
            };
        case LOGOUT_OK:
            return {
                ...state,
                logout: false,
                logged: false
            };
        default:
            return state;
    }
}