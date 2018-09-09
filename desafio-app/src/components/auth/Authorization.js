import React from 'react';
import {authService} from '../../services/index';

export const Authorization = (allowedRoles) => (WrappedComponent) => {
    return class Wrapper extends React.Component {

        getAuth = () => {
            return authService.getUserDetails();
        };

        isloggedIn = (auth) => {
            let history = this.props.history;

            if (!auth.loggedIn) {
                authService.logout();
                history.push('/login');
            }
        };

        isPermitted = (permissions, roles) => {
            let autorizado = false;

            roles.forEach(e => {
                if (permissions.includes(e)) autorizado = true;
            });

            return autorizado;
        };

        notPermitted = (auth) => {
            let history = this.props.history;
            (auth.loggedIn) ? history.push('/401') : history.push('/login');
        };

        componentWillMount() {
            this.isloggedIn(this.getAuth());
            const roles = this.getAuth().roles;

            if (!this.isPermitted(allowedRoles, roles)) this.notPermitted(this.getAuth());
        }

        render() {
            return <WrappedComponent {...this.props} />;
        }
    }
};