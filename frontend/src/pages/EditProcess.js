import React, { Component } from 'react';
import { connect } from 'react-redux';
import { showProcess, editProcess, deleteProcess, listAccounts, addAccountToProcess, removeAccountFromProcess } from '../redux/actions';
import { Link } from 'react-router-dom';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class EditProcess extends Page {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            accountId: '',
            accounts: []
        };
    }

    componentDidMount() {
        const { id } = this.props.match.params;
        this.props.dispatch(showProcess(id))
            .then(({ payload }) => {
                this.setState(payload.process);
            });


        this.props.dispatch(listAccounts());
    }

    onChange(fieldName, e) {
        const state = {};
        state[fieldName] = e.target.value;

        this.setState(state);
    }

    edit = () => {
        const { id } = this.props.match.params;
        const { name } = this.state;
        const data = { name };

        this.props.dispatch(editProcess(id, data))
            .then(() => {
                this.reload();
            })
            .catch(() => {
                errorMessage('Houve um problema ao editar o processo');
            })
    }

    delete = () => {
        const { id } = this.props.match.params;
        this.props.dispatch(deleteProcess(id))
            .then(() => {
                this.redirect('/process/list');
            })
            .catch(() => {
                errorMessage('Houve um problema ao deletar o processo');
            })
    }

    addAccount = () => {
        const { id } = this.props.match.params;
        this.props.dispatch(addAccountToProcess(id, this.state.accountId))
            .then(() => {
                this.reload();
            })
            .catch(() => {
                errorMessage('Houve um problema ao adicionar a conta');
            })
    }

    removeAccount = (accountId) => {
        const { id } = this.props.match.params;
        this.props.dispatch(removeAccountFromProcess(id, accountId))
            .then(() => {
                this.reload();
            })
            .catch(() => {
                errorMessage('Houve um problema ao remover a conta');
            })
    }

    render() {
        const { selected } = this.props.process;

        const options = this.props.account.list.map((option, i) => {
            const value = option.id || option._id;
            const text = option.email;

            return <option key={i} value={value}>{text}</option>
        });

        const accounts = this.state.accounts.map((accountId, i) => {
            const account = this.props.account.list.find((account) => {
                return account._id === accountId
            });

            return account ? (
                <div key={i}>
                    {account.email}
                    <button onClick={this.removeAccount.bind(this, account._id)}>Remover conta</button>
                </div>
            ) : null;
        });

        return (
            <div className="Login">
                <div className="form">
                    <label>Nome</label>
                    <input value={this.state.name} onChange={this.onChange.bind(this, 'name')} type='text' />

                    <label>Adicionar conta</label>
                    <select value={this.state.accountId} onChange={this.onChange.bind(this, 'accountId')}>
                        {options}
                    </select>
                    <button onClick={this.addAccount}>Adicionar conta</button>

                    {accounts}

                    <button onClick={this.delete}>Deletar este processo</button>
                    <button onClick={this.edit}>Salvar</button>
                </div>
            </div>
        );
    }
}

const mapStateToProps = ({ process, account, session }) => {
    return { process, account, session };
};

export default connect(
    mapStateToProps
)(EditProcess);