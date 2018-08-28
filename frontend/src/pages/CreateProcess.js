import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createProcess } from '../redux/actions';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class CreateProcess extends Page {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            description: '',
            status: '',
            accounts: []
        };
    }

    create =() => {
        const { name, description, type } = this.state;
        const data = { name, description, type };
        this.props.dispatch(createProcess(data))
            .then(() => {
                this.redirect('/home');
            })
            .catch(() => {
                errorMessage('Houve um problema ao criar o processo');
            })
    }

    onChange(fieldName, e) {
        const state = {};
        state[fieldName] = e.target.value;

        this.setState(state);
    }

    render() {
        const statusOptions = [
            { id: 'pendente', name: 'pendente' },
            { id: 'finalizado', name: 'Finalizado' }
        ];

        const options = statusOptions.map((option, i) => {
            const value = option.id || option._id;
            const text = option.name;

            return <option key={i} value={value}>{text}</option>
        });

        return (
            <div className="Login">
                <div className="form">
                    <label>Nome</label>
                    <input value={this.state.name} onChange={this.onChange.bind(this, 'name')} type='text' />

                    <label>Descrição</label>
                    <textarea value={this.state.description} onChange={this.onChange.bind(this, 'description')} />

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
)(CreateProcess);