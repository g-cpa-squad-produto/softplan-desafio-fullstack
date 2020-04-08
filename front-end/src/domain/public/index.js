import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import { Container } from "@material-ui/core";

export default class PublicRoutes extends Component {
  render() {
    return (
      <Container>
        <Router class="PublicRoutes" path="/">
          <Switch>
            <Route exact={true} path="/" component={Login} />
            <Route path="/register" component={Register} />
            <Route path="*" component={Login} />
          </Switch>
        </Router>
      </Container>
    );
  }
}
