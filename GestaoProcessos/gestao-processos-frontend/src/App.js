import React, { Component } from 'react';
import './css/pure-min.css';
import './css/side-menu.css';
import UsuarioBox from './Usuario'


class App extends React.Component {
  render() {
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
            <UsuarioBox />                       
        </div>
      </div>
    </div>);
  }
}

export default App;