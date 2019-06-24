import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import UserActions from '../../store/ducks/userDucks';
import ProccessActions from '../../store/ducks/proccessDucks';
import CommentsActions from '../../store/ducks/commentsDucks';
import ViewInfo from '../../components/ViewInfo/ViewInfo';

class Dashboard extends Component {
  componentDidMount() {
    const {
      loadRequestUser, loadRequestProccess, loadRequestComments, role,
    } = this.props;
    if (role === 'ADMIN') {
      loadRequestUser();
    } else if (role === 'SUPERUSER') {
      loadRequestProccess();
    } else if (role === 'USER') {
      loadRequestComments();
    }
  }

  render() {
    const {
      role, dataUserReducer, dataProccessReducer, dataCommentsReducer,
    } = this.props;

    let infos = null;
    if (role === 'ADMIN') {
      infos = dataUserReducer;
    } else if (role === 'SUPERUSER') {
      infos = dataProccessReducer;
    } else if (role === 'USER') {
      infos = dataCommentsReducer;
    }

    return <ViewInfo rolesInfos={infos} role={role} />;
  }
}

const mapStateToProps = state => ({
  dataUserReducer: state.UserReducer.data,
  dataProccessReducer: state.ProccessReducer.data,
  dataCommentsReducer: state.CommentsReducer.data,
  role: state.AuthenticationReducer.role,
});

const mapDispatchToProps = dispatch => bindActionCreators(
  {
    ...UserActions,
    ...ProccessActions,
    ...CommentsActions,
  },
  dispatch,
);

Dashboard.propTypes = {
  loadRequestUser: PropTypes.func.isRequired,
  loadRequestProccess: PropTypes.func.isRequired,
  loadRequestComments: PropTypes.func.isRequired,
  dataUserReducer: PropTypes.arrayOf(PropTypes.object).isRequired,
  dataProccessReducer: PropTypes.arrayOf(PropTypes.object).isRequired,
  dataCommentsReducer: PropTypes.arrayOf(PropTypes.object).isRequired,
  role: PropTypes.string.isRequired,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Dashboard);
