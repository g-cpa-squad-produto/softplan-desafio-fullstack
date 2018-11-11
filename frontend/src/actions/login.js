import axios from 'axios'

import {
  URL,
  USER_STORAGE_KEY,
  LOGIN,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  LOGOUT,
} from '../constants'

export const login = () => ({
  type: LOGIN
});

export const loginSuccess = () => ({
  type: LOGIN_SUCCESS
});

export const loginFailure = error => ({
  type: LOGIN_FAILURE,
  payload: { error }
});

export const logoutSuccess = () => ({
  type: LOGOUT
});

export function signIn({ email, password }, history) {
  return dispatch => {
    dispatch(login());
    return axios.post(`${URL}/login`, { email, password })
                .then(res => {
                  dispatch(loginSuccess());
                  localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(res.data));
                  history.push('/home');
                })
                .then(error => dispatch(loginFailure(error)))
                .catch(error => dispatch(loginFailure(error)));
  }
}

export function logout(history) {
  localStorage.clear();
  history.push('/');
  return (dispatch) => {
    dispatch(logoutSuccess());
  }
}

export function handleUser(history) {
  return (dispatch) => {
    const user = localStorage.getItem(USER_STORAGE_KEY);
    if(user) {
      if (history.location.pathname === '/') {
          history.push('/home');
      } else {
        history.push(history.location.pathname)
      }
      dispatch(loginSuccess());
    } else {
      dispatch(loginFailure());
    }
  };
}
