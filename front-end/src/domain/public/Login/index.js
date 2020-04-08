import React, { Component } from "react";
import { Link } from "react-router-dom";
import Cookies from "universal-cookie";
import { TextField, Button } from "@material-ui/core";
import "./login.css";
import AxiosConfig from '../../../configs/AxiosConfig';

export default class Login extends Component {
  state = {
    user: {
      email: "",
      password: "",
      type: "ADMIN"
    },
  };

  onChange = (e) => {
    this.setState({ user: { ...this.state.user, [e.target.name]: e.target.value } });
  };

  login = async () => {
    try {
      const cookies = new Cookies();
      const { data: user } = await new AxiosConfig().post("auth/login", this.state.user);

      cookies.set("Authorization", "token");
      cookies.set("userType", user.type);

      this.props.history.go('/') 
      alert('logou com sucesso!');
    } catch (error) {
      alert('Não foi possível fazer o login!')
    }
  }

  render() {
    return (
      <div className="Login">
        <form
          onSubmit={(e) => {
            e.preventDefault();
            this.login();
          }}
        >
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
            Login
          </Button>
        </form>
        <Link to="/register">Ainda não é cadastrado?</Link>
      </div>
    );
  }
}
