import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

export default function routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/login" component={Logon} />
        <Route path="/register" component={Register} />
        <PrivateRoute path="/process" component={ProcessList} />
      </Switch>
    </BrowserRouter>
  );
}
