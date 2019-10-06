import React, { Component } from "react";
import ProcessoPendentesItem from "./Processo/ProcessoPendentesItem";
import { connect } from "react-redux";
import { getProcessosSemParecer } from "../actions/userActions";
import PropTypes from "prop-types";

class DashboardProcessosPendentes extends Component {
  //load do componente
  componentDidMount() {
    this.props.getProcessosSemParecer();
  }

  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="font-weight-bold display-4 text-center">
                Processos Pendentes de Parecer
              </h1>
              <br />

              <br />
              <hr />

              {projects.map(project => (
                <ProcessoPendentesItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

DashboardProcessosPendentes.propTypes = {
  getProcessosSemParecer: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProcessosSemParecer }
)(DashboardProcessosPendentes);
