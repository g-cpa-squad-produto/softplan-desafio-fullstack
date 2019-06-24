import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { Form, Container, Title } from './stylesLogin';
import AuthenticationActions from '../../store/ducks/authenticationDucks';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      errorUser: '',
    };
  }

  handleClick = (e) => {
    e.preventDefault();

    const { loginRequest, history } = this.props;
    const { username, password } = this.state;

    if (!username || !password) {
      this.setState({ errorUser: 'Preencha os campos' });
    } else {
      loginRequest(username, password, history);
    }
  };

  render() {
    const { username, password, errorUser } = this.state;
    const { fetching, error } = this.props;

    return (
      <Container>
        <Form onSubmit={this.handleClick}>
          <Title>Login</Title>
          {(errorUser || error) && <p>{errorUser || error}</p>}
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
          <button type="submit">
            {fetching ? <FontAwesomeIcon icon={faSpinner} spin /> : 'Entrar'}
          </button>
        </Form>
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  fetching: state.AuthenticationReducer.fetching,
  error: state.AuthenticationReducer.error,
});

const mapDispatchToProps = dispatch => bindActionCreators(AuthenticationActions, dispatch);

Login.propTypes = {
  fetching: PropTypes.bool.isRequired,
  loginRequest: PropTypes.func.isRequired,
  history: PropTypes.object,
  error: PropTypes.string,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Login);
