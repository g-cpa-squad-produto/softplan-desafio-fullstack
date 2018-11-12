import React, { Component } from 'react'
import Typography from '@material-ui/core/Typography'

import {
  USER_STORAGE_KEY,
  ADMIN,
} from '../constants'

class Menu extends Component {

  render() {
    let role = JSON.parse(localStorage.getItem(USER_STORAGE_KEY)).role
    let visibility = "hide";

    if (this.props.menuVisibility) {
      visibility = "show";
    }

    return (
      <div id="flyoutMenu" onMouseDown={this.props.handleMouseDown} className={visibility}>
        <Typography className="mt-2 mb-2" variant="h4">Menu</Typography>
        <Typography className="mt-2 mb-2" variant="h6"><a href="/home">Início</a></Typography>
        <Typography className={"mt-2 mb-2 " +(role === ADMIN ? 'show' : 'hidden')} variant="h6"><a href="/user">Usuários</a></Typography>
      </div>
    );
  }
}

export default Menu
