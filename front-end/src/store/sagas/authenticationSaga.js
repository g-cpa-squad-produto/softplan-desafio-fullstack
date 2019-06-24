import { call, put, select } from 'redux-saga/effects';
import api from '../../services/api';
import AuthenticationActions from '../ducks/authenticationDucks';

export function* login({ username, password, history }) {
  const payload = { username, password };
  try {
    const { data } = yield call(api.post, '/api/auth/login', payload);
    yield put(AuthenticationActions.loginSuccess(data));
    yield call(axiosInterceptors);
    history.push('/dashboard');
  } catch ({ response }) {
    let errorMsg = '';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor. Tente novamente.';
    } else {
      errorMsg = 'Login ou Senha inválidos.';
    }
    yield put(AuthenticationActions.loginFailure(errorMsg));
  }
}

export function* axiosInterceptors() {
  const token = yield select(state => state.AuthenticationReducer.token);

  api.interceptors.request.use((config) => {
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });

  return token;
}

export function getRole(data) {
  const { roles } = data;
  let singleRole = '';
  roles.forEach(({ role }) => {
    singleRole = role;
  });
  return singleRole;
}
