import React from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPortrait } from '@fortawesome/free-solid-svg-icons';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import {
  Container, Item, StyledLink, UserInfo,
} from './stylesMenuContent';

const handleClick = (logout, closeCallback) => {
  closeCallback();
  confirmAlert({
    message: 'Deseja sair ?',
    buttons: [
      {
        label: 'Sim',
        onClick: () => {
          logout();
        },
      },
      {
        label: 'Não',
      },
    ],
  });
};

const Menu = ({
  options, username, logout, closeCallback,
}) => (
  <Container>
    <UserInfo>
      <FontAwesomeIcon icon={faPortrait} />
      {username}
    </UserInfo>
    {options.map(option => (
      <Item key={option.name}>
        <StyledLink onClick={closeCallback} to={option.link}>
          {option.name}
        </StyledLink>
      </Item>
    ))}

    <StyledLink to="#" onClick={() => handleClick(logout, closeCallback)}>
      Logout
    </StyledLink>
  </Container>
);

Menu.propTypes = {
  options: PropTypes.arrayOf(PropTypes.object).isRequired,
  username: PropTypes.string.isRequired,
  logout: PropTypes.func.isRequired,
  closeCallback: PropTypes.func.isRequired,
};

export default Menu;
