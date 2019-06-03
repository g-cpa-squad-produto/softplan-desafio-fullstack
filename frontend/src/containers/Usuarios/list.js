import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import UsuariosList from '../../components/Usuarios/list';

class UsuariosListContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="card">
                <div className="card-body">
                    <h4 className="card-title">Usuários cadastrados</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Visão de administrador</h6>
                    <br />
                    <Link to="/usuarios/new" className="btn btn-primary">Cadastrar usuário</Link>
                    <br /><br />
                    <UsuariosList />
                </div>
            </div>    
        );
    }

}

export default UsuariosListContainer;