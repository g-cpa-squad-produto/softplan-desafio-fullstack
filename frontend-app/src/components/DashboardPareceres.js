import React, { Component } from "react";
import ParecerItem from "./Parecer/ParecerItem";
import { connect } from "react-redux";
import { getPareceresByUsuario } from "../actions/userActions";
import PropTypes from "prop-types";

class DashboardPareceres extends Component {
  //load do componente
  componentDidMount() {
    const { usuarioId } = this.props.match.params;
    this.props.getPareceresByUsuario(usuarioId);
  }

  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="font-weight-bold display-4 text-center">
                Meus pareceres
              </h1>
              <br />

              <br />
              <hr />

              {projects.map(project => (
                <ParecerItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

DashboardPareceres.propTypes = {
  getPareceresByUsuario: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getPareceresByUsuario }
)(DashboardPareceres);
