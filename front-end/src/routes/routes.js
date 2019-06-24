import React from 'react';
import { Route, Switch } from 'react-router-dom';
import CreateUser from '../pages/CreateUser/CreateUser';
import CreateProccess from '../pages/CreateProccess/CreateProccess';
import CreateComments from '../pages/CreateComments/CreateComments';
import Login from '../pages/Login/Login';
import Dashboard from '../pages/Dashboard/Dashboard';
import PrivateRoute from './PrivateRoute';

const Routes = () => (
  <Switch>
    <Route exact path="/" component={Login} />
    <PrivateRoute path="/createuser" component={CreateUser} />
    <PrivateRoute path="/dashboard" component={Dashboard} />
    <PrivateRoute path="/createProccess" component={CreateProccess} />
    <PrivateRoute path="/createComments" component={CreateComments} />
    <Route path="*" component={() => <h1>Página não encontrada</h1>} />
  </Switch>
);

export default Routes;
