import React, {Component} from 'react';
import {Switch, Route, withRouter} from 'react-router-dom';
import {Authorization} from '../components/auth/Authorization';
import Login from '../components/login/Login';
import NotFound from '../components/default/notFound/NotFound';
import NotAuthorized from "../components/default/notAuthorized/NotAuthorized";
import PrivateRoute from "./PrivateRoute";
import UserList from "../view/users/list/UserList";
import Dashboard from "../view/dashboard/Dashboard";
import UserDetail from "../view/users/detail/UserDetail";
import UserForm from "../view/users/form/UserForm";
import ProcessList from "../view/process/triador/list/ProcessList";
import ProcessForm from "../view/process/triador/form/ProcessForm";
import ProcessDetail from "../view/process/triador/detail/ProcessDetail";
import ProcessFinalizadorList from "../view/process/finalizador/list/ProcessFinalizadorList";
import ProcessFinalizadorForm from "../view/process/finalizador/form/ProcessFinalizadorForm";

const Admin = Authorization(['ROLE_ADMIN']);
const Triador = Authorization(['ROLE_TRIADOR']);
const Finalizador = Authorization(['ROLE_FINALIZADOR']);

class GeneralRoutes extends Component {
    render() {
        return (
            <Switch>
                <PrivateRoute path="/" exact component={Dashboard} {...this.props} />

                <PrivateRoute path="/usuarios" exact component={Admin(UserList)} {...this.props} />
                <PrivateRoute path="/usuarios/cadastrar" component={Admin(UserForm)} {...this.props} />
                <PrivateRoute path="/usuarios/:id" component={Admin(UserDetail)} {...this.props} />

                <PrivateRoute path="/processos" exact component={Triador(ProcessList)} {...this.props} />
                <PrivateRoute path="/processos/cadastrar" component={Triador(ProcessForm)} {...this.props} />

                <PrivateRoute path="/processos/finalizadores" exact component={Finalizador(ProcessFinalizadorList)} {...this.props} />
                <PrivateRoute path="/processos/finalizadores/:id" component={Finalizador(ProcessFinalizadorForm)} {...this.props} />

                <PrivateRoute path="/processos/:id" component={Triador(ProcessDetail)} {...this.props} />

                <Route path="/login" render={() => <Login {...this.props} />} />
                <Route path="/401" component={NotAuthorized} />
                <Route component={NotFound} />
            </Switch>
        );
    }
}

export default withRouter(GeneralRoutes);