import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createUsuario } from "../../actions/userActions";
import classnames from "classnames";

class AddUser extends Component {
  constructor() {
    super();

    //controlled component
    //estado inicial
    this.state = {
      email: "",
      password: "",
      tipo: "0",
      status: "0",
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  //life cycles hooks
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  //para mudar o estado
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProject = {
      email: this.state.email,
      password: this.state.password,
      tipo: parseInt(this.state.tipo),
      status: parseInt(this.state.status)
    };
    this.props.createUsuario(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Criar Usuário</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errors.email
                      })}
                      placeholder="Email"
                      name="email"
                      value={this.state.email}
                      onChange={this.onChange}
                    />
                    {errors.email && (
                      <div className="invalid-feedback">{errors.email}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errors.password
                      })}
                      placeholder="Senha"
                      name="password"
                      value={this.state.password}
                      onChange={this.onChange}
                    />
                    {errors.password && (
                      <div className="invalid-feedback">{errors.password}</div>
                    )}
                  </div>

                  <div className="form-group">
                    <select
                      id="inputEstado"
                      className="form-control"
                      name="tipo"
                      value={this.state.tipo}
                      onChange={this.onChange}
                    >
                      <option value="0">ADMIN</option>
                      <option value="1">TRIADOR</option>
                      <option value="2">FINALIZADOR</option>
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
                      <option label="ATIVO" value="0">
                        ATIVO
                      </option>
                      <option value="1">INATIVO</option>
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

AddUser.propTypes = {
  createUsuario: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { createUsuario }
)(AddUser);
