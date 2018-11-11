import React from 'react'
import { Route, Redirect } from 'react-router-dom'

import { USER_STORAGE_KEY } from '../constants'

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={props => (
      localStorage.getItem(USER_STORAGE_KEY)
        ? <Component {...props} />
        : <Redirect to="/" />
    )}
  />
);

export default PrivateRoute
