import React from 'react';
import { HashRouter, Route, Switch } from 'react-router-dom';
import Master from './pages/Master';

import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';
import ListAccounts from './pages/ListAccounts';
import EditAccount from './pages/EditAccount';
import ListProcesses from './pages/ListProcesses';
import CreateProcess from './pages/CreateProcess';
import EditProcess from './pages/EditProcess';
import PendingProcesses from './pages/PendingProcesses';
import UpdateFeedback from './pages/UpdateFeedback';
import Home from './pages/Home';

const routes = [
    {
        path: '/login',
        component: Login
    },
    {
        path: '/process/list',
        component: ListProcesses
    },
    {
        path: '/process/create',
        component: CreateProcess
    },
    {
        path: '/process/pending',
        component: PendingProcesses
    },
    {
        path: '/process/:id',
        component: EditProcess
    },
    {
        path: '/process/:id/feedback',
        component: UpdateFeedback
    },
    {
        path: '/account/list',
        component: ListAccounts
    },
    {
        path: '/account/create',
        component: CreateAccount
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