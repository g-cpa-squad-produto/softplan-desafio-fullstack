import React, { Component } from 'react';

import AtribuicoesParecer from '../../components/Atribuicoes/parecer';

class AtribuicoesParecerContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="card">
                <div className="card-body">
                    <h4 className="card-title">Parecer do processo</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Visão de finalizador</h6>
                    <br />
                    <AtribuicoesParecer idAtribuicao={this.props.match.params.id} history={this.props.history} />
                </div>
            </div>    
        );
    }

}

export default AtribuicoesParecerContainer;