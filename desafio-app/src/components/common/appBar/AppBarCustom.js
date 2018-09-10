import React, {Component} from 'react';
import classNames from 'classnames';
import {AppBar, Toolbar, IconButton, Typography, withStyles} from '@material-ui/core';
import DrawerCustom from '../drawer/DrawerCustom';
import MenuIcon from '@material-ui/icons/Menu';
import {styles} from './styles';

class AppBarCustom extends Component {
    state = {
        open: true,
    };

    handleDrawerOpen = () => {
        this.setState({open: true});
    };

    handleDrawerClose = () => {
        this.setState({open: false});
    };

    render() {
        const { classes } = this.props;

        return (
            [
                <AppBar key={0} position="absolute" className={classNames(classes.appBar, this.state.open && classes.appBarShift)}>
                    <Toolbar disableGutters={!this.state.open} className={classes.toolbar}>
                        <IconButton color="inherit" aria-label="Open drawer" onClick={this.handleDrawerOpen}
                                    className={classNames(classes.menuButton, this.state.open && classes.menuButtonHidden)}>
                            <MenuIcon/>
                        </IconButton>
                        <Typography variant="title" color="inherit" noWrap className={classes.title}>
                            Dashboard
                        </Typography>
                    </Toolbar>
                </AppBar>,
                <DrawerCustom key={1} open={this.state.open} handleDrawerClose={this.handleDrawerClose.bind(this)} {...this.props} />
            ]
        );
    }
}

export default withStyles(styles)(AppBarCustom);