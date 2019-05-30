import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

import { Header } from '../components/header';
import { PrivateRoute } from '../components';
import { HomePage } from '../pages/home';
import { LoginPage } from '../pages/login';
import { AdminPage } from '../pages/admin';
import { TriagemPage } from '../pages/triagem';
import { FinalizacaoPage } from '../pages/finalizacao';

class App extends Component {

    render() {
        return (
            <div className="container">
                <Router>
                    <div>
                        <Header/>
                        <br />
                        <PrivateRoute exact path="/" component={HomePage} />
                        <PrivateRoute path="/admin" component={AdminPage} />
                        <PrivateRoute path="/triagem" component={TriagemPage} />
                        <PrivateRoute path="/finalizacao" component={FinalizacaoPage} />
                        <Route path="/login" component={LoginPage} />
                    </div>
                </Router>
            </div>
        );
    }
    
}

export { App }; 