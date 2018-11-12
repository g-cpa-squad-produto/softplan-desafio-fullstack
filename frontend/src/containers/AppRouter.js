import React, { Component } from 'react'
import {
  BrowserRouter as Router,
  Route,
  Switch
} from 'react-router-dom'

import Login from './Login'
import PrivateRoute from './PrivateRoute'
import Home from './Home'
import User from './User'
import NewUser from './NewUser'

class AppRouter extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path="/" exact component={Login} />
          <PrivateRoute path="/home" component={Home} />
          <PrivateRoute path="/user" component={User} />
          <PrivateRoute path="/userNew" component={NewUser} />
          <Route path="*" component={Login} />
        </Switch>
      </Router>
    )
  }
}

export default AppRouter;
