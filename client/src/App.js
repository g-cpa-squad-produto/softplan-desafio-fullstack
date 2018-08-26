import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import { SideMenu } from './layout/SideMenu'

class App extends Component {
  render() {
    return (
      <div className="App">
        <header>
          <SideMenu />
        </header>
        <div className="App-intro">
        </div>
      </div>
    );
  }
}

export default App;
