import React, { Component } from "react";
import { Link } from "react-router-dom";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from "@material-ui/core";
import AxiosConfig from '../../../configs/AxiosConfig';

export default class AdminUsers extends Component {
  state = {
    users: [
      {
        id: 1,
        name: "Rafael Camilo",
        email: "camilo.melges@gmail.com",
        type: "ADMIN",
      },
      {
        id: 2,
        name: "Rafael Camilo",
        email: "camilo.melges@gmail.com",
        type: "TRIATOR",
      },
      {
        id: 3,
        name: "Rafael Camilo",
        email: "camilo.melges@gmail.com",
        type: "FINISHER",
      },
    ],
  };

  getUsers = async () => {
    try {
      const { data: users } = await new AxiosConfig().get("users");
      this.setState({ users });
    } catch (error) {
      alert('Não foi possível pegar os usuários!')
    }
  }

  componentWillMount = () => {
    this.getUsers();
  }

  render() {
    return (
      <div class="AdminUsers">
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Id</TableCell>
                <TableCell align="right">Nome</TableCell>
                <TableCell align="right">E-mail</TableCell>
                <TableCell align="right">Tipo</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.users.map((row) => (
                <TableRow key={row.id}>
                  <TableCell component="th" scope="row">
                    {row.id}
                  </TableCell>
                  <TableCell align="right">{row.name}</TableCell>
                  <TableCell align="right">{row.email}</TableCell>
                  <TableCell align="right">{row.type}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        AdminUsers
        <Link to="/add">AddUser</Link>
        <Link to="/edit">EditUser</Link>
      </div>
    );
  }
}
