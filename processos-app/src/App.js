import React, { Component } from 'react';

import { Link, withRouter } from "react-router-dom";
import Routes from "./Routes";
import './App.css';

export const Logo = "Processos.App";

class App extends Component {
  constructor(props){
    super(props);

    this.state = {
      isAuthenticated: false
    };
  }

  componentDidMount(){
    this.userHasAuthenticated(this.state.isAuthenticated);
  }

  userHasAuthenticated = authenticated => {
    this.setState({ isAuthenticated: authenticated });
  }

  handleLogout = event => {
    this.userHasAuthenticated(false);
    this.handleLogin();
  }

  handleLogin = event => {
    this.props.history.push("/login");
  }

  render() {
    const authProps = {
      isAuthenticated: this.state.isAuthenticated,
      userHasAuthenticated: this.userHasAuthenticated
    };

    return (
      <div className="App container-fluid">
        <nav className="navbar navbar-inverse">
          <div className="container-fluid">
            <div className="navbar-header">
              <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#topo-navbar-collapse" aria-expanded="false">
                <span className="sr-only">Toggle navigation</span>
                <span className="icon-bar"></span>
                <span className="icon-bar"></span>
                <span className="icon-bar"></span>
              </button>
              <Link className="navbar-brand" to="/">{Logo}</Link>
            </div>
    
            <div className="collapse navbar-collapse" id="topo-navbar-collapse">
              <ul className="nav navbar-nav">
                <li><Link to="/usuarios" className="nav-link">Usuários</Link></li>
                <li><Link to="/processos">Processos</Link></li>
              </ul>
              <ul className="nav navbar-nav navbar-right">
                <li> 
                    {this.state.isAuthenticated 
                    ? <button className="btn btn-danger navbar-btn" onClick={this.handleLogout}>Logout</button> 
                    : <button className="btn btn-success navbar-btn" onClick={this.handleLogin}>Login</button>}
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <Routes authProps={authProps} />
      </div>
    );
  }
}

export default withRouter(App);
