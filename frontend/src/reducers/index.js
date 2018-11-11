import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'

import login from './login'

const rootReducer = combineReducers({
  form: formReducer,
  login: login,
})

export default rootReducer
