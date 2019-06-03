import React, { Component } from 'react';

import ProcessosAtribuicao from '../../components/Processos/atribuicao';

class ProcessosAtribuicaoContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="card">
                <div className="card-body">
                    <h4 className="card-title">Atribuição de processos</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Visão de triador</h6>
                    <br />
                    <ProcessosAtribuicao idProcesso={this.props.match.params.id} history={this.props.history} />
                </div>
            </div>    
        );
    }

}

export default ProcessosAtribuicaoContainer;