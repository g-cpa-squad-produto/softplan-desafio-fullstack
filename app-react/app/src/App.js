import React, {Component} from 'react';
import './App.css';
import {Provider} from 'react-redux';
import {Router, Route, Switch} from 'react-router-dom';
import {Store} from './store';

import Login from './containers/login/Login';
import Users from './containers/users/Users';
import Process from './containers/process/Process';
import Parecer from './containers/parecer/Parecer';
import AppBarLogin from './components/AppBarLogin';
import AppBar from './containers/app-bar/AppBar';
import httpService from './services/http-service';
import {history} from './services/history';

import './App.css';
import 'typeface-roboto';

httpService.setupInterceptors();

class App extends Component {
    render() {
        return (
            <Provider store={Store}>
                <Router history={history}>
                    <div>
                        <div>
                            <Switch>
                                <Route path="/" exact={true} component={AppBarLogin}/>
                                <Route path="/**" component={AppBar}/>
                            </Switch>
                        </div>
                        <Switch>
                            <Route path="/" exact={true} component={Login}/>
                            <Route path="/users" component={Users}/>
                            <Route path="/process" component={Process}/>
                            <Route path="/parecer" component={Parecer}/>
                        </Switch>
                    </div>
                </Router>
            </Provider>
        );
    }
}

export default App;
