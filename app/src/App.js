import React, {Component} from 'react';
import './App.css';
import Home from './Home';
import {BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import UserList from './user/UserList';
import UserEdit from "./user/UserEdit";
import ProcessList from "./process/ProcessList";
import ProcessEdit from "./process/ProcessEdit";
import ProcessUsers from "./process/ProcessUsers";
import Login from "./auth/Login";
import AppNavbar from "./fragments/AppNavbar";
import PrivateRoute from "./utils/PrivateRoute";
import { ACCESS_TOKEN } from "./utils/constants";
import { getCurrentUser} from "./utils/api";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: false,
            redirect: false
        }
    }

    getCurrentUser = () => {
        this.setState({ isLoading: true });
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).catch(error => {
            this.setState({
                isLoading: false
            });
        });
    };

    loadCurrentUser = () => {
        this.setState({
            isLoading: true
        });
        this.getCurrentUser();
    }

    componentDidMount() {
        this.loadCurrentUser();
    }

    handleLogout = (redirectTo = "/", notificationType = "success", description = "Logout efetuado.") => {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
            currentUser: null,
            isAuthenticated: false
        });
        this.setState({redirect: redirectTo});
    }

    handleLogin = () => {
        this.loadCurrentUser();
        this.setState({redirect: '/'});
    }

    render() {

        if (this.state.isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }
        return (
            <Router>
                { this.state.redirect && <Redirect to={this.state.redirect}/> }
                <AppNavbar/>
                <Switch>
                    <Route
                        path='/'
                        exact={true}
                        render={
                            (props) => <Home isAuthenticated={this.state.isAuthenticated}
                                             currentUser={this.state.currentUser}
                                             handleLogout={this.handleLogout} {...props} />}/>
                    <PrivateRoute
                        authenticated={this.state.isAuthenticated}
                        path="/users"
                        exact={true}
                        component={UserList} handleLogout={this.handleLogout}/>
                    <PrivateRoute
                        authenticated={this.state.isAuthenticated}
                        path="/users/:id"
                        exact={true}
                        component={UserEdit} handleLogout={this.handleLogout}/>
                    <PrivateRoute
                        authenticated={this.state.isAuthenticated}
                        path="/process"
                        exact={true}
                        component={ProcessList} handleLogout={this.handleLogout}/>
                    <PrivateRoute
                        authenticated={this.state.isAuthenticated}
                        path="/process/:id"
                        exact={true}
                        component={ProcessEdit} handleLogout={this.handleLogout}/>
                    <PrivateRoute
                        authenticated={this.state.isAuthenticated}
                        path="/process/users/:id"
                        exact={true}
                        component={ProcessUsers} handleLogout={this.handleLogout}/>
                    <Route
                        path="/login"
                        render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
