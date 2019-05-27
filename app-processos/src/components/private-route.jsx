import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { ApplicationContext } from '../components/application-context';

export const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={props => (
        ApplicationContext.getUser()
            ? <Component {...props} />
            : <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
    )} />
)