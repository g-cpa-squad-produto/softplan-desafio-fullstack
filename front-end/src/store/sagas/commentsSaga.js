import { call, put } from 'redux-saga/effects';
import api from '../../services/api';

import commentsActions from '../ducks/commentsDucks';

export function* saveComments({ params }) {
  const { comment, history } = params;
  const payload = { comment, userID: '1', proccessID: '2' };
  try {
    const { data } = yield call(api.post, '/api/comments', payload);
    yield put(commentsActions.saveSuccessComments(data));
    history.push('/createComments', 'success');
  } catch ({ response }) {
    let errorMsg = 'Tente novamente';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor';
    }
    yield put(commentsActions.saveFailureComments(errorMsg));
  }
}

export function* updateComments({ params }) {
  const { id, comment, history } = params;
  const payload = { comment, userID: '1', proccessID: '2' };

  try {
    const { data } = yield call(api.put, `/api/comments/${id}`, payload);
    yield put(commentsActions.updateSuccessComments(data));
    history.push('/createComments', 'success');
  } catch ({ response }) {
    let errorMsg = 'Tente novamente';
    if (response === undefined) {
      errorMsg = 'Erro de Servidor';
    }
    yield put(commentsActions.updateFailureUser(errorMsg));
  }
}

export function* deleteComments({ id }) {
  try {
    const { data } = yield call(api.delete, `/api/comments/${id}`);
    yield put(commentsActions.deleteSuccessComments(data));
    yield call(getComments);
  } catch (error) {
    yield put(commentsActions.deleteFailureComments());
  }
}

export function* getComments() {
  try {
    const { data } = yield call(api.get, '/api/comments');
    yield put(commentsActions.loadSuccessComments(data.data));
  } catch (error) {
    yield put(commentsActions.loadFailureComments());
  }
}
