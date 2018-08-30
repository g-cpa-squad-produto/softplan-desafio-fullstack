import React, { Component } from 'react';
import './css/pure-min.css';
import './css/side-menu.css';
import { Link } from 'react-router-dom';

class App extends Component {

    constructor() {
        super();
        this.state = { usuario: '' };
    }

    componentDidMount() {
        const usuario = JSON.parse(localStorage.getItem('usuario'));
        this.setState({ usuario: usuario });
    }

    render() {

        var usuarioPerfil = null;
        if (this.state.usuario) 
            usuarioPerfil = this.state.usuario.perfil.name; 

        return (
            <div id="layout">
                <div id="menu">
                    <div className="pure-menu">
                        <a className="pure-menu-heading" href="/">React-App</a>
                        <ul className="pure-menu-list">
                            <li className="pure-menu-item">
                                <Link to="/" className="pure-menu-link">Home</Link>
                            </li>
                            { 
                                usuarioPerfil && usuarioPerfil === 'ADM' ? (
                                    <li className="pure-menu-item">
                                        <Link to="/usuario" className="pure-menu-link">Usuarios</Link>
                                    </li>
                                ) : 
                                (<span/>)
                            }
                            { 
                                usuarioPerfil && (usuarioPerfil === 'TRI' || usuarioPerfil === 'FIN') ? (
                                    <li className="pure-menu-item">
                                        <Link to="/processo" className="pure-menu-link">Processos</Link>
                                    </li>
                                ) : 
                                (<span/>)
                            }
                            {
                                usuarioPerfil ? (
                                    <li className="pure-menu-item">
                                        <Link to="/logout" className="pure-menu-link">Logout</Link>
                                    </li>
                                ) : (<span/>)
                            }
                        </ul>
                    </div>
                </div>

                <div id="main">
                    {this.props.children}
                </div>
            </div>
        );
    }
}

export default App;
