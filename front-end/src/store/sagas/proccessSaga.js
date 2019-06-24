import { call, put } from 'redux-saga/effects';
import api from '../../services/api';

import processActions from '../ducks/proccessDucks';

export function* saveProccess({ params }) {
  const { name, history } = params;
  const payload = {
    name,
    status: 'Pendente',
    createdBY: '2',
    userID: ['1', '2'],
  };

  try {
    yield call(api.post, '/api/proccess', payload);
    yield put(processActions.saveSuccessProccess());
    history.push('/createProccess', 'success');
  } catch ({ response }) {
    let errorMsg = 'Tente novamente';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor.';
    }
    yield put(processActions.saveFailureProccess(errorMsg));
  }
}

export function* updateProccess({ params }) {
  const { id, name, history } = params;
  const payload = {
    name,
    status: 'Pendente',
    createdBY: '2',
    userID: ['1', '2'],
  };

  try {
    yield call(api.put, `/api/proccess/${id}`, payload);
    yield put(processActions.updateSuccessProccess());
    history.push('/createProccess', 'success');
  } catch ({ response }) {
    let errorMsg = 'Tente novamente';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor.';
    }
    yield put(processActions.updateFailureProccess(errorMsg));
  }
}

export function* deleteProccess({ id }) {
  try {
    const { data } = yield call(api.delete, `/api/proccess/${id}`);
    yield put(processActions.deleteSuccessProccess(data));
    yield call(getProccess);
  } catch (error) {
    yield put(processActions.deleteFailureProccess());
  }
}

export function* getProccess() {
  try {
    const { data } = yield call(api.get, '/api/proccess');
    yield put(processActions.loadSuccessProccess(data.data));
  } catch (error) {
    yield put(processActions.loadFailureProccess());
  }
}
