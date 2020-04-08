import React, { Component } from "react";
import { Link } from "react-router-dom";
import { TextField, Button } from "@material-ui/core";
import "../Login/login.css";
import AxiosConfig from '../../../configs/AxiosConfig';

export default class Register extends Component {
  state = {
    user: {
      name: "",
      email: "",
      password: "",
      type: "ADMIN"
    },
  };

  onChange = (e) => {
    this.setState({ user: { ...this.state.user, [e.target.name]: e.target.value } });
  };

  register = async () => {
    try {
      await new AxiosConfig().post("auth/register", this.state.user);
      alert('Usuário cadastrado com sucesso!');
      this.props.history.push('/login') 
    } catch (error) {
      alert('Ocorreu um erro salvar o usuário!');
    }
  }

  render() {
    return (
      <div className="Login">
        <form
          onSubmit={(e) => {
            e.preventDefault();
            this.register();
          }}
        >
          <TextField
            required
            className="input"
            name="name"
            onChange={(e) => this.onChange(e)}
            value={this.state.name}
            label="Name"
            variant="outlined"
          />
          <TextField
            required
            className="input"
            name="email"
            onChange={(e) => this.onChange(e)}
            value={this.state.email}
            label="E-mail"
            autoComplete="current-email"
            variant="outlined"
          />
          <TextField
            required
            className="input"
            name="password"
            onChange={(e) => this.onChange(e)}
            value={this.state.password}
            type="password"
            label="Senha"
            autoComplete="current-password"
            variant="outlined"
          />
          <Button
            type="submit"
            className="buttonSave"
            variant="contained"
            color="primary"
          >
            Cadastrar
          </Button>
        </form>
        <Link to="/login">Já sou cadastrado!</Link>
      </div>
    );
  }
}
