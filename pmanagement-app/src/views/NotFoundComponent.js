import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class NotFoundComponent extends Component {
  render() {
    return (
      <div className="app">
        <header className="App-header">
          <h1>404 - Sorry this page is not found</h1>
          <br />
          <div className="align-center">
            <Link to="/home">Back to Home</Link>
          </div>
        </header>
      </div>
    );
  }
}