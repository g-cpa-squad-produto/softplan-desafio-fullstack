import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { Form, Container, Error, Success } from './stylesCreateUser';
import UserActions from '../../store/ducks/userDucks';

class CreateUser extends Component {
  state = {
    username: '',
    password: '',
    role: 'admin',
    hasError: null,
    location: false,
    id: '',
  };

  componentDidMount() {
    const { location } = this.props;
    if (location.state !== undefined) {
      const { id, username, password, roles } = location.state;
      this.setState({
        username,
        password,
        role: roles[0].role,
        location: true,
        id,
      });
    }
  }

  handleCreateUser = (e) => {
    e.preventDefault();
    const { saveRequestUser, updateRequestUser,  history } = this.props;
    const {id,  username, password, location, role  } = this.state;
    this.setState({success: ''});

    if (!username || !password) {
      this.setState({ hasError: 'Preencha os campos' });
    } else {

      location
        ? updateRequestUser({
          id,
          username,
          password,
          role,
          history,
        })
        : saveRequestUser({ username, password, role, history });
    }
  };

  handleChange = (e) => {
    this.setState({ role: e.target.value });
  };

  render() {
    const { username, password, hasError, role } = this.state;
    const { fetching, error } = this.props.userReducer;
    let msgSuccess=''
    if(this.props.history.location.state === 'success'){
      msgSuccess = 'Usuário salvo com sucesso';
    }

    return (
      <Container>
        <Form onSubmit={this.handleCreateUser}>
          {((hasError || error ) && (<Error>{hasError || error}</Error> )) || 
          (msgSuccess && <Success>{msgSuccess}</Success>)}
          <input
            type="text"
            placeholder="Nome de usuário"
            onChange={e => this.setState({ username: e.target.value })}
            value={username}
          />
          <input
            type="password"
            placeholder="Senha"
            onChange={e => this.setState({ password: e.target.value })}
            value={password}
          />
          <select value={role} onChange={this.handleChange}>
            <option value="admin">Usuário admin</option>
            <option value="superuser">Usuário triador</option>
            <option value="user">Usuário finalizador</option>
          </select>

          <button type="submit">
            {fetching ? <FontAwesomeIcon icon={faSpinner} spin /> : 'Salvar'}
          </button>
        </Form>
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  userReducer: state.UserReducer,
});

const mapDispatchToProps = dispatch => bindActionCreators(UserActions, dispatch);

CreateUser.propTypes = {
  location: PropTypes.shape({
    username: PropTypes.string,
    password: PropTypes.string,
    role: PropTypes.string,
  }),
  saveRequestUser: PropTypes.func.isRequired,
  updateRequestUser: PropTypes.func.isRequired,
  userReducer: PropTypes.shape({
    fetching: PropTypes.bool.isRequired,
    error: PropTypes.string,
  }),
  history: PropTypes.object,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(CreateUser);
