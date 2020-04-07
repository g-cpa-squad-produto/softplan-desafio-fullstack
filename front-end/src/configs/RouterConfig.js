import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "../public/Login";
import Register from "../public/Register";
import Cookies from "universal-cookie";
import AdminRoutes from "../private/Admin";
import TriatorRoutes from "../private/Triator";
import FinisherRoutes from "../private/Finisher";
import Container from '@material-ui/core/Container';

const cookies = new Cookies();

export default class RouterConfig extends Component {
  render() {
    const token = cookies.get("token");
    const userType = cookies.get("userType");
    return (
      <div>
        {token != null && token !== "" ? (
          userType === "ADMIN" ? (
            <AdminRoutes />
          ) : userType === "TRIATOR" ? (
            <TriatorRoutes />
          ) : (
            <FinisherRoutes />
          )
        ) : (
          <Container>
            <Router class="RouterConfig" path="/">
              <Switch>
                <Route exact={true} path="/" component={Login}></Route>
                <Route exact={true} path="/login" component={Login}></Route>
                <Route path="/register" component={Register}></Route>
              </Switch>
            </Router>
          </Container>
        )}
      </div>
    );
  }
}
