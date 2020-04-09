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
  Modal,
} from "@material-ui/core";
import AxiosConfig from "../../../../configs/AxiosConfig";
import { AddCircleOutlineRounded } from "@material-ui/icons";
import "./index.css";
import ProcessForm from "../../../../components/ProcessForm";

export default class ProcessesList extends Component {
  state = {
    modalOpen: false,
    processes: [],
    processId: null,
  };

  getProcesses = async () => {
    try {
      let { data: processes } = await new AxiosConfig().get("processes");
      processes = processes.filter((process) => !process.description);
      this.setState({ processes });
    } catch (error) {
      alert("Não foi possível listar os processos!");
    }
  };

  componentDidMount = () => {
    this.getProcesses();
  };

  finishProcess = (processId) => {
    this.setState({ processId });
    this.handleModal(true);
  };

  handleModal = (modalOpen) => {
    this.setState({ modalOpen });
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
                <TableCell align="right"></TableCell>
                <TableCell align="right"></TableCell>
                <TableCell align="right"></TableCell>
                <TableCell align="right"></TableCell>
                <TableCell align="right"></TableCell>
                <TableCell align="right"></TableCell>
                <TableCell align="center">Finalizar Processo</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.processes.map((process) => (
                <TableRow key={process.id}>
                  <TableCell component="th" scope="row">
                    {process.id}
                  </TableCell>
                  <TableCell align="right">{process.description}</TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="right"></TableCell>
                  <TableCell align="center">
                    <AddCircleOutlineRounded
                      className="icon"
                      onClick={() => this.finishProcess(process.id)}
                    />
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <Modal
          className="Modal"
          open={this.state.modalOpen}
          onClose={() => this.handleModal(false)}
          aria-labelledby="simple-modal-user"
          aria-describedby="simple-modal-user-description"
        >
          <ProcessForm processId={this.state.processId} />
        </Modal>
      </div>
    );
  }
}
