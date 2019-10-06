import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteUsuarios } from "../../actions/userActions";
import { Link } from "react-router-dom";

class UserItem extends Component {
  onDeleteClick = usuarioId => {
    this.props.deleteUsuarios(usuarioId);
  };
  render() {
    const { project } = this.props;

    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{project.id}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <h3>{project.email}</h3>
              <p>{project.tipo && project.tipo.substring(5)}</p>
              <p>{project.status}</p>
            </div>
            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
                <Link to={`/updateUser/${project.id}`}>
                  <li className="list-group-item update">
                    <i className="fa fa-edit pr-1"> Atualizar</i>
                  </li>
                </Link>
                <li
                  className="list-group-item delete"
                  onClick={this.onDeleteClick.bind(this, project.id)}
                >
                  <i className="fa fa-minus-circle pr-1"> Eliminar</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UserItem.propTypes = {
  deleteUsuarios: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { deleteUsuarios }
)(UserItem);
