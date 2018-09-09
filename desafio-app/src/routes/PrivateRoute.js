import React, {Component} from "react";
import {Route, Redirect} from "react-router-dom";
import AppBarCustom from "../components/common/appBar/AppBarCustom";

class PrivateRoute extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isAuthenticated: this.props.auth.loggedIn
        };
    }

    render() {
        return this.state.isAuthenticated ?
            <div style={{display: 'flex'}}>
                <AppBarCustom />
                <Route {...this.props} />
            </div> :
            <Redirect to="/login"/>;
    }
}

export default PrivateRoute;