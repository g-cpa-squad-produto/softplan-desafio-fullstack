import React, { Component } from 'react';
import { connect } from 'react-redux';
import { showAccount, editAccount, deleteAccount } from '../redux/actions';
import { Link } from 'react-router-dom';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class EditAccount extends Page {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            type: ''
        };
    }

    componentDidMount() {
        const { id } = this.props.match.params;
        this.props.dispatch(showAccount(id))
        .then(({payload}) => {
            this.setState(payload.account);
        })
    }

    onChange(fieldName, e) {
        const state = {};
        state[fieldName] = e.target.value;

        this.setState(state);
    }

    edit = () => {
        const { id } = this.props.match.params;
        const { email, password, type } = this.state;
        const data = { email, password, type };

        this.props.dispatch(editAccount(id, data))
            .then(() => {
                this.redirect('/home');
            })
            .catch(() => {
                errorMessage('Houve um problema ao editar a conta');
            })
    }

    delete = () => {
        const { id } = this.props.match.params;

        this.props.dispatch(deleteAccount(id))
            .then(() => {
                this.redirect('/home');
            })
            .catch(() => {
                errorMessage('Houve um problema ao deletar a conta');
            })
    }

    render() {
        const { selected } = this.props.account;

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
                    <input onChange={this.onChange.bind(this, 'password')} type='password' />

                    <label>Tipo</label>
                    <select value={this.state.type} onChange={this.onChange.bind(this, 'type')}>
                        {options}
                    </select>

                    <button onClick={this.edit}>Enviar</button>
                    <button onClick={this.delete}>Deletar</button>
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
)(EditAccount);