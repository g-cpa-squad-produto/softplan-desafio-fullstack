import { createStore, combineReducers } from 'redux'
import {reducer as toastrReducer} from 'react-redux-toastr'

const reducers = {
  toastr: toastrReducer
}
const reducer = combineReducers(reducers)
const store = createStore(reducer)

export default store;