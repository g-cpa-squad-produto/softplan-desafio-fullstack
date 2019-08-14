import React, { Component } from 'react';
import './css/pure-min.css';
import './css/side-menu.css';
import $ from 'jquery';
import InputCustomizado from './componentes/InputCustomizado';


class App extends React.Component {

  constructor() {
    super();
    this.state = {lista : [], nome:'', login:'', senha:'', confirmaSenha:''};
    this.enviarForm.bind(this);
    this.setNome = this.setNome.bind(this);
    this.setLogin = this.setLogin.bind(this);
    this.setSenha = this.setSenha.bind(this);
    this.setConfirmaSenha = this.setConfirmaSenha.bind(this);
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
    this.setState({confirmaSenha: evento.target.value});
  }

  render() {
    console.log('render');
    return (
    <div id="layout">
      <a href="#menu" id="menuLink" className="menu-link">
          <span></span>
      </a>
    
      <div id="menu">
          <div className="pure-menu">
              <a className="pure-menu-heading" href="#">Gestão</a>
    
              <ul className="pure-menu-list">
                  <li className="pure-menu-item"><a href="/" className="pure-menu-link">Home</a></li>
                  <li className="pure-menu-item"><a href="#" className="pure-menu-link">Usuários</a></li>
    
                  <li className="pure-menu-item menu-item-divided pure-menu-selected">
                      <a href="#" className="pure-menu-link">Processos</a>
                  </li>
              </ul>
          </div>
      </div>
    
      <div id="main">
        <div className="header">
          <h1>Cadastro de Usuários</h1>
        </div>
        <div className="content" id="content">
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
                  this.state.lista.map(function(usuario){
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
        </div>
      </div>
    </div>);
  }
}

export default App;