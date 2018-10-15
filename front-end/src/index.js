import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import {isAuth} from './Methods';
import * as User from './Views/User';
import * as Process from './Views/Process';
import * as Sight from './Views/Sight';
import Login from './Views/Login';
import NotFound from './Views/NotFound';

import Base from './Components/Base';

const AppRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={(props) => (
        isAuth()
            ? <Base><Component {...props} /></Base>
            : <Redirect to='/login' />
    )} />
);


const LoginRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={(props) => (
        isAuth()
            ? <Redirect to='/' />
            : <Component {...props} />
    )} />
);

ReactDOM.render(
    <Router>
        <Switch>
            <LoginRoute exact path="/login" component={Login} />

            <AppRoute exact path="/usuario" component={User.List} />
            <AppRoute exact path="/usuario/cadastro" component={User.Form} />
            <AppRoute exact path="/usuario/editar/:id" component={User.Form} />

            <AppRoute exact path="/processo" component={Process.List} />
            <AppRoute exact path="/processo/cadastro" component={Process.Form} />
            <AppRoute exact path="/processo/editar/:id" component={Process.Form} />

            <AppRoute exact path="/parecer/processo/:id" component={Sight.Form} />

            <AppRoute exact path="/" component={Process.List} />
            <AppRoute exact path="*" component={NotFound} />
        </Switch>
    </Router>,
    document.getElementById('root')
);
