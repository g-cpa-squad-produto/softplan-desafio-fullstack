import React, {Component} from 'react';
import classNames from 'classnames';
import {Drawer, IconButton, Divider, List, withStyles} from '@material-ui/core';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import {styles} from "./styles";
import {mainListItems, secondaryListItems} from './listItems';

class DrawerCustom extends Component {
    render() {
        const { classes } = this.props;

        return (
            <Drawer variant="permanent" open={this.props.open}
                    classes={{paper: classNames(classes.drawerPaper, !this.props.open && classes.drawerPaperClose)}}>
                <div className={classes.toolbarIcon}>
                    <IconButton onClick={this.props.handleDrawerClose}>
                        <ChevronLeftIcon />
                    </IconButton>
                </div>
                <Divider />
                <List>{mainListItems}</List>
                <Divider />
                <List>{secondaryListItems}</List>
            </Drawer>
        );
    }
}

export default withStyles(styles)(DrawerCustom);