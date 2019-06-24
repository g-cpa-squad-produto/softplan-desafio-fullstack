import { createActions, createReducer } from 'reduxsauce';

/*
 * Action Types & Creators
 */
const { Types, Creators } = createActions({
  // load
  loadRequestComments: null,
  loadSuccessComments: ['data'],
  loadFailureComments: ['error'],
  // save
  saveRequestComments: ['params'],
  saveSuccessComments: null,
  saveFailureComments: ['error'],
  // update
  updateRequestComments: ['params'],
  updateSuccessComments: null,
  updateFailureComments: ['error'],
  // delete
  deleteRequestComments: ['id'],
  deleteSuccessComments: null,
  deleteFailureComments: ['error'],
});

export const CommentsTypes = Types;
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
export const CommentsReducer = createReducer(INITIAL_STATE, {
  // load
  [Types.LOAD_REQUEST_COMMENTS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.LOAD_SUCCESS_COMMENTS]: (state, { data }) => ({ ...state, data, fetching: false }),
  [Types.LOAD_FAILURE_COMMENTS]: (state, { error }) => ({ ...state, error, fetching: false }),

  // Save
  [Types.SAVE_REQUEST_COMMENTS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.SAVE_SUCCESS_COMMENTS]: (state, action) => ({
    ...state,
    fetching: false,
  }),
  [Types.SAVE_FAILURE_COMMENTS]: (state, { error }) => ({ ...state, error, fetching: false }),

  // update
  [Types.UPDATE_REQUEST_COMMENTS]: state => ({
    ...state,
    fetching: true,
    dataCOMMENTSs: [],
    error: null,
  }),
  [Types.UPDATE_SUCCESS_COMMENTS]: state => ({
    ...state,
    fetching: false,
  }),
  [Types.UPDATE_FAILURE_COMMENTS]: (state, { error }) => ({ ...state, error, fetching: false }),

  // delete
  [Types.DELETE_REQUEST_COMMENTS]: state => ({
    ...state,
    fetching: true,
    error: null,
  }),
  [Types.DELETE_SUCCESS_COMMENTS]: state => ({ ...state, fetching: false }),
  [Types.DELETE_FAILURE_COMMENTS]: (state, { error }) => ({ ...state, error, fetching: false }),
});
