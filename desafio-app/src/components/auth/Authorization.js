import React from 'react';
import {authService} from '../../services/index';
import * as authActions from '../../store/auth/action';
import SimpleSnackbar from "../notification/SimpleSnackBar";

export const Authorization = (allowedRoles) => (WrappedComponent) => {
    return class Wrapper extends React.Component {
        state = {
            openNotification: false,
            textNotification: ''
        };

        handleOpenNotification = (text) => {
            this.setState({
                openNotification: true,
                textNotification: text
            });
        };

        handleCloseNotification = (event, reason) => {
            if (reason === 'clickaway') {
                return;
            }

            this.setState({openNotification: false});
        };

        componentWillMount() {
            this.isloggedIn(this.getAuth());
            const roles = this.getAuth().roles;

            if (!this.isPermitted(allowedRoles, roles)) this.notPermitted(this.getAuth());
        }

        getAuth = () => {
            return authService.getUserDetails();
        };

        isloggedIn = (auth) => {
            let history = this.props.history;

            if (!auth.loggedIn) {
                authActions.logout();
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

        render() {
            return (
                [
                    <SimpleSnackbar key="notification" handleOpen={this.handleOpenNotification}
                                    handleClose={this.handleCloseNotification}
                                    open={this.state.openNotification} text={this.state.textNotification}/>,
                    <WrappedComponent key="wrappedComponent" notify={this.handleOpenNotification} {...this.props} />
                ]
            );
        }
    }
};