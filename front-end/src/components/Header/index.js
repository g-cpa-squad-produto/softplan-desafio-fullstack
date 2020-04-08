import React, { Component } from "react";
import { AppBar, Toolbar, IconButton, Typography } from "@material-ui/core";
import { ExitToApp } from "@material-ui/icons";
import "./header.css";
import Cookies from "universal-cookie";

const cookies = new Cookies();

export default class Header extends Component {
  signOut = () => {
    cookies.remove("Authorization");
    cookies.remove("userType");
    // eslint-disable-next-line no-restricted-globals
    location.reload();
  };

  render() {
    return (
      <AppBar position="static">
        <Toolbar>
          <div className="halfHeader">
            <Typography component="h1" variant="h5" noWrap>
              Softplan
            </Typography>
          </div>
          <div className="halfHeader halfHeader__last">
            <IconButton
              edge="start"
              color="inherit"
              aria-label="menu"
              onClick={() => this.signOut()}
            >
              <ExitToApp />
            </IconButton>
          </div>
        </Toolbar>
      </AppBar>
    );
  }
}
