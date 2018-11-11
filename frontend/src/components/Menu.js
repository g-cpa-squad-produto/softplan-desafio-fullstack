import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { Link } from 'react-router'
import Typography from '@material-ui/core/Typography'

class Menu extends Component {

  render() {
    let visibility = "hide";

    if (this.props.menuVisibility) {
      visibility = "show";
    }

    return (
      <div id="flyoutMenu" onMouseDown={this.props.handleMouseDown} className={visibility}>
        <Typography className="mt-2 mb-2" variant="h4">Menu</Typography>
        <Typography className="mt-2 mb-2" variant="h6"><a href="/home">Início</a></Typography>
        <Typography className="mt-2 mb-2" variant="h6"><a href="/user">Usuários</a></Typography>
      </div>
    );
  }
}

export default Menu
