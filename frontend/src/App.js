import "./components/custom.css";
import React, { Component } from "react";

import Header from "./components/Header";
import SideBar from "./components/SideBar";
import Footer from "./components/Footer";
import { HashRouter } from "react-router-dom";
import Routes from "./Routes";
import Messages from "./common/msg/messages";

class App extends Component {
  render() {
    return (
      <HashRouter>
        <div>
          <Header />
          <SideBar />
          <div className="content-wrapper">
            <Routes />
          </div>
          <Footer />
          <Messages />
        </div>
      </HashRouter>
    );
  }
}

export default App;
