import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AdminUsers from "./ListUsers";
import AdminAddUser from "./AddUser";
import AdminEditUser from "./EditUser";

export default class AdminRoutes extends Component {
  render() {
    return (
      <Router className="AdminRoutes" path="/">
        <Switch>
          <Route exact={true} path="/" component={AdminUsers} />
          <Route path="/add" component={AdminAddUser} />
          <Route path="/edit" component={AdminEditUser} />
          <Route path="*" component={AdminUsers} />
        </Switch>
      </Router>
    );
  }
}
