import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "../public/Login";
import Register from "../public/Register";
import Cookies from "universal-cookie";

const cookies = new Cookies();

export default class RouterConfig extends Component {
  render() {
    const token = cookies.get("token");
    return (
      <Router class="RouterConfig" path="/">
        {token != null && token !== "" ? (
          <div>logado</div>
        ) : (
          <Switch>
            <Route exact={true} path="/" component={Login}></Route>
            <Route exact={true} path="/login" component={Login}></Route>
            <Route path="/register" component={Register}></Route>
          </Switch>
        )}
      </Router>
    );
  }
}
