import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Logon from "./pages/Logon";
import Register from "./pages/Register";
import PrivateRoute from "./components/PrivateRoute";
import ProcessList from "./pages/ProcessList";
import NewProcess from "./pages/NewProcess";

export default function routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Logon} />
        <PrivateRoute path="/register" component={Register} />
        <PrivateRoute path="/process/new" component={NewProcess} />
        <PrivateRoute path="/process" component={ProcessList} />
      </Switch>
    </BrowserRouter>
  );
}
