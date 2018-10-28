import React, { Component } from "react";
import { FormGroup, FormControl, ControlLabel, Button, ButtonToolbar } from "react-bootstrap";
import {urlUsuarios} from '../ui/webservices/ProcessosAPI';
import {Alert} from '../ui/alert/Alert';
import axios from 'axios';

export default class UsuarioForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            erros: [],
            codigo: null,
            email: "",
            nome: "",
            senha: "",
            permissoes:[{
                nome: "ROLE_USUARIO_TRIADOR"
            }] 
        };
    };

    componentDidMount(){
        const { codigo } = this.props.match.params

        if(codigo != null) {
            axios.get(urlUsuarios+"/"+codigo).then(response => {
                const usuario = response.data;
                this.setState({
                    codigo: usuario.codigo,
                    email: usuario.email,
                    nome: usuario.nome,
                    permissoes: usuario.permissoes
                });
            }).catch((error) => {
                this.props.history.push("/usuarios");
            });
        }
    }

    validateForm() {
        return this.state.email.length > 0 
        && this.state.email.length <= 100
        && this.state.nome.length > 0 
        && this.state.nome.length <= 50 
        && this.state.senha.length >= 3 
        && this.state.senha.length <= 16;
    };

    handleChange = event => {
        event.preventDefault();

        this.setState({
          [event.target.name]: event.target.value
        });
    };

    handleSubmit(event) {
        event.preventDefault();

        const usuario = {...this.state};

        // Verifica se é um novo usuário
        if(usuario.codigo == null){
            axios.post(urlUsuarios, usuario).then(response => {
                this.props.history.push("/usuarios");
            }).catch((error, xhr) => {
                if(error.response != null && error.response.data.erros != null){
                    this.setState({
                        erros : error.response.data.erros
                    });
                } else {
                    this.setState({
                        erros : [{mensagem: 'Erro Inesperado'}]
                    });
                }
            });
        } else {
            axios.put(urlUsuarios+"/"+usuario.codigo, usuario).then(response => {
                this.props.history.push("/usuarios");
            }).catch((error, xhr) => {
                console.log(error);
                if(error.response != null && error.response.data.erros != null){
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

    };

    handleCancel(event) {
        event.preventDefault();
        this.props.history.push("/usuarios");
    }

    render(){
        return(
            <div className="NovoUsuario container">
                <Alert erros={this.state.erros} />
                <form id="usuarioForm" onSubmit={this.handleSubmit.bind(this)}>
                    <FormGroup>
                        <ControlLabel>E-mail</ControlLabel>
                        <FormControl 
                            onChange={this.handleChange}
                            type="email"
                            name="email"
                            value={this.state.email} 
                            readOnly={this.state.codigo != null ? true : false} 
                            placeholder="Digite o e-mail"/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Nome Completo</ControlLabel>
                        <FormControl 
                            onChange={this.handleChange}
                            name="nome"
                            value={this.state.nome} 
                            placeholder="Digite o nome completo"/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Senha</ControlLabel>
                        <FormControl
                            type="password" 
                            onChange={this.handleChange}
                            name="senha"
                            value={this.senha} 
                            placeholder="Digite a senha"/>
                    </FormGroup>  
                    
                    <ButtonToolbar>
                        <Button type="submit" bsStyle="success" disabled={!this.validateForm()}>{this.state.codigo != null ? 'Atualizar' : 'Criar'} Usuário</Button>
                        <Button type="button" bsStyle="default" onClick={this.handleCancel.bind(this)}>Voltar</Button>
                    </ButtonToolbar>
                </form>
            </div>
        )
    };
}