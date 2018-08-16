import React, {Component} from 'react';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import {Provider} from 'react-redux';
import store from './store';
import jwt_decode from 'jwt-decode';
import setAuthToken from './setAuthToken';
import {setCurrentUser, logoutUser} from './actions/authentication';

import Navbar from './components/Navbar';
import Login from './components/Login';
import Home from './components/Home';

import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Register from "./components/Register";

if (localStorage.jwtToken) {
    setAuthToken(localStorage.jwtToken);
    const decoded = jwt_decode(localStorage.jwtToken);
    store.dispatch(setCurrentUser(decoded));
    const currentTime = Date.now() / 1000;
    if (decoded.exp < currentTime) {
        store.dispatch(logoutUser());
        window.location.href = '/login'
    }
}

const PrivateRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        store.getState().auth.isAuthenticated ? (
            <Component {...props} />
        ) : (
            <Redirect to={{
                pathname: '/login',
                state: {from: props.location}
            }}/>
        )
    )}/>
)

class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <Router >
                    <main>
                        <Navbar/>
                        <div className="container">
                            <PrivateRoute exact path="/" component={Home}/>
                            <PrivateRoute exact path="/register" component={Register}/>
                            <Route exact path="/login" component={Login}/>
                        </div>
                    </main>

                </Router>
            </Provider>
        );
    }
}

export default App;
