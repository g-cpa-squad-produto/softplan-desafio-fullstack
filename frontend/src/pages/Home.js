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

    render() {
        if(!this.props.session.data) return null;
        return (
            <div className="Login">
                {
                    this.props.session.data.type === 'administrator' && this.renderAdministratorOptions()
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