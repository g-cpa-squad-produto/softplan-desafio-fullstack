import { call, put } from 'redux-saga/effects';
import api from '../../services/api';

import userActions from '../ducks/userDucks';

export function* saveUser({ params }) {
  const { username, password, role, history } = params;
  const payload = { username, password, roles: [role] };
  try {
    const { data } = yield call(api.post, '/api/users', payload);
    yield put(userActions.saveSuccessUser(data));
    history.push('/createUser','success');
  } catch ({ response }) {
    let errorMsg = 'Erro de servidor. tente novamente';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor. Tente novamente.';
    } else if (response.data.errors.length > 0) {
      if (response.data.errors[0] === 'User already exists') {
        errorMsg = 'Usuário já cadastrado.';
      }
    }
    yield put(userActions.saveFailureUser(errorMsg));
  }
}

export function* updateUser({ params }) {
  const {
    id, username, password, role,history
  } = params;
  const payload = { username, password, roles: [role] };

  try {
    const { data } = yield call(api.post, `/api/users/${id}`, payload);
    yield put(userActions.updateSuccessUser(data));
    history.push('/createUser','success');
  } catch ({ response }) {
    let errorMsg = 'Erro de servidor. tente novamente';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor. Tente novamente.';
    } else if (response.data.errors.length > 0) {
      if (response.data.errors[0] === 'User already exists') {
        errorMsg = 'Usuário já cadastrado.';
      }
    }
    yield put(userActions.updateFailureUser(errorMsg));
  }
}

export function* deleteUser({ id }) {
  
  try {
    const { data } = yield call(api.delete, `/api/users/${id}`);
    yield put(userActions.deleteSuccessUser(data));
    yield call(getUsers);
  } catch (error) {
    yield put(userActions.deleteFailureUser());
  }
}

export function* getUsers() {
  try {
    const { data } = yield call(api.get, '/api/users');
    yield put(userActions.loadSuccessUser(data.data));
  } catch (error) {
    yield put(userActions.loadFailureUser());
  }
}
