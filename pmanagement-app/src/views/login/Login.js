import React, { Component } from "react";
import api from "../../services/api";
import { login } from "../../services/auth";

class Login extends Component {

    state = {
        username: "",
        password: "",
        error: ""
    };

    render() {
        return (
            <div className="app">
                <form onSubmit={this.handleLogIn}>
                    <h1>Login</h1>
                    {this.state.error && <div className="error-message"><p>{this.state.error}</p></div>}
                    <label htmlFor="username">Username</label>
                    <input
                        id="username"
                        name="username"
                        type="text"
                        placeholder="Enter your username"
                        value={this.state.username}
                        onChange={this.handleChange}
                    />
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        name="password"
                        type="password"
                        placeholder="Enter your password"
                        value={this.state.password}
                        onChange={this.handleChange}
                    />
                    <div className="align-center">
                        <button type="submit">Login</button>
                    </div>
                </form>
            </div>
            );
    }

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    handleLogIn = async event => {
        event.preventDefault();

        const { username, password } = this.state;
        if (!username || !password) {
            this.setState({
                error: "Fill in username and password to continue!"
            });
        } else {
            try {
                const response = await api.post(
                    "/api/login?username=" + this.state.username + "&password=" + this.state.password
                );
                login(response.data);
                this.props.history.push("/");
            } catch (err) {
                this.setState({
                    error:
                        "ERROR: Credentials check. T.T"
                });
            }
        }
    };
};

export default Login;