import React, { Component } from 'react';
import { connect } from 'react-redux';
import { listAccounts } from '../redux/actions';
import { Link } from 'react-router-dom';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class ListAccounts extends Page {
    constructor(props) {
        super(props);
    }

    componentDidMount(){
        this.props.dispatch(listAccounts());
    }

    render() {
        const { list } = this.props.account
        return (
            <div className="Login">
                {
                    list.map((item, i) => {
                        return (
                            <div key={i}>
                                <Link to={`/account/${item._id}`}> {item.email} </Link>
                            </div>
                        )
                    })
                }
            </div>
        );
    }
}

const mapStateToProps = ({ account, session }) => {
    return { account, session };
};

export default connect(
    mapStateToProps
)(ListAccounts);