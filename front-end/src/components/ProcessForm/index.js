import React, { Component } from "react";
import {
  TextField,
  Button,
  Typography,
} from "@material-ui/core";
import AxiosConfig from "../../configs/AxiosConfig";
import "./index.css";

export default class ProcessForm extends Component {
  state = {
    description: "",
    processId: this.props.processId,
  };

  onChange = (e) => {
    this.setState({ description: e.target.value });
  };

  finishProcess = async () => {
    try {
      await new AxiosConfig().patch(
        `processes/finish/${this.state.processId}`,
        this.state.description
      );
      alert("Processo finalizado com sucesso!");
      // eslint-disable-next-line no-restricted-globals
      location.replace("/");
    } catch (error) {
      alert("Não foi possível finalizar o processo");
    }
  };

  render() {
    return (
      <div className="ProcessForm">
        <Typography component="h1" variant="h4" noWrap className="BreadCrumb">
          Finalizar Processo
        </Typography>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            this.finishProcess();
          }}
        >
          <TextField
            required
            className="input"
            onChange={(e) => this.onChange(e)}
            value={this.state.description}
            label="Description"
            variant="outlined"
          />
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
