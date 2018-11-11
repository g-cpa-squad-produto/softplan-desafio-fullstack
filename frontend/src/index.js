import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import reduxThunk from 'redux-thunk'
import { handleUser } from './actions/login'
import reducers from './reducers'
import createBrowserHistory from "history/createBrowserHistory"

import AppRouter from './containers/AppRouter'
import './css/style.css'

const history = createBrowserHistory()
const createStoreWithMiddleware = applyMiddleware(reduxThunk)(createStore);
const store = createStoreWithMiddleware(reducers);
store.dispatch(handleUser(history))

ReactDOM.render(
  <Provider store={store}>
    <AppRouter />
  </Provider>,
  document.getElementById('root')
)
