import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import './App.css';

class Home extends Component {
    render() {
        return (
            <div>
                <h1>Home</h1>
            </div>
        );
    }
}

export default withRouter(Home);
