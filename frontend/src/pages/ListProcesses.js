import React, { Component } from 'react';
import { connect } from 'react-redux';
import { listProcesses } from '../redux/actions';
import { Link } from 'react-router-dom';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class ListProcesses extends Page {
    constructor(props) {
        super(props);
    }

    componentDidMount(){
        this.props.dispatch(listProcesses());
    }

    render() {
        const { list } = this.props.process;
        return (
            <div className="Login">
                {
                    list.map((item, i) => {
                        return (
                            <div key={i}>
                                <Link to={`/process/${item._id}`}> {item.name} </Link>
                            </div>
                        )
                    })
                }
                <Link to={'/process/create'}>Criar novo processo</Link>
            </div>
        );
    }
}

const mapStateToProps = ({ process, session }) => {
    return { process, session };
};

export default connect(
    mapStateToProps
)(ListProcesses);