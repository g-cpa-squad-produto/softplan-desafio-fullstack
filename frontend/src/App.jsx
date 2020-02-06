import React, { Component } from "react";
import { connect } from "react-redux";
import { BrowserRouter } from "react-router-dom";

import Routes from "./Routes";
import Header from "./components/Header";
import Login from "./views/Login";

class App extends Component {
    render() {
        const { user } = this.props.auth;

        if (!user) {
            return <Login />;
        }

        return (
            <BrowserRouter>
                <Header />
                <Routes />
            </BrowserRouter>
        );
    }
}

const mapStateToProps = state => {
    return {
        auth: state.auth
    };
};

export default connect(mapStateToProps)(App);
