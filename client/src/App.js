import React, { Component } from 'react';
import './App.css';

import SideMenu from './layout/SideMenu'

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-intro">
          <SideMenu />
        </div>
      </div>
    );
  }
}

export default App;
