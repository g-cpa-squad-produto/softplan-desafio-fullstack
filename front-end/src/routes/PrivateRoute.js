import React, { Fragment } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Menu from '../components/Menu/Menu';

const PrivateRoute = ({ component: Component, isLogged, ...rest }) => (
  <Route
    {...rest}
    render={props => (isLogged ? (
      <Fragment>
        <Menu  />
        <Component {...props} />
      </Fragment>
    ) : (
      <Redirect to={{ pathname: '/', state: { from: props.location } }} />
    ))
    }
  />
);

const mapStateToProps = state => ({
  isLogged: state.AuthenticationReducer.isLogged,
});

PrivateRoute.prototype = {
  component: PropTypes.shape({
    Component: PropTypes.object.isRequired,
    isLogged: PropTypes.bool.isRequired,
  }).isRequired,
};

export default connect(
  mapStateToProps,
  {},
)(PrivateRoute);
