import React, { Component } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
} from "@material-ui/core";
import AxiosConfig from "../../../../configs/AxiosConfig";
import { AddCircleIcon } from "@material-ui/icons";
import "./index.css";

export default class ProcessesList extends Component {
  state = {
    modalOpen: false,
    processes: [],
  };

  getProcesses = async () => {
    try {
      const { data: processes } = await new AxiosConfig().get("processes");
      this.setState({ processes });
    } catch (error) {
      alert("Não foi possível pegar os processos!");
    }
  };

  componentDidMount = () => {
    this.getProcesses();
  };

  render() {
    return (
      <div className="ProcessesList">
        <div className="Header">
          <Typography component="h1" variant="h4" noWrap className="BreadCrumb">
            Processos
          </Typography>
        </div>
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Id</TableCell>
                <TableCell align="right">Descrição</TableCell>
                <TableCell align="right"></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.processes.map((process) => (
                <TableRow key={process.id}>
                  <TableCell component="th" scope="row">
                    {process.id}
                  </TableCell>
                  <TableCell align="right">{process.description}</TableCell>
                  <TableCell align="right">
                  <AddCircleIcon
                      className="icon"
                      onClick={() => this.editUser(user)}
                    />
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    );
  }
}
