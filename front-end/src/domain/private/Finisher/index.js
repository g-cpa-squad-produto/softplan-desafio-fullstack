import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ProcessesList from './ProcessesList';

export default class FinisherRoutes extends Component {
  render() {
    return (
      <Router class="FinisherRoutes" path="/">
        <Switch>
          <Route exact={true} path="/" component={ProcessesList} />
          <Route exact={true} path="*" component={ProcessesList} />
        </Switch>
      </Router>
    );
  }
}
