import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AddUser from "./components/User/AddUser";
import AddProcesso from "./components/Processo/AddProcesso";
import { Provider } from "react-redux";
import store from "./store";
import DashboardProcessos from "./components/DashboardProcessos";
import DashboardProcessosPendentes from "./components/DashboardProcessosPendentes";
import AddParecer from "./components/Parecer/AddParecer";
import UpdateUser from "./components/User/UpdateUser";
import VisualizarParecer from "./components/Parecer/VisualizarParecer";
import Login from "./components/UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securtyUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/userActions";
import SecuredRoute from "./securtyUtils/SecuredRoute";
import DashboardPareceres from "./components/DashboardPareceres";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  console.log(decoded_jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Switch>
            <SecuredRoute exact path="/dashboard" component={Dashboard} />
            <SecuredRoute
              exact
              path="/dashboardProcessos"
              component={DashboardProcessos}
            />
            <SecuredRoute
              exact
              path="/dashboardPareceres/:usuarioId"
              component={DashboardPareceres}
            />
            <SecuredRoute
              exact
              path="/dashboardProcessosPedentes"
              component={DashboardProcessosPendentes}
            />
            <SecuredRoute exact path="/addUser" component={AddUser} />
            <SecuredRoute exact path="/addProcesso" component={AddProcesso} />
            <SecuredRoute
              exact
              path="/addParecer/:usuarioId/:processoId"
              component={AddParecer}
            />
            <SecuredRoute
              exact
              path="/visualizarParecer/:processoId"
              component={VisualizarParecer}
            />

            <SecuredRoute
              exact
              path="/updateUser/:usuarioId"
              component={UpdateUser}
            />
          </Switch>
          <Route exact path="/login" component={Login} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
