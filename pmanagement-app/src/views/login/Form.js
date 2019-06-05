import React from "react";
import axios from 'axios';
import Cookies from 'universal-cookie';

import '../../index.css';

export class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            cookies: new Cookies()
        };
    }

    render() {
        const { username, password } = this.state;
        return (
            <div className="app">
                <h1>Login</h1>
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor="username">Email</label>
                    <input
                        id="username"
                        name="username"
                        type="text"
                        placeholder="Enter your username"
                        value={username}
                        onChange={this.handleChange}
                    />
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        name="password"
                        type="password"
                        placeholder="Enter your password"
                        value={password}
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

    handleSubmit = event => {
        event.preventDefault();

        // axios({
        //     url: 'http://localhost:8080/api/login?username=' + this.state.username + '&password=' + this.state.password,
        //     method: 'post',
        //     headers: {
        //         'Authentication': ''
        //     }
        // })
        axios.post('http://localhost:8080/api/login?username=' + this.state.username + "&password=" + this.state.password)
            .then((result) => {
                this.state.cookies.set('authenticated', true, { path: '/' });
                this.props.history.push('/');
            })
            .catch((err) => {
                this.state.cookies.set('authenticated', false, { path: '/' });

                console.log(err);
            });
    };
}