import React, { Component } from "react";
import ProcessoItem from "./Processo/ProcessoItem";
import CreateProcessoButton from "./Processo/CreateProcessoButton";
import { connect } from "react-redux";
import { getProcessos } from "../actions/userActions";
import PropTypes from "prop-types";

class DashboardProcessos extends Component {
  //load do componente
  componentDidMount() {
    this.props.getProcessos();
  }

  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="font-weight-bold display-4 text-center">
                Processos
              </h1>
              <br />
              <CreateProcessoButton />

              <br />
              <hr />
              {projects.map(project => (
                <ProcessoItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

DashboardProcessos.propTypes = {
  getProcessos: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProcessos }
)(DashboardProcessos);
