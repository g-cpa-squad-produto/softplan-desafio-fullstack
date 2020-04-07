import React, { Component } from "react";
import { Link } from "react-router-dom";
import { TextField, Button } from "@material-ui/core";
import "../Login/login.css";

export default class Register extends Component {
  render() {
    return (
      <div className="Login">
        <form
          noValidate
          onSubmit={(e) => {
            e.preventDefault();
            console.log("Registrou");
          }}
        >
          <TextField
            className="input"
            name="name"
            label="Name"
            variant="outlined"
          />
          <TextField
            className="input"
            name="email"
            label="E-mail"
            autoComplete="current-email"
            variant="outlined"
          />
          <TextField
            className="input"
            name="password"
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
