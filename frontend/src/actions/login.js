import axios from 'axios'

import {
  URL,
  USER_STORAGE_KEY,
  LOGIN,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
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

export function signIn({ email, password }, history) {
  return dispatch => {
    dispatch(login());
    return axios.post(`${URL}/login`, { email, password })
                .then(res => {
                  debugger;
                  dispatch(loginSuccess());
                  localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(res.data));
                  history.push('/home');
                })
                .then(error => dispatch(loginFailure(error)))
                .catch(error => dispatch(loginFailure(error)));
  }
}
