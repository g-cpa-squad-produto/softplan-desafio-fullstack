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
  Button,
  Modal,
} from "@material-ui/core";
import AxiosConfig from "../../../../configs/AxiosConfig";
import { Edit, Add } from "@material-ui/icons";
import "./index.css";
import UserForm from "../../../../components/UserForm";

export default class AdminUsers extends Component {
  state = {
    modalOpen: false,
    users: [],
    user: {},
  };

  getUsers = async () => {
    try {
      const { data: users } = await new AxiosConfig().get("users");
      this.setState({ users });
    } catch (error) {
      alert("Não foi possível pegar os usuários!");
    }
  };

  editUser = (user) => {
    this.setState({ user });
    this.handleModal(true);
  };

  componentDidMount = () => {
    this.getUsers();
  };

  changeRoute = (id) =>
    this.props.history.push((id && `/edit/${id}`) || "/add");

  handleModal = (modalOpen) => { 
    this.setState({ modalOpen });
    if (!modalOpen) {
      this.getUsers()
    }
  };

  render() {
    return (
      <div className="AdminUsers">
        <div className="Header">
          <Typography component="h1" variant="h4" noWrap className="BreadCrumb">
            Usuários
          </Typography>
          <Button
            variant="contained"
            color="primary"
            startIcon={<Add />}
            onClick={() => this.editUser()}
          >
            Usuário
          </Button>
        </div>
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Id</TableCell>
                <TableCell align="right">Nome</TableCell>
                <TableCell align="right">E-mail</TableCell>
                <TableCell align="right">Tipo</TableCell>
                <TableCell align="right"></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.users.map((user) => (
                <TableRow key={user.id}>
                  <TableCell component="th" scope="row">
                    {user.id}
                  </TableCell>
                  <TableCell align="right">{user.name}</TableCell>
                  <TableCell align="right">{user.email}</TableCell>
                  <TableCell align="right">{user.type}</TableCell>
                  <TableCell align="center">
                    <Edit
                      className="icon"
                      onClick={() => this.editUser(user)}
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
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
        >
          <UserForm user={this.state.user} />
        </Modal>
      </div>
    );
  }
}
