import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { logout } from "../auth/authActions";
import { toastr } from "react-redux-toastr";
import axios from "axios";

class Navbar extends Component {
  constructor(props) {
    super(props);
    this.state = { open: false, usuario: "" };
  }

  componentWillMount() {
    axios
      .get("/login/usuario", {
        params: { token: localStorage.getItem("token") }
      })
      .then(resp => {
        this.setState({ usuario: resp.data });
      })
      .catch(e => {
        toastr.error("Erro", e);
      });
  }

  changeOpen() {
    this.setState({ open: !this.state.open });
  }

  render() {
    const { nome, tipoUsuario } = this.state.usuario;
    return (
      <div className="navbar-custom-menu">
        <ul className="nav navbar-nav">
          <li
            onMouseLeave={() => this.changeOpen()}
            className={`dropdown user user-menu ${
              this.state.open ? "open" : ""
            }`}
          >
            <a
              href="javascript:;"
              onClick={() => this.changeOpen()}
              aria-expanded={this.state.open ? "true" : "false"}
              className="dropdown-toggle"
              data-toggle="dropdown"
            >
              <span className="hidden-xs">{nome}</span>
            </a>
            <ul className="dropdown-menu">
              <li className="user-header">
                <p>
                  {nome}
                  <small>{tipoUsuario}</small>
                </p>
              </li>
              <li className="user-footer">
                <div className="pull-right">
                  <a
                    href="#"
                    onClick={this.props.logout}
                    className="btn btn-default btn-flat"
                  >
                    Sair
                  </a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    );
  }
}

const mapStateToProps = state => ({ user: state.auth.user });
const mapDispatchToProps = dispatch => bindActionCreators({ logout }, dispatch);
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Navbar);
