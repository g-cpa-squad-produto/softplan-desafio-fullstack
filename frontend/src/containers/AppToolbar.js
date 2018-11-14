import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { withStyles } from '@material-ui/core/styles'
import { withRouter } from "react-router-dom"
import { logout } from '../actions/login'
import { connect } from 'react-redux'

import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button'
import IconButton from '@material-ui/core/IconButton'
import MenuIcon from '@material-ui/icons/Menu'
import Menu from '../components/Menu'
import '../css/menu.css'

class AppToolbar extends Component {
  constructor(props, context) {
    super(props, context)

    this.state = {
      visible: false
    };

    this.handleMouseDown = this.handleMouseDown.bind(this);
    this.toggleMenu = this.toggleMenu.bind(this);
  }

  handleMouseDown(e) {
    if (!this.state.visible) {
        this.toggleMenu();
    }

    e.stopPropagation();
  }

  toggleMenu() {
    this.setState({
        visible: !this.state.visible
    });
  }

  submit = (values) => {
    this.props.logout(this.props.history);
  }

  render () {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <AppBar position="static" container={16}>
          <Toolbar>
            <IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
              <MenuIcon onClick={this.handleMouseDown} />
              <Menu handleMouseDown={this.handleMouseDown}
                    menuVisibility={this.state.visible}/>
            </IconButton>
            <Typography variant="h6" color="inherit" className={classes.grow}>
              {/* WELCOME?! */}
            </Typography>
            <Button onClick={this.submit} color="inherit">Sair</Button>
          </Toolbar>
        </AppBar>
      </div>
    )
  }
}

const styles = {
  root: {
    flexGrow: 1,
    display: 'flex',
    marginBottom: '50px',
  },
  grow: {
    flexGrow: 1,
  },
  menuButton: {
    marginLeft: -12,
    marginRight: 20,
  },
}

AppToolbar.propTypes = {
  classes: PropTypes.object.isRequired,
}

AppToolbar = withStyles(styles)(AppToolbar)
AppToolbar = connect(null, {logout})(AppToolbar)

export default withRouter(AppToolbar)
