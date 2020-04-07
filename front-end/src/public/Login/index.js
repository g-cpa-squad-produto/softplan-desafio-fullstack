import React, { Component } from "react";
import { Link } from "react-router-dom";
import { TextField, Button } from "@material-ui/core";
import "./login.css";

export default class Login extends Component {
  render() {
    return (
      <div className="Login">
          <form noValidate>
            <TextField name="email" label="E-mail" />
            <TextField id="password" label="Senha" />
            <Button className="buttonSave" variant="contained" color="primary">
              Login
            </Button>
          </form>
          <Link to="/register">Não é cadastrado ainda?</Link>
      </div>
    );
  }
}
