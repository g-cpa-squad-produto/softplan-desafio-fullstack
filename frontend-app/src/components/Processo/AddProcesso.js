import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProcesso } from "../../actions/userActions";
import classnames from "classnames";

class AddUser extends Component {
  constructor() {
    super();

    //controlled component
    //estado inicial
    this.state = {
      numero: "",
      descricao: "",
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
      numero: this.state.numero,
      descricao: this.state.descricao
    };
    this.props.createProcesso(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Criar Processo</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errors.numero
                      })}
                      placeholder="Número do Processo"
                      name="numero"
                      value={this.state.numero}
                      onChange={this.onChange}
                    />
                    {errors.numero && (
                      <div className="invalid-feedback">{errors.numero}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errors.descricao
                      })}
                      placeholder="Descricao do Processo"
                      name="descricao"
                      value={this.state.descricao}
                      onChange={this.onChange}
                    ></textarea>
                    {errors.descricao && (
                      <div className="invalid-feedback">{errors.descricao}</div>
                    )}
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
  createProcesso: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { createProcesso }
)(AddUser);
