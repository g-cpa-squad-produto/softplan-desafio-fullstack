import React from 'react'

import UsuarioService from '../app/service/usuarioService'
import { AuthContext } from '../main/provedorAutenticacao'

class Home extends React.Component{

    state = {
        
    }

    constructor(){
        super()
        this.usuarioService = new UsuarioService();
    }


    render(){
        return (
            <div className="jumbotron">
                <h1 className="display-3">Bem vindo!</h1>
                <p className="lead">Esse é seu sistema de processos.</p>
              
                <hr className="my-4" />
                <p>E essa é sua área administrativa, utilize um dos menus ou botões abaixo para navegar pelo sistema.</p>
                <p className="lead">
                    <a className="btn btn-primary btn-lg" 
                    href="#/cadastro-usuarios" 
                    role="button"><i className="pi pi-users"></i>  
                     Cadastrar Usuário
                    </a>
                    <a className="btn btn-danger btn-lg" 
                    href="#/cadastro-processos" 
                    role="button"><i className="pi pi-money-bill"></i>  
                     Cadastrar Processo
                    </a>
                </p>
            </div>
        )
    }
}

Home.contextType = AuthContext;

export default Home