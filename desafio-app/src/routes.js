import React, {Component} from 'react';
import {Route, withRouter, Switch} from 'react-router-dom';
import App from "./app/App";
import Login from "./components/login/Login";

class Routes extends Component {
    render() {
        return (
            <Switch>
                <Route path="/" exact component={App}/>
                <Route path="/login" component={Login}/>
                {/*<Route path="/login"
                       render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>*/}
                {/*<PrivateRoute authenticated={this.state.isAuthenticated} path="/poll/new" component={NewPoll}
                              handleLogout={this.handleLogout}/>*/}
                {/*<Route component={NotFound}/>*/}
            </Switch>
        );
    }
}

export default withRouter(Routes);