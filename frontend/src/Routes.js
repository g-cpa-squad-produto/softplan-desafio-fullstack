import React from "react";
import { Switch, Route, Redirect } from "react-router";

import Usuario from "./usuario/usuario";
import Processo from "./processo/processo";
import Home from "./components/Home";

export default props => (
  <Switch>
    <Route exact path="/" component={Home} />
    <Route path="/usuario" component={Usuario} />
    <Route path="/processo" component={Processo} />
    <Redirect from="*" to="/" />
  </Switch>
);
