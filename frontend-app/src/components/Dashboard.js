import React, { Component } from "react";
import UserItem from "./User/UserItem";
import CreateUserButton from "./User/CreateUserButton";
import { connect } from "react-redux";
import { getUsuarios } from "../actions/userActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  //load do componente
  componentDidMount() {
    this.props.getUsuarios();
  }

  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="font-weight-bold display-4 text-center">
                Usuários
              </h1>
              <br />
              <CreateUserButton />

              <br />
              <hr />
              {projects.map(project => (
                <UserItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  getUsuarios: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getUsuarios }
)(Dashboard);
