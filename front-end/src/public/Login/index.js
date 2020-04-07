import React, { Component } from "react";
import { Link } from "react-router-dom";
import { TextField, Button } from "@material-ui/core";
import "./login.css";

export default class Login extends Component {
  render() {
    return (
      <div className="Login">
        <form
          noValidate
          onSubmit={(e) => {
            e.preventDefault();
            console.log("LOGOU");
          }}
        >
          <TextField
            className="input"
            name="email"
            label="E-mail"
            autoComplete="current-email"
            variant="outlined"
          />
          <TextField
            className="input"
            id="password"
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
        <Link to="/register">Não é cadastrado ainda?</Link>
      </div>
    );
  }
}
