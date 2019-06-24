import { createActions, createReducer } from 'reduxsauce';

/*
 * Action Types & Creators
 */
const { Types, Creators } = createActions({
  // load
  loadRequestUser: null,
  loadSuccessUser: ['data'],
  loadFailureUser: ['error'],
  // save
  saveRequestUser: ['params'],
  saveSuccessUser: null,
  saveFailureUser: ['error'],
  // update
  updateRequestUser: ['params'],
  updateSuccessUser: null,
  updateFailureUser: ['error'],
  // delete
  deleteRequestUser: ['id'],
  deleteSuccessUser: null,
  deleteFailureUser: ['error'],
});

export const UserTypes = Types;
export default Creators;

/*
 * Initial State
 */
export const INITIAL_STATE = {
  data: [],
  error: null,
  fetching: false,
};

/*
 * Reducer
 */
export const UserReducer = createReducer(INITIAL_STATE, {
  // load
  [Types.LOAD_REQUEST_USER]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.LOAD_SUCCESS_USER]: (state, { data }) => ({ ...state, data, fetching: false }),
  [Types.LOAD_FAILURE_USER]: (state, { error }) => ({ ...state, error, fetching: false }),

  // Save
  [Types.SAVE_REQUEST_USER]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.SAVE_SUCCESS_USER]: (state, action) => ({
    ...state,
    fetching: false,
  }),
  [Types.SAVE_FAILURE_USER]: (state, { error }) => ({ ...state, error, fetching: false }),

  // update
  [Types.UPDATE_REQUEST_USER]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.UPDATE_SUCCESS_USER]: state => ({
    ...state,
    fetching: false,
  }),
  [Types.UPDATE_FAILURE_USER]: (state, { error }) => ({ ...state, error, fetching: false }),

  // delete
  [Types.DELETE_REQUEST_USER]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.DELETE_SUCCESS_USER]: state => ({ ...state, fetching: false }),
  [Types.DELETE_FAILURE_USER]: (state, { error }) => ({ ...state, error, fetching: false }),
});
