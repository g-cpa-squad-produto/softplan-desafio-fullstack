import React, { Component } from "react";
import {
  TextField,
  Button,
  Typography,
  MenuItem,
  InputLabel,
  FormControl,
  Select,
} from "@material-ui/core";
import AxiosConfig from "../../configs/AxiosConfig";
import "./UserForm.css";

export default class UserForm extends Component {
  state = {
    user: this.props.user || {
      name: "",
      email: "",
      password: "",
      type: "TRIATOR",
    },
  };

  onChange = (e) => {
    this.setState({
      user: { ...this.state.user, [e.target.name]: e.target.value },
    });
  };

  saveUser = async () => {
    try {
      await new AxiosConfig()[!this.props.user ? "post" : "put"](
        "users",
        this.state.user
      );
      alert("Usuário salvo com sucesso!");
      // eslint-disable-next-line no-restricted-globals
      location.replace("/");
    } catch (error) {
      alert("Não foi possível salvar o usuário");
    }
  };

  render() {
    return (
      <div className="UserForm">
        <Typography component="h1" variant="h4" noWrap className="BreadCrumb">
          {!this.props.user ? "Novo usuário" : "Editar Usuário"}
        </Typography>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            this.saveUser();
          }}
        >
          <TextField
            required
            className="input"
            name="name"
            onChange={(e) => this.onChange(e)}
            value={this.state.user.name}
            label="Name"
            variant="outlined"
          />
          <TextField
            required
            className="input"
            name="email"
            onChange={(e) => this.onChange(e)}
            value={this.state.user.email}
            label="E-mail"
            autoComplete="current-email"
            variant="outlined"
          />
          <TextField
            required
            className="input"
            name="password"
            onChange={(e) => this.onChange(e)}
            value={this.state.user.password}
            type="password"
            label="Senha"
            autoComplete="current-password"
            variant="outlined"
          />
          <FormControl required className="input" variant="outlined">
            <InputLabel>Permissão</InputLabel>
            <Select
              name="type"
              value={this.state.user.type}
              onChange={(e) => this.onChange(e)}
              label="Permissão"
            >
              <MenuItem value={"ADMIN"}>Administrador</MenuItem>
              <MenuItem value={"TRIATOR"}>Triador</MenuItem>
              <MenuItem value={"FINISHER"}>Finalizador</MenuItem>
            </Select>
          </FormControl>
          <Button
            type="submit"
            className="buttonSave"
            variant="contained"
            color="primary"
          >
            Salvar
          </Button>
        </form>
      </div>
    );
  }
}
