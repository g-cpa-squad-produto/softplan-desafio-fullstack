import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AdminUsers from './List';
import AdminAddUser from './Add';
import AdminEditUser from './Edit';

export default class AdminRoutes extends Component {
  render() {
    return (
      <Router class="RouterConfig" path="/">
        <Switch>
          <Route exact={true} path="/" component={AdminUsers}></Route>
          <Route path="/add" component={AdminAddUser}></Route>
          <Route path="/edit" component={AdminEditUser}></Route>
          <Route path="*" component={AdminUsers}></Route>
        </Switch>
      </Router>
    );
  }
}
