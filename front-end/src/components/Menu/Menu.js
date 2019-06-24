import React, { Component } from 'react';
import CheeseburgerMenu from 'cheeseburger-menu';
import HamburgerMenu from 'react-hamburger-menu';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import MenuContent from './MenuContent/MenuContent';
import { Container, Title } from './stylesMenu';
import AuthenticationActions from '../../store/ducks/authenticationDucks';

class Menu extends Component {
  constructor(props) {
    super(props);

    this.state = {
      menuOpen: false,
      menuOptions: [],
    };
  }

  componentDidMount() {
    const { role } = this.props;

    if (role === 'ADMIN') {
      this.setState({
        menuOptions: [
          {
            name: 'Visualizar usuário',
            link: '/dashboard',
          },
          {
            name: 'Cadastrar usuário',
            link: '/createUser',
          },
        ],
      });
    } else if (role === 'SUPERUSER') {
      this.setState({
        menuOptions: [
          {
            name: 'Visualizar processos',
            link: '/dashboard',
          },
          {
            name: 'Cadastrar processos',
            link: '/createProccess',
          },
        ],
      });
    } else if (role === 'USER') {
      this.setState({
        menuOptions: [
          {
            name: 'Visualizar parecer',
            link: '/dashboard',
          },
          {
            name: 'Cadastrar parecer',
            link: '/createComments',
          },
        ],
      });
    }
  }

  openMenu = () => {
    this.setState({ menuOpen: true });
  };

  closeMenu = () => {
    this.setState({ menuOpen: false });
  };

  render() {
    const { menuOpen, menuOptions } = this.state;
    const { username, logout } = this.props;

    return (
      <Container>
        <CheeseburgerMenu isOpen={menuOpen} closeCallback={this.closeMenu}>
          <MenuContent
            options={menuOptions}
            username={username}
            logout={logout}
            closeCallback={this.closeMenu}
          />
        </CheeseburgerMenu>

        <HamburgerMenu
          isOpen={menuOpen}
          menuClicked={this.openMenu}
          width={32}
          height={24}
          strokeWidth={3}
          rotate={0}
          color="black"
          animationDuration={0.5}
        />
        <Title>Gerenciador de Processos</Title>
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  role: state.AuthenticationReducer.role,
  username: state.AuthenticationReducer.username,
});

const mapDispatchToProps = dispatch => bindActionCreators(AuthenticationActions, dispatch);

Menu.propTypes = {
  role: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
  logout: PropTypes.func.isRequired,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Menu);
