import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createParecer } from "../../actions/userActions";
import classnames from "classnames";

class AddUser extends Component {
  constructor() {
    super();

    //controlled component
    //estado inicial
    this.state = {
      texto: "",
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
    const { usuarioId } = this.props.match.params;
    const { processoId } = this.props.match.params;

    e.preventDefault();
    const newProject = {
      texto: this.state.texto
    };
    this.props.createParecer(
      usuarioId,
      processoId,
      newProject,
      this.props.history
    );
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Criar Parecer</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errors.texto
                      })}
                      placeholder="Inclua seu parecer aqui"
                      name="texto"
                      value={this.state.texto}
                      onChange={this.onChange}
                    ></textarea>
                    {errors.texto && (
                      <div className="invalid-feedback">{errors.texto}</div>
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
  createParecer: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { createParecer }
)(AddUser);
