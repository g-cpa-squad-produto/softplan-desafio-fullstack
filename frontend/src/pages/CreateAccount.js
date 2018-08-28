import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createAccount } from '../redux/actions';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class CreateAccount extends Page {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            type: ''
        };
    }

    create = () => {
        const { email, password, type } = this.state;
        const data = { email, password, type };
        this.props.dispatch(createAccount(data))
            .then(() => {
                this.redirect('/home');
            })
            .catch(() => {
                errorMessage('Houve um problema ao criar conta');
            })
    }

    onChange(fieldName, e) {
        const state = {};
        state[fieldName] = e.target.value;

        this.setState(state);
    }

    render() {
        const typesOptions = [
            { id: 'administrador', name: 'Administrador' },
            { id: 'triador', name: 'Triador' },
            { id: 'finalizador', name: 'Ginalizador' },
        ];

        const options = typesOptions.map((option, i) => {
            const value = option.id || option._id;
            const text = option.name;

            return <option key={i} value={value}>{text}</option>
        });

        return (
            <div className="Login">
                <div className="form">
                    <label>E-mail</label>
                    <input value={this.state.email} onChange={this.onChange.bind(this, 'email')} type='email' />

                    <label>Senha</label>
                    <input value={this.state.password} onChange={this.onChange.bind(this, 'password')} type='password' />

                    <label>Tipo</label>
                    <select value={this.state.type} onChange={this.onChange.bind(this, 'type')}>
                        {options}
                    </select>

                    <button onClick={this.create}>Enviar</button>
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
)(CreateAccount);