import React, { Component } from 'react';
import { connect } from 'react-redux';
import { login } from '../redux/actions';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class Login extends Page {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: ''
        };
    }

    login = () => {
        const { email, password } = this.state;
        const data = {email, password};
        this.props.dispatch(login(data))
            .then(() => {
                this.redirect('/home');
            })
            .catch(() => {
                errorMessage('Houve um problema ao realizar o login');
            })
    }

    onChange(fieldName, e) {
        const state = {};
        state[fieldName] = e.target.value;

        this.setState(state);

        console.log(this.state)
    }

    render() {
        return (
            <div className="Login">
                <div className="form">
                    <label>E-mail</label>
                    <input onChange={this.onChange.bind(this, 'email')} type='email' />

                    <label>Senha</label>
                    <input onChange={this.onChange.bind(this, 'password')} type='password' />

                    <button onClick={this.login}>Enviar</button>
                </div>
            </div>
        );
    }
}

const mapStateToProps = ({ account, session }) => {
    return { account, session };
};

export default connect(
    mapStateToProps
)(Login);