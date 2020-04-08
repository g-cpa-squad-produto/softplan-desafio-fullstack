import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ProcessesList from './ProcessesList';
import ProcessesAdd from './ProcessesAdd';

export default class TriatorRoutes extends Component {
  render() {
    return (
      <Router class="TriatorRoutes" path="/">
        <Switch>
          <Route exact={true} path="/" component={ProcessesList} />
          <Route path="/add" component={ProcessesAdd} />
          <Route path="*" component={ProcessesList} />
        </Switch>
      </Router>
    );
  }
}
