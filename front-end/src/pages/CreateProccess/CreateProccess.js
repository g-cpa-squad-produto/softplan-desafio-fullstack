import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { Form, Container, Error, Success } from './stylesCreateProccess';
import ProccessActions from '../../store/ducks/proccessDucks';

class CreateProccess extends Component {
  state = {
    name: '',
    password: '',
    role: 'admin',
    hasError: null,
    location: false,
    id: '',
  };

  componentDidMount() {
    const { location } = this.props;
    if (location.state !== undefined) {
      const { id, name } = location.state;
      this.setState({
        name,
        location: true,
        id,
      });
    }
  }

  handleCreateUser = (e) => {
    e.preventDefault();
    const { saveRequestProccess, updateRequestProccess,  history } = this.props;
    const {id,  name, location  } = this.state;
    this.setState({success: ''});
    
    if (!name) {
      this.setState({ hasError: 'Preencha os campos' });
    } else {

      location
        ? updateRequestProccess({
          id,
          name,
          history,
        })
        : saveRequestProccess({ name, history });
    }
  };

  render() {
    const { name, hasError } = this.state;
    const { fetching, error } = this.props.proccessReducer;
    let msgSuccess=''
    if(this.props.history.location.state === 'success'){
      msgSuccess = 'Processo salvo com sucesso';
    }

    return (
      <Container>
        <Form onSubmit={this.handleCreateUser}>
          {((hasError || error ) && (<Error>{hasError || error}</Error> )) || 
          (msgSuccess && <Success>{msgSuccess}</Success>)}
          <input
            type="text"
            placeholder="Nome de processo"
            onChange={e => this.setState({ name: e.target.value })}
            value={name}
          />
          <button type="submit">
            {fetching ? <FontAwesomeIcon icon={faSpinner} spin /> : 'Salvar'}
          </button>
        </Form>
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  proccessReducer: state.ProccessReducer,
});

const mapDispatchToProps = dispatch => bindActionCreators(ProccessActions, dispatch);

CreateProccess.propTypes = {
  location: PropTypes.shape({
    name: PropTypes.string,
   }),
  saveRequestProccess: PropTypes.func.isRequired,
  updateRequestProccess: PropTypes.func.isRequired,
  proccessReducer: PropTypes.shape({
    fetching: PropTypes.bool.isRequired,
    error: PropTypes.string,
  }),
  history: PropTypes.object,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(CreateProccess);
