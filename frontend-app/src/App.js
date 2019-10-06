import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddUser from "./components/User/AddUser";
import AddProcesso from "./components/Processo/AddProcesso";
import { Provider } from "react-redux";
import store from "./store";
import DashboardProcessos from "./components/DashboardProcessos";
import DashboardProcessosPendentes from "./components/DashboardProcessosPendentes";
import AddParecer from "./components/Parecer/AddParecer";
import UpdateUser from "./components/User/UpdateUser";
import VisualizarParecer from "./components/Parecer/VisualizarParecer";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route
            exact
            path="/dashboardProcessos"
            component={DashboardProcessos}
          />
          <Route
            exact
            path="/dashboardProcessosPedentes"
            component={DashboardProcessosPendentes}
          />
          <Route exact path="/addUser" component={AddUser} />
          <Route exact path="/addProcesso" component={AddProcesso} />
          <Route
            exact
            path="/addParecer/:usuarioId/:processoId"
            component={AddParecer}
          />
          <Route
            exact
            path="/visualizarParecer/:processoId"
            component={VisualizarParecer}
          />
          <Route exact path="/updateUser/:usuarioId" component={UpdateUser} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
