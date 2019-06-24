import { createActions, createReducer } from 'reduxsauce';

/*
 * Action Types & Creators
 */
const { Types, Creators } = createActions({
  // load
  loadRequestProccess: null,
  loadSuccessProccess: ['data'],
  loadFailureProccess: ['error'],
  // save
  saveRequestProccess: ['params'],
  saveSuccessProccess: null,
  saveFailureProccess: ['error'],
  // update
  updateRequestProccess: ['params'],
  updateSuccessProccess: null,
  updateFailureProccess: ['error'],
  // delete
  deleteRequestProccess: ['id'],
  deleteSuccessProccess: null,
  deleteFailureProccess: ['error'],
});

export const ProccessTypes = Types;
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
export const ProccessReducer = createReducer(INITIAL_STATE, {
  // load
  [Types.LOAD_REQUEST_PROCCESS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.LOAD_SUCCESS_PROCCESS]: (state, { data }) => ({ ...state, data, fetching: false }),
  [Types.LOAD_FAILURE_PROCCESS]: (state, { error }) => ({ ...state, error, fetching: false }),

  // Save
  [Types.SAVE_REQUEST_PROCCESS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.SAVE_SUCCESS_PROCCESS]: (state, action) => ({
    ...state,
    fetching: false,
  }),
  [Types.SAVE_FAILURE_PROCCESS]: (state, { error }) => ({ ...state, error, fetching: false }),

  // update
  [Types.UPDATE_REQUEST_PROCCESS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.UPDATE_SUCCESS_PROCCESS]: state => ({
    ...state,
    fetching: false,
  }),
  [Types.UPDATE_FAILURE_PROCCESS]: (state, { error }) => ({ ...state, error, fetching: false }),

  // delete
  [Types.DELETE_REQUEST_PROCCESS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.DELETE_SUCCESS_PROCCESS]: state => ({ ...state, fetching: false }),
  [Types.DELETE_FAILURE_PROCCESS]: (state, { error }) => ({ ...state, error, fetching: false }),
});
