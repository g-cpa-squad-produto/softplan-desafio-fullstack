import './components/custom.css'
import React, { Component } from 'react'  
import { HashRouter } from 'react-router-dom'


import Header from './components/Header'
import SideBar from './components/SideBar'
import Footer from './components/Footer'

import Routes from './Routes'
 
class App extends Component {

  render() {
    return (
      <HashRouter>
        <div>
        <Header />
        <SideBar />
        <div className='content-wrapper'>
          <Routes />
        </div>
        <Footer />
        </div>
      </HashRouter>
    )
  }
}

export default App;