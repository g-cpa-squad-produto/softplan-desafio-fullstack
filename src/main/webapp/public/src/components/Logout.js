import React, { Component } from 'react';

export default class Logout extends Component {

    componentWillMount() {
        localStorage.removeItem('usuario');
        debugger;
        this.props.history.push('/');
    }

    render() {
        return null;
    }
}