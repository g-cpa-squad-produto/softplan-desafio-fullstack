import React, {Component} from 'react';
import {Switch, Route, withRouter} from 'react-router-dom';
import Login from '../components/login/Login';
import NotFound from '../components/default/not-found/NotFound';
import PrivateRoute from "./PrivateRoute";
import UserList from "../view/users/list/UserList";

class GeneralRoutes extends Component {
    render() {
        return (
            <Switch>
                <Route path="/" exact component={Login}/>
                <Route path="/login" component={Login}/>
                <PrivateRoute path="/usuarios" component={UserList}/>
                {/*<Route path="/login"
                       render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>*/}
                {/*<PrivateRoute authenticated={this.state.isAuthenticated} path="/poll/new" component={NewPoll}
                              handleLogout={this.handleLogout}/>*/}
                <Route component={NotFound}/>
            </Switch>
        );
    }
}

export default withRouter(GeneralRoutes);