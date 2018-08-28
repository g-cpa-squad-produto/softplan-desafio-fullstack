import React, { Component } from 'react';
import { connect } from 'react-redux';
import { listPendingProcesses } from '../redux/actions';
import { Link } from 'react-router-dom';
import { errorMessage } from '../libs/helpers';
import Page from './Page';

class PendingProcesses extends Page {
    constructor(props) {
        super(props);

        this.state = {
            feedback: ''
        };
    }

    componentDidMount() {
        const { id } = this.props.match.params;
        this.props.dispatch(listPendingProcesses(id));
    }

    // feedback() {
    //     this.props.dispatch(feedback())
    //         .catch(() => {
    //             errorMessage('Houve um problema ao enviar um parecer');
    //         })
    // }

    onChange(fieldName, e) {
        const state = {};
        state[fieldName] = e.target.value;

        this.setState(state);
    }

    render() {
        const { pending: list } = this.props.process;
        return (
            <div className="Login">
                <div className="form">
                    {
                        list.map((item, i) => {
                            return (
                                <div key={i}>
                                    <Link to={`/process/${item._id}/feedback`}> {item.name} </Link>
                                </div>
                            )
                        })
                    }
                    
                    {!list.length && <span>Nenhum processo pendente de parecer</span>}

                </div>
            </div>
        );
    }
}

const mapStateToProps = ({ process, session }) => {
    return { process, session };
};

export default connect(
    mapStateToProps
)(PendingProcesses);