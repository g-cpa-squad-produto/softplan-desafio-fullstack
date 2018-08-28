import React from 'react';
import { HashRouter, Route, Switch } from 'react-router-dom';
import Master from './pages/Master';

import Login from './pages/Login';
import CreateProcess from './pages/CreateProcess';
import CreateAccount from './pages/CreateAccount';
import ListAccounts from './pages/ListAccounts';
import ListProcesses from './pages/ListProcesses';
import EditAccount from './pages/EditAccount';
import EditProcess from './pages/EditProcess';
import Home from './pages/Home';

const routes = [
    {
        path: '/login',
        component: Login
    },
    {
        path: '/process/create',
        component: CreateProcess
    },
    {
        path: '/process/:id',
        component: EditProcess
    },
    {
        path: '/process/list',
        component: ListProcesses
    },
    {
        path: '/account/create',
        component: CreateAccount
    },
    {
        path: '/account/list',
        component: ListAccounts
    },
    {
        path: '/account/:id',
        component: EditAccount
    },
    {
        path: '/home',
        component: Home
    }
];

const Router = () => (
    <HashRouter>
        <Switch>
            {
                routes.map((route, i) => {
                    const Component = route.component;
                    return (
                        <Route key={i} path={route.path} exact render={(props) => {
                            return (
                                <Master protected={route.protected} {...props}>
                                    <Component {...props} {...route.props} />
                                </Master>
                            )
                        }} />
                    );
                })
            }
        </Switch>
    </HashRouter>);

export default Router;