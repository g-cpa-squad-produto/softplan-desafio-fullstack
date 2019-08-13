import React, { Component } from 'react';
import './css/pure-min.css';
import './css/side-menu.css';
import $ from 'jquery';


class App extends React.Component {

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
            <form className="pure-form pure-form-aligned">
              <div className="pure-control-group">
                <label htmlFor="nome">Nome</label> 
                <input id="nome" type="text" name="nome" value=""  />                  
              </div>
              <div className="pure-control-group">
                <label htmlFor="email">Login</label> 
                <input id="email" type="text" name="login" value=""  />                  
              </div>
              <div className="pure-control-group">
                <label htmlFor="senha">Senha</label> 
                <input id="senha" type="password" name="senha"  />                                      
              </div>
              <div className="pure-control-group">
                <label htmlFor="senha">Confirma Senha</label> 
                <input id="senha" type="password" name="confirmaSenha"  />                                      
              </div>
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