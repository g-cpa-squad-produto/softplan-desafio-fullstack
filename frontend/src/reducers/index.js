import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'

import login from './login'
import user from './user'

const rootReducer = combineReducers({
  form: formReducer,
  login: login,
  user: user,
})

export default rootReducer
