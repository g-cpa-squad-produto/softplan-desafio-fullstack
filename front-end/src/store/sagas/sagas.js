import { all, takeLatest } from 'redux-saga/effects';
import { UserTypes } from '../ducks/userDucks';
import { AuthenticationTypes } from '../ducks/authenticationDucks';
import { ProccessTypes } from '../ducks/proccessDucks';
import { CommentsTypes } from '../ducks/commentsDucks';
import {
  saveUser, getUsers, updateUser, deleteUser,
} from './userSaga';
import { login } from './authenticationSaga';
import {
  getProccess, deleteProccess, saveProccess, updateProccess,
} from './proccessSaga';
import {getComments, saveComments, updateComments, deleteComments} from './commentsSaga'

export default function* rootSaga() {
  return yield all([
    // Users
    takeLatest(UserTypes.LOAD_REQUEST_USER, getUsers),
    takeLatest(UserTypes.SAVE_REQUEST_USER, saveUser),
    takeLatest(UserTypes.UPDATE_REQUEST_USER, updateUser),
    takeLatest(UserTypes.DELETE_REQUEST_USER, deleteUser),
    takeLatest(AuthenticationTypes.LOGIN_REQUEST, login),
    // Process
    takeLatest(ProccessTypes.LOAD_REQUEST_PROCCESS, getProccess),
    takeLatest(ProccessTypes.SAVE_REQUEST_PROCCESS, saveProccess),
    takeLatest(ProccessTypes.DELETE_REQUEST_PROCCESS, deleteProccess),
    takeLatest(ProccessTypes.UPDATE_REQUEST_PROCCESS, updateProccess),
    // Comments
    takeLatest(CommentsTypes.LOAD_REQUEST_COMMENTS, getComments),
    takeLatest(CommentsTypes.SAVE_REQUEST_COMMENTS, saveComments),
    takeLatest(CommentsTypes.DELETE_REQUEST_COMMENTS, deleteComments),
    takeLatest(CommentsTypes.UPDATE_REQUEST_COMMENTS, updateComments),
  ]);
}
