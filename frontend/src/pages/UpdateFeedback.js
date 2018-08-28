import React, { Component } from 'react';
import { connect } from 'react-redux';
import { showProcess, updateFeedback, listAccounts } from '../redux/actions';
import { Link } from 'react-router-dom';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class UpdateFeedback extends Page {
    constructor(props) {
        super(props);

        this.state = {
            feedback: '',
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

    updateFeedback = () => {
        const { id } = this.props.match.params;
        const { feedback } = this.state;
        const data = { feedback };
        
        this.props.dispatch(updateFeedback(id, data))
            .then(() => {
                this.reload();
            })
            .catch(() => {
                errorMessage('Houve um problema ao editar o processo');
            })
    }

    render() {
        const { selected } = this.props.process;

        const options = this.props.account.list.map((option, i) => {
            const value = option.id || option._id;
            const text = option.email;

            return <option key={i} value={value}>{text}</option>
        });

        const processAccounts = this.props.process.accounts || [];
        const accounts = processAccounts.map((accountId, i) => {
            const account = this.props.account.list.find((account) => {
                return account._id === accountId
            });

            return account ? (
                <div key={i}>
                    {account.email}
                </div>
            ) : null;
        });

        return (
            <div className="Login">
                <div className="form">
                    <h1>{this.props.process.selected.name}</h1>

                    <label>Parecer</label>
                    <textarea value={this.state.feedback || ''} onChange={this.onChange.bind(this, 'feedback')} />

                    <button onClick={this.updateFeedback}>Enviar</button>
                </div>
            </div>
        );
    }
}

const mapStateToProps = ({ process, account, session }) => {
    console.log(process)
    return { process, account, session };
};

export default connect(
    mapStateToProps
)(UpdateFeedback);