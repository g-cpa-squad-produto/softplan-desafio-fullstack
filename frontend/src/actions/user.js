import axios from 'axios';
import {
  URL,
  USER_STORAGE_KEY,
  LIST_USER,
  LIST_USER_SUCCESS,
  LIST_USER_ERROR,
  SAVE_USER,
  SAVE_USER_SUCCESS,
  SAVE_USER_ERROR,
} from '../constants'

export const listUser = () => ({
  type: LIST_USER
});

export const listUserSuccess = users => ({
  type: LIST_USER_SUCCESS,
  payload: { users }
});

export const listUserFailure = error => ({
  type: LIST_USER_ERROR,
  payload: { error }
});

export function list () {
  let token = JSON.parse(localStorage.getItem(USER_STORAGE_KEY)).token
  return dispatch => {
    dispatch(listUser());
    return axios.get(`${URL}/users`, { headers: { Authorization: token }, params: { page: 0, offset: 10 } })
                .then(res => {
                  dispatch(listUserSuccess(res.data));
                  return res.data;
                })
                .then(error => dispatch(listUserFailure(error)))
                .catch(error => dispatch(listUserFailure(error)));
  };
}

export const savingUser = () => ({
  type: SAVE_USER
});

export const saveUserSuccess = user => ({
  type: SAVE_USER_SUCCESS,
  payload: { user }
});

export const saveUserFailure = error => ({
  type: SAVE_USER_ERROR,
  payload: { error }
});

export function saveUser({ name, email, password, role }, history) {
  let token = JSON.parse(localStorage.getItem(USER_STORAGE_KEY)).token
  return dispatch => {
    dispatch(savingUser());
    return axios.post(`${URL}/users/new`, { name, email, password, role }, { headers: { Authorization: token } })
                .then(res => {
                  dispatch(saveUserSuccess(res.data));
                  history.push('/user');
                })
                .then(error => dispatch(saveUserFailure(error)))
                .catch(error => dispatch(saveUserFailure(error)));
  }
}
