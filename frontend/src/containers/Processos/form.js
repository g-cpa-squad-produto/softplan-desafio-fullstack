import React, { Component } from 'react';

import ProcessosForm from '../../components/Processos/form';

class ProcessosFormContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                <div className="card col-md-6">
                    <div className="card-body">
                        <h4 className="card-title">Cadastro de processos</h4>
                        <h6 className="card-subtitle mb-2 text-muted">Visão de triador</h6>
                        <br />
                        <ProcessosForm history={this.props.history} />
                    </div>
                </div>
            </div>    
        );
    }

}

export default ProcessosFormContainer;