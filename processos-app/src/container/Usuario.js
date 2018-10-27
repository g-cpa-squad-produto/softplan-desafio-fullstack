import React, {Component} from 'react';
import { Button } from "react-bootstrap";
import UsuariosList from '../ui/usuario/UsuarioList';
import {urlUsuarios} from '../ui/webservices/ProcessosAPI';
import {Alert} from '../ui/alert/Alert';
import axios from 'axios';

export default class Usuario extends Component {
    
    constructor(props){
        super(props);

        this.state = {
            usuarios : [],
            erros: []
        };
    }

    componentDidMount(){
        this.selecionarUsuarios();
    }

    selecionarUsuarios(){
        axios.get(urlUsuarios).then(response => {
            this.setState({usuarios: response.data, erros:[]})
        }).catch((error) => {
            this.setState({usuarios: []})
        });
    }

    handleCriarUsuario(event){
        event.preventDefault();
        this.props.history.push("/usuarios/novo");
    }

    excluirUsuario(codigo) {
        axios.delete(urlUsuarios+"/"+codigo).then(response => {
            this.selecionarUsuarios();
        }).catch((error) => {
            if(error.response.data != null && error.response.data.erros != null){
                this.setState({
                    erros : error.response.data.erros
                });
            } else {
                this.setState({
                    erros : [{mensagem: 'Erro Inesperado'}]
                });
            }
        });
    }
    
    render(){
        return(
            <div className="container container-component">
                <Alert erros={this.state.erros} />
                <UsuariosList excluirUsuario={this.excluirUsuario.bind(this)} usuarios={this.state.usuarios} />
                <Button type="button" bsStyle="success" onClick={this.handleCriarUsuario.bind(this)}>Criar Usuário</Button>
            </div>
        );
    }
}