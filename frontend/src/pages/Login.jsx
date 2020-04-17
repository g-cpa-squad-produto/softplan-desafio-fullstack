import React, { Component } from 'react'
import './Login.css'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { login } from '../services/service'

export default class Login extends Component {

    state = {
        username: 'admin',
        password: 'admin'
    }

    handleSubmit = event => {
        event.preventDefault();

        sessionStorage.clear();

        login(this.state.username, this.state.password).then(
            response => {
                const sessionData = response.data
                sessionStorage.setItem("session_data", JSON.stringify(sessionData))
                this.props.history.push('api/user')
            }, error => {
                console.log(error.response)
            })
    }

    handleChangeUser = event => {
        this.setState({ username: event.target.value });
    }

    handleChangePass = event => {
        this.setState({ password: event.target.value });
    }

    render() {
        return (

            <div className="container">
                <div className="row justify-content-md-center">
                    <div className="col-md-6">
                        <div className="form-login">
                            <h2>Olá!</h2>
                            <Form onSubmit={this.handleSubmit}>
                                <Form.Group controlId="formUsername">
                                    <Form.Control type="text" placeholder="Usuário"
                                    value={this.state.username} onChange={this.handleChangeUser}/>
                                </Form.Group>
                                <Form.Group controlId="formPassword">
                                    <Form.Control type="password" placeholder="Senha"
                                    value={this.state.password} onChange={this.handleChangePass}/>
                                </Form.Group>
                                <Button variant="primary" type="submit" size="lg" block>
                                    Entrar
                                </Button>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}