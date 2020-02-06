import React, { Component } from "react";
import { connect } from "react-redux";

import LoginForm from "./LoginForm";
import {
    login
} from "../../store/auth/authActions";

class Login extends Component {

    handleForm = data => {
        const { email, senha } = data;
        this.props.login(email, senha);
    };

    render() {
        const { isLoading } = this.props.auth;

        return (
            <section className="login-container">
                <div className="box form-box">
                    <LoginForm
                        onSubmit={this.handleForm}
                        isLoading={isLoading}
                    />
                </div>
            </section>
        );
    }
}

const mapStateToProps = state => {
    return { auth: state.auth };
};

export default connect(
    mapStateToProps,
    { login }
)(Login);
