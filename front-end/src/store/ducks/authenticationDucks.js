import { createActions, createReducer } from 'reduxsauce';

/*
 * Action Types & Creators
 */
const { Types, Creators } = createActions({
  loginRequest: ['username', 'password', 'history'],
  loginSuccess: ['data'],
  loginFailure: ['error'],
  logout: null,
});

export const AuthenticationTypes = Types;
export default Creators;

/*
 * Initial State
 */
export const INITIAL_STATE = {
  username: null,
  role: null,
  error: null,
  fetching: false,
  token: null,
  isLogged: false,
};

/*
 * Reducer
 */
export const AuthenticationReducer = createReducer(INITIAL_STATE, {
  [Types.LOGIN_REQUEST]: state => ({ ...state, fetching: true, error: null }),
  [Types.LOGIN_SUCCESS]: (state, { data, data: { username, token, roles } }) => ({
    ...state,
    username,
    role: roles[0].role,
    token,
    fetching: false,
    isLogged: true,
  }),
  [Types.LOGIN_FAILURE]: (state, { error }) => ({ ...state, fetching: false, error }),
  [Types.LOGOUT]: state => ({
    ...state,
    username: null,
    role: null,
    error: null,
    fetching: false,
    token: null,
    isLogged: false,
  }),
});
