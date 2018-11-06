import React from "react";
import { Switch, Route, Redirect } from "react-router";

import Usuario from "./usuario/usuario";
import Processo from "./processo/processo";
import Parecer from "./parecer/parecer";
import Home from "./components/Home";

export default props => (
  <Switch>
    <Route exact path="/" component={Home} />
    <Route path="/usuario" component={Usuario} />
    <Route path="/processo" component={Processo} />
    <Route path="/parecer" component={Parecer} />
    <Redirect from="*" to="/" />
  </Switch>
);
