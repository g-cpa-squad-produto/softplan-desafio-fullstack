import React, { Component } from 'react';
import { ToastsStore } from 'react-toasts';
import UsuariosService from '../../services/usuarios.service';

class Login extends Component {
    
    constructor(props) {
        super(props);
        
        this.state = {
            username: '',
            password: ''
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        e.preventDefault();

        const { username, password } = this.state;
        
        if (!username) {
            ToastsStore.warning("Informe o seu email.");
            return;
        }

        if (!password) {
            ToastsStore.warning("Informe a sua senha.");
            return;
        }

        UsuariosService.login(username, password)
            .then(user => {
                var path = '/login';
                if (user.perfil === 'ADMINISTRADOR') {
                    path = '/usuarios';
                } else if (user.perfil === 'TRIADOR') {
                    path = '/processos';
                } else if (user.perfil === 'FINALIZADOR') {
                    path = '/atribuicoes';
                }
                const { from } = { from: { pathname: path } };
                this.props.history.push(from);
            })
            .catch(error => {
                console.log(error);
                ToastsStore.warning("Email e/ou senha incorreto(s).")
            });
    }

    render() {
        const { username, password } = this.state;
        return (
            <div>
                <form name="loginForm" onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="email">Endereço de email</label>
                        <input type="email" className="form-control" id="email" name="username" value={username} onChange={this.handleChange} aria-describedby="emailHelp" placeholder="Informe o seu email.." />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Senha</label>
                        <input type="password" className="form-control" id="password" name="password" value={password} onChange={this.handleChange} placeholder="Informe a sua senha.." />
                    </div>
                    <button type="submit" className="btn btn-primary">Acessar</button>
                </form>
            </div>
        );
    }

}

export default Login;