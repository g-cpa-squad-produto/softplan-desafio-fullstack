import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen, faTrashAlt, faSpinner } from '@fortawesome/free-solid-svg-icons';
import UserActions from '../../store/ducks/userDucks';
import ProccessActions from '../../store/ducks/proccessDucks';
import CommentsActions from '../../store/ducks/commentsDucks';

import {
  Container,
  List,
  ListItems,
  ListItem,
  Button,
  ListHead,
  ListHeadItem,
  HeadItem,
} from './stylesViewInfo';

class ViewInfo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isDelete: false,
    };
  }

  handleClick = (clicked, info) => {
    const { history, deleteRequestUser, deleteRequestProccess, deleteRequestComments,role } = this.props;
    
    if (clicked === 'editar') {
      if (role === 'ADMIN') {
        history.push('./createuser', info);
      } else if (role === 'SUPERUSER') {
        history.push('./createProccess', info);
      } else {
        history.push('./createComments', info);
      }
    } else {
      this.setState({ isDelete: false });

      confirmAlert({
        message: 'Deseja excluir usuário ?',
        buttons: [
          {
            label: 'Sim',
            onClick: () => {
              if (role === 'ADMIN') {
                deleteRequestUser(info.id);
              } else if (role === 'SUPERUSER') {
                deleteRequestProccess(info.id);
              } else {
                deleteRequestComments(info.id);
              }
            },
          },
          {
            label: 'Não',
          },
        ],
      });
      this.setState({ isDelete: true });
    }
  };

  render() {
    const { rolesInfos, fetchingUser, role, fetchingProccess } = this.props;
    const { isDelete } = this.state;

    return (
      <Container>
        {(fetchingUser || fetchingProccess) && !isDelete ? (
          <FontAwesomeIcon icon={faSpinner} spin />
        ) : (
          <List>
            <ListHead>
              <ListHeadItem>Nome</ListHeadItem>
              <ListHeadItem>
                <HeadItem>Ações</HeadItem>
              </ListHeadItem>
            </ListHead>

            {rolesInfos.map(rolesInfo => {
              let valueName = '';
              if (role === 'ADMIN') {
                valueName = rolesInfo.username;
              } else if (role === 'SUPERUSER') {
                valueName = rolesInfo.name;
              } else {
                valueName = rolesInfo.comment;
              }

              return (
                <ListItems key={rolesInfo.id}>
                  <ListItem>{valueName}</ListItem>
                  <ListItem>
                    <Button onClick={() => this.handleClick('editar', rolesInfo)} title="Editar">
                      <FontAwesomeIcon icon={faPen} />
                    </Button>
                    <Button onClick={() => this.handleClick('remover', rolesInfo)} title="Remover">
                      <FontAwesomeIcon icon={faTrashAlt} />
                    </Button>
                  </ListItem>
                </ListItems>
              );
            })}
          </List>
        )}
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  fetchingUser: state.UserReducer.fetching,
  fetchingProccess: state.ProccessReducer.fetching,
  fetchingComments: state.CommentsReducer.fetching,
});

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    {
      ...UserActions,
      ...ProccessActions,
      ...CommentsActions,
    },
    dispatch,
  );

ViewInfo.propTypes = {
  fetchingUser: PropTypes.bool.isRequired,
  fetchingProccess: PropTypes.bool.isRequired,
  fetchingComments: PropTypes.bool.isRequired,
  role: PropTypes.string.isRequired,
  rolesInfos: PropTypes.array.isRequired,
  history: PropTypes.object.isRequired,
  deleteRequestUser: PropTypes.func.isRequired,
  deleteRequestProccess: PropTypes.func.isRequired,
  deleteRequestComments: PropTypes.func.isRequired,
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps,
  )(ViewInfo),
);
