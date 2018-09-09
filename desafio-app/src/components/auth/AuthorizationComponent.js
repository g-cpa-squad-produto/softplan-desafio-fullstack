import React from 'react';
import {authService} from '../../services/index';

export default class AuthorizationComponent extends React.Component {

    getAuth = () => {
        return authService.getUserDetails();
    };

    isPermitted = (permissions, roles) => {
        let autorizado = false;

        roles.forEach(e => {
            if (permissions.includes(e)) autorizado = true;
        });

        return autorizado;
    };

    render() {
        const roles = this.getAuth().roles;
        return (this.isPermitted(this.props.roles, roles) || this.props.roles === undefined)
            ? <div> {this.props.children} </div> : null;
    }
}
