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
  Typography,
  Button,
} from "@material-ui/core";
import AxiosConfig from "../../../../configs/AxiosConfig";
import { Edit, Add } from "@material-ui/icons";
import "./listUsers.css";

export default class AdminUsers extends Component {
  state = {
    users: [],
  };

  getUsers = async () => {
    try {
      const { data: users } = await new AxiosConfig().get("users");
      this.setState({ users });
    } catch (error) {
      alert("Não foi possível pegar os usuários!");
    }
  };

  componentWillMount = () => {
    this.getUsers();
  };

  changeRoute = (id) =>
    this.props.history.push((id && `/edit/${id}`) || "/add");

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
            onClick={() => this.props.history.push("/add")}
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
                      onClick={(row) =>
                        this.props.history.push(`/edit/${user.id}`)
                      }
                    />
                  </TableCell>
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
