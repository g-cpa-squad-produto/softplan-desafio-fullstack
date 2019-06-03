import React, { Component } from 'react';

import Login from '../../components/Login/login';

class LoginContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="card">
                <div className="card-body">
                    <h4 className="card-title">Login</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Acesso ao sistema</h6>
                    <br />
                    <Login history={this.props.history} />
                </div>
            </div>
        );
    }

}

export default LoginContainer;