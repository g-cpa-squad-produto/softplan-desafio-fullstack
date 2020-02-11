import React, { Fragment } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { Redirect } from 'react-router';

import { isAuthenticated } from "./services/auth";

import Login from "./views/login/Login";
import Logout from "./views/logout/Logout"
import Home from "./views/home/Index";
import ListUsers from "./views/user/List"
import FormUsers from "./views/user/Form"
import NotFoundComponent from "./views/NotFoundComponent";

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render = {(props) => (isAuthenticated() ? <Component {...props} /> : <Redirect to={{ pathname: "/login", state: { from: props.location } }} />)}
  />
);

const Routes = () => (
  <BrowserRouter>
    <Fragment>
      <Switch>
        <Route exact path="/login" component={Login} />
        <Route exact path="/logout" component={Logout} />
        <PrivateRoute exact path="/" component={Home} />
        <PrivateRoute path="/users/list" component={ListUsers} />
        <PrivateRoute path="/users/form" component={FormUsers} />
        <Route path="*" component={NotFoundComponent} />
      </Switch>
    </Fragment>
  </BrowserRouter>
);

export default Routes;