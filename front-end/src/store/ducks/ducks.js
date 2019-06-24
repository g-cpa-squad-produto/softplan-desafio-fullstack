import { combineReducers } from 'redux';
import { UserReducer } from './userDucks';
import { ProccessReducer } from './proccessDucks';
import { CommentsReducer } from './commentsDucks';

import { AuthenticationReducer, AuthenticationTypes } from './authenticationDucks';

const appReducer = combineReducers({
  UserReducer,
  AuthenticationReducer,
  ProccessReducer,
  CommentsReducer,
});

const rootReducer = (state, action) => {
  if (action.type === AuthenticationTypes.LOGOUT) {
    state = undefined;
  }

  return appReducer(state, action);
};

export default rootReducer;
