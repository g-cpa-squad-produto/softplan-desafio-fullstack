import React, {Component} from 'react';
import {Switch, Route, withRouter} from 'react-router-dom';
import {Authorization} from '../components/auth/Authorization';
import Login from '../components/login/Login';
import NotFound from '../components/default/notFound/NotFound';
import NotAuthorized from "../components/default/notAuthorized/NotAuthorized";
import PrivateRoute from "./PrivateRoute";
import UserList from "../view/users/list/UserList";
import Dashboard from "../components/dashboard/Dashboard";

const Admin = Authorization(['ROLE_ADMIN']);
const Triador = Authorization(['ROLE_TRIADOR']);
const Finalizador = Authorization(['ROLE_FINALIZADOR']);

class GeneralRoutes extends Component {
    render() {
        return (
            <Switch>
                <PrivateRoute path="/" exact component={Dashboard} {...this.props} />
                <PrivateRoute path="/usuarios" component={Admin(UserList)} {...this.props} />
                <Route path="/login" render={() => <Login {...this.props} />} />
                <Route path="/401" component={NotAuthorized} />
                <Route component={NotFound} />
            </Switch>
        );
    }
}

export default withRouter(GeneralRoutes);