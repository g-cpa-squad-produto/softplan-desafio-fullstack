import React, {Component} from "react";
import {Route, Redirect} from "react-router-dom";
import {AuthService} from "../services/AuthService";

class PrivateRoute extends Component {
    render() {
        return AuthService.isAuthenticated ?
            <Route {...this.props} /> :
            <Redirect to="/login"/>;
    }
}

export default PrivateRoute;