import React, {Component} from 'react';
import './App.css';
import {getCurrentUser} from "./utils/api";
import {BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import {ACCESS_TOKEN} from "./utils/constants";

import AppNavbar from "./fragments/AppNavbar";
import Footer from "./fragments/Footer";

import PrivateRoute from "./utils/PrivateRoute";
import Login from "./pages/auth/Login";
import Home from './pages/Home';
import UserList from './pages/user/UserList';
import UserEdit from "./pages/user/UserEdit";
import ProcessList from "./pages/process/ProcessList";
import ProcessEdit from "./pages/process/ProcessEdit";
import OpinionList from "./pages/opinions/OpinionList";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: true,
            redirect: false
        }
    }

    getCurrentUser = () => {
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).finally(() => {
            this.setState({isLoading: false, redirect: '/'});
        });
    };

    loadCurrentUser = () => {
        this.setState({isLoading: true});
        this.getCurrentUser();
    }

    componentDidMount() {
        this.loadCurrentUser();
    }

    handleLogout = () => {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
            currentUser: null,
            isAuthenticated: false,
            redirect: "/login"
        });
    }

    handleLogin = () => {
        this.loadCurrentUser();
    }

    render() {
        if (this.state.isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }
        return (
            <Router>
                {this.state.redirect && <Redirect to={this.state.redirect}/>}
                <AppNavbar
                    isAuthenticated={this.state.isAuthenticated}
                    currentUser={this.state.currentUser}
                    handleLogout={this.handleLogout}/>
                <div className="mb-5 pb-5">
                    <Switch>
                        <PrivateRoute
                            authenticated={this.state.isAuthenticated}
                            currentUser={this.state.currentUser}
                            path="/"
                            exact={true}
                            component={Home}/>
                        <PrivateRoute
                            authenticated={this.state.isAuthenticated}
                            currentUser={this.state.currentUser}
                            path="/users"
                            exact={true}
                            component={UserList}/>
                        <PrivateRoute
                            authenticated={this.state.isAuthenticated}
                            currentUser={this.state.currentUser}
                            path="/users/:id"
                            exact={true}
                            component={UserEdit}/>
                        <PrivateRoute
                            authenticated={this.state.isAuthenticated}
                            currentUser={this.state.currentUser}
                            path="/process"
                            exact={true}
                            component={ProcessList}/>
                        <PrivateRoute
                            authenticated={this.state.isAuthenticated}
                            currentUser={this.state.currentUser}
                            path="/process/:id"
                            exact={true}
                            component={ProcessEdit}/>
                        <PrivateRoute
                            authenticated={this.state.isAuthenticated}
                            currentUser={this.state.currentUser}
                            path="/opinions/process/:id"
                            exact={true}
                            component={OpinionList}/>
                        <Route
                            path="/login"
                            render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>
                    </Switch>
                </div>
                <Footer/>
            </Router>
        )
    }
}

export default App;
