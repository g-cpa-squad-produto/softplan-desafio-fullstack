import React, { Component } from 'react';
import Http from '../Http';
import { messageAlert, setAuth } from '../Methods';

class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: ''
        };

        this.save = this.save.bind(this);
        this.change = this.change.bind(this);
    }

    change(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    save(e) {
        e.preventDefault();

        new Http().post('/login', this.state).send((res) => {
            if (res.data.message) {
                messageAlert(res.data.message, 'warning');
            } else {
                setAuth(res.data);
                window.location.href = '/processo'
            }
        })
    }

    render() {
        return (
            <div className="page-content--bge5">
                <div className="container">
                    <div className="login-wrap">
                        <div className="login-content">
                            <div className="login-logo">
                                GERENCIAMENTO DE PROCESSOS
                            </div>
                            <div className="login-form">
                                <form onSubmit={this.save}>
                                    <div className="form-group">
                                        <label>Usuário</label>
                                        <input className="au-input au-input--full" type="text" name="username" value={this.state.username} onChange={this.change} placeholder="Usuário" />
                                    </div>
                                    <div className="form-group">
                                        <label>Senha</label>
                                        <input className="au-input au-input--full" type="password" name="password" value={this.state.password} onChange={this.change} placeholder="Senha" />
                                    </div>
                                    <button className="au-btn au-btn--block au-btn--green m-b-20" type="submit">Entrar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;