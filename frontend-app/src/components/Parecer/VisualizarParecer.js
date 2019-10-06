import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { getParecerByProcesso } from "../../actions/userActions";
import classnames from "classnames";

class AddUser extends Component {
  constructor() {
    super();

    //controlled component
    //estado inicial
    this.state = {
      texto: ""
    };
  }

  componentDidMount() {
    const { processoId } = this.props.match.params;
    this.props.getParecerByProcesso(processoId, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    const { texto } = nextProps.project;

    this.setState({
      texto
    });
  }

  render() {
    const { processoId } = this.props.match.params;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">
                  Parecer do processo {processoId}
                </h5>
                <hr />
                <form>
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg "
                      placeholder="Inclua seu parecer aqui"
                      name="texto"
                      value={this.state.texto}
                      disabled
                    ></textarea>
                  </div>
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
  getParecerByProcesso: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project.project
});
export default connect(
  mapStateToProps,
  { getParecerByProcesso }
)(AddUser);
