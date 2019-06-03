import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { ToastsStore } from 'react-toasts';
import UsuariosService from '../../services/usuarios.service';

class HomeContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {};
        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout(e) {
        UsuariosService.logout();
        ToastsStore.success("Logout realizado com sucesso!");
    }

    render() {
        return (
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                <div className="jumbotron jumbotron-fluid">
                    <div className="container text-center">
                        <h1 className="display-4">Gerenciador de processos</h1>
                        <p className="lead">Seja bem vindo ao Gerenciador de processos do desafio fullstack developer!</p>
                        <div>
                            <Link className="btn btn-primary btn-lg" to="/login">Login</Link>&nbsp;
                            <button className="btn btn-secondary btn-lg" onClick={this.handleLogout}>Logout</button>
                        </div>
                    </div>
                </div>
            </div>    
        );
    }

}

export default HomeContainer;