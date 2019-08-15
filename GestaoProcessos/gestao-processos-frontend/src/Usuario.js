import React, { Component } from 'react';
import $ from 'jquery';
import InputCustomizado from './componentes/InputCustomizado';

class FormularioUsuario extends Component{

    constructor() {
        super();
        this.state = {nome:'', login:'', senha:'', confirmaSenha:''};
        this.enviarForm.bind(this);
        this.setNome = this.setNome.bind(this);
        this.setLogin = this.setLogin.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setConfirmaSenha = this.setConfirmaSenha.bind(this);
    }

    enviarForm(evento) {
        evento.preventDefault();
        console.log('SALVANDO DADOS...');
        $.ajax({
          url: 'http://localhost:8080/gestao-processos/api/usuarios',
          contentType: 'application/json',
          dataType: 'json',
          method: 'post',
          data: JSON.stringify({nome: this.state.nome, login: this.state.login, senha: this.state.senha, confirmaSenha: this.state.confirmaSenha}),
          success: function(resposta){
            console.log('SUCESSO...');
            this.setState({lista:resposta});
          }.bind(this),
          error: function(resposta){
            console.log('ERRO...');
          }
        });
    }
    
    setNome(evento){
        this.setState({nome: evento.target.value});
    }
    
    setLogin(evento){
        this.setState({login: evento.target.value});
    }
    
    setSenha(evento){
        this.setState({senha: evento.target.value});
    }
    
    setConfirmaSenha(evento){
        console.log(evento.target.value);
        this.setState({confirmaSenha: evento.target.value});
    }
    
    render(){
        return(
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned" onSubmit={this.enviarForm.bind(this)}>
                <InputCustomizado id="nome" name="nome" type="text" value={this.state.nome} onChange={this.setNome} label="Nome"/>
                <InputCustomizado id="login" name="login" type="text" value={this.state.login} onChange={this.setLogin} label="Login"/>
                <InputCustomizado id="senha" name="senha" type="password" value={this.state.senha} onChange={this.setSenha} label="Senha"/>
                <InputCustomizado id="confirmaSenha" name="confirmaSenha" type="password" value={this.state.confirmaSenha} onChange={this.setConfirmaSenha} label="Confirma Senha"/>

                
                <div className="pure-control-group">                                  
                    <label></label> 
                    <button type="submit" className="pure-button pure-button-primary">Salvar</button>                                    
                </div>

                </form>             
        
            </div>
        );
    }
}

class TabelaUsuario extends Component{
    
    render(){
        return(
            <div>            
                <table className="pure-table">
                <thead>
                    <tr>
                    <th>Nome</th>
                    <th>Login</th>
                    <th>Perfil</th>
                    </tr>
                </thead>
                <tbody>
                    {
                    this.props.lista.map(function(usuario){
                        return (
                        <tr key={usuario.id}>
                            <td>{usuario.nome}</td>
                            <td>{usuario.login}</td>
                            <td>{usuario.perfis}</td>
                        </tr>
                        )
                    })
                    }
                </tbody>
                </table> 
            </div>
        );
    }
}

export default class UsuarioBox extends Component{
    constructor() {
        super();
        this.state = {lista : []};
    }

    componentDidMount(){
        $.ajax({
          url:'http://localhost:8080/gestao-processos/api/usuarios',
          dataType: 'json',
          success:function(resposta){
            this.setState({lista:resposta});
          }.bind(this)
        });
    }
    render(){
        return(
            <div>
                <FormularioUsuario />
                <TabelaUsuario lista={this.state.lista}/>
            </div>
        );
    }
}