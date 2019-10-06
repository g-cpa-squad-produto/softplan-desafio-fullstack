import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { getUsuarioById } from "../../actions/userActions";
import { updateUsuario } from "../../actions/userActions";
import classnames from "classnames";

class UpdateUser extends Component {
  constructor() {
    super();

    //controlled component
    //estado inicial
    this.state = {
      email: "",
      password: "",
      tipo: "",
      status: ""
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount() {
    const { usuarioId } = this.props.match.params;
    this.props.getUsuarioById(usuarioId, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    const { email, password, tipo, status } = nextProps.project;

    this.setState({
      email,
      tipo,
      status
    });
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  parseTipo(tipo) {
    if (tipo === "ROLE_ADMIN") return "0";
    if (tipo === "ROLE_TRIADOR") return "1";
    if (tipo === "ROLE_FINALIZADOR") return "2";
  }
  parseStatus(status) {
    if (status === "ATIVO") return "0";
    if (status === "INATIVO") return "1";
  }

  onSubmit(e) {
    const { usuarioId } = this.props.match.params;
    e.preventDefault();
    const updatedUser = {
      email: this.state.email,
      password: this.state.password,
      tipo: this.parseTipo(this.state.tipo),
      status: this.parseStatus(this.state.status)
    };
    this.props.updateUsuario(usuarioId, updatedUser, this.props.history);
  }

  render() {
    // const { errors } = this.state;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Atualizar Usuário</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Email"
                      name="email"
                      value={this.state.email}
                      onChange={this.onChange}
                      disabled
                    />
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames(
                        "form-control form-control-lg ",
                        {}
                      )}
                      placeholder="Senha"
                      name="password"
                      value={this.state.password}
                      onChange={this.onChange}
                    />
                  </div>

                  <div className="form-group">
                    <select
                      id="inputEstado"
                      className="form-control"
                      name="tipo"
                      value={this.state.tipo}
                      onChange={this.onChange}
                    >
                      <option value="ROLE_ADMIN">ADMIN</option>
                      <option value="ROLE_TRIADOR">TRIADOR</option>
                      <option value="ROLE_FINALIZADOR">FINALIZADOR</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <select
                      id="inputEstado"
                      className="form-control"
                      name="status"
                      value={this.state.status}
                      onChange={this.onChange}
                    >
                      <option label="ATIVO" value="ATIVO">
                        ATIVO
                      </option>
                      <option value="INATIVO">INATIVO</option>
                    </select>
                  </div>

                  <input
                    type="submit"
                    className="btn btn-dark btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateUser.propTypes = {
  getUsuarioById: PropTypes.func.isRequired,
  updateUsuario: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project.project
});

export default connect(
  mapStateToProps,
  { getUsuarioById, updateUsuario }
)(UpdateUser);
