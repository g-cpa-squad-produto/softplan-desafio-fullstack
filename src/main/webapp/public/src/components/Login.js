import React, {Component} from 'react';
import '../css/login.css';

export default class Login extends Component {

    constructor(props) {
        super(props);

        var msg = '';

        const queryParams = new URLSearchParams(props.location.search);
        const queryMsg = queryParams.get('msg');

        if (queryMsg) msg = queryMsg;

        this.state = { msg: msg };
    }

    envia(event) {
        event.preventDefault();

        const requestInfo = {
            method: 'POST',
            body: JSON.stringify({
                nome: this.login.value
            }),
            headers: new Headers({
                'Content-type': 'application/json'
            })
        };

        fetch('http://localhost:8080/api/login', requestInfo)
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error("Não foi possível fazer o login.")
            }
        })
        .then(usuario => {
            localStorage.setItem('usuario', usuario);
            const user = JSON.parse(usuario);
            debugger;
            if (user.perfil.name === 'TRI' || user.perfil.name === 'FIN')
                this.props.history.push('/processo');
            else
                this.props.history.push('/usuario');
        })
        .catch(error => {
            this.setState({ msg: error.message });
        });
    }

    render() {
        return (
            <div>
                <span>{this.state.msg}</span>
                <form className="login" onSubmit={this.envia.bind(this)}>
                    <h1 className="login-title">Login</h1>
                    <input type="text" className="login-input" placeholder="Nome usuário" autofocus ref={(input) => this.login = input} />
                    <input type="submit" value="Lets Go" className="login-button" />
                </form>
            </div>
        );
    }

}