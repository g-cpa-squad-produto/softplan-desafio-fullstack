import React, { Component } from "react";
import axios from "axios";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";

import { BrowserRouter } from "react-router-dom";

import App from "./App";
import Auth from "./auth/auth";
import { validateToken } from "./auth/authActions";

const baseURL = "http://localhost:8081/desafiosoftplan";

class AuthOrApp extends Component {
  componentWillMount() {
    axios.defaults.baseURL = baseURL;
    if (this.props.auth.token) {
      this.props.validateToken(this.props.auth.token);
    }
  }

  render() {
    const { user, token, validToken } = this.props.auth;
    if (validToken) {
      axios.defaults.headers.common["token"] = "Bearer " + token;
      return (
        <BrowserRouter>
          <App>{this.props.children}</App>
        </BrowserRouter>
      );
    } else if (!user && !validToken) {
      return <Auth />;
    } else {
      return false;
    }
  }
}

const mapStateToProps = state => ({ auth: state.auth });
const mapDispatchToProps = dispatch =>
  bindActionCreators({ validateToken }, dispatch);
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AuthOrApp);
