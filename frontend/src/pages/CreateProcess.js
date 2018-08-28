import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createProcess } from '../redux/actions';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class CreateProcess extends Page {
    constructor(props) {
        super(props);

        this.state = {
            name: ''
        };
    }

    create =() => {
        const { name } = this.state;
        const data = { name };
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
        return (
            <div className="Login">
                <div className="form">
                    <label>Nome</label>
                    <input value={this.state.name} onChange={this.onChange.bind(this, 'name')} type='text' />

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