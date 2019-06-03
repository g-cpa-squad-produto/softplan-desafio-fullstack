import React, { Component } from 'react';

import UsuariosForm from '../../components/Usuarios/form';

class UsuariosFormContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="card">
                <div className="card-body">
                    <h4 className="card-title">Cadastro de usuários</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Visão de administrador</h6>
                    <UsuariosForm history={this.props.history} />
                </div>
            </div>    
        );     
    }

}

export default UsuariosFormContainer;