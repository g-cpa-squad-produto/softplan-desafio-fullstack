import React, {Component} from 'react';
import { Button, FormControl } from "react-bootstrap";
import {Logo} from '../App';

export default class Login extends Component {
    constructor(props){
        super();

        this.state = {
            email: "",
            senha: ""
        };
    }

    handleSubmit(event) {
        event.preventDefault();

        const login = {...this.state};
        
        this.props.userHasAuthenticated(true);

        this.props.history.push("/");
    }

    validateForm() {
        return this.state.email.length > 0 
            && this.state.email.length <= 100 
            && this.state.senha.length >= 3
            && this.state.senha.length <= 16;
      }

    handleChange = (event) => {
        event.preventDefault();
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render(){
        return(
            <div className="container text-center container-login">
                <form className="form-login" onSubmit={this.handleSubmit.bind(this)}>
                    <h1 className="h3 mb-3 font-weight-normal">{Logo}</h1>
                    
                    <label htmlFor="email" className="sr-only">E-mail</label>
                    <FormControl
                        value={this.email} onChange={this.handleChange}
                        type="email" id="email" name="email" placeholder="Digite o e-mail"
                    />
                    
                    <label htmlFor="senha" className="sr-only">Senha</label>
                    <FormControl 
                        value={this.senha} onChange={this.handleChange}
                        type="password" id="senha" name="senha" placeholder="Digite a senha"
                    />

                    <Button
                        disabled={!this.validateForm()}
                        className="btn btn-lg btn-success btn-block"
                        type="submit">Acessar</Button>
                </form>
            </div>
        )   
    }
}