import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AdminUsers from "./AdminUsers";

export default class AdminRoutes extends Component {
  render() {
    return (
      <Router className="AdminRoutes" path="/">
        <Switch>
          <Route exact={true} path="/" component={AdminUsers} />
          <Route path="*" component={AdminUsers} />
        </Switch>
      </Router>
    );
  }
}
