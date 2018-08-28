import React, { Component } from 'react';
import { connect } from 'react-redux';
import { errorMessage } from '../libs/helpers';
import Page from './Page';
import { Link } from 'react-router-dom';

class Home extends Page {
    constructor(props) {
        super(props);
    }

    componentDidMount(){
    }

    renderAdministratorOptions() {
        return (
            <div>
                <Link to={'/account/list'}>Contas</Link>
            </div>
        );
    }

    renderTriadorOptions() {
        return (
            <div>
                <Link to={'/process/list'}>Processos</Link>
            </div>
        );
    }

    renderFinalizadorOptions() {
        return (
            <div>
                <Link to={'/process/pending'}>Processos pendentes</Link>
            </div>
        );
    }

    render() {
        if(!this.props.session.data) return null;
        return (
            <div className="Login">
                {
                    this.props.session.data.type === 'administrator' && this.renderAdministratorOptions()
                }

                {
                    this.props.session.data.type === 'triador' && this.renderTriadorOptions()
                }

                {
                    this.props.session.data.type === 'finalizador' && this.renderFinalizadorOptions()
                }

                <button onClick={this.logout.bind(this)}>Logout</button>
            </div>
        );
    }
}

const mapStateToProps = ({ account, session }) => {
    return { account, session };
};

export default connect(
    mapStateToProps
)(Home);