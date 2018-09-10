import React, {Component} from 'react';
import classNames from 'classnames';
import {Drawer, IconButton, Divider, List} from '@material-ui/core';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ExitIcon from "@material-ui/icons/ExitToApp";
import NavItems from './NavItems';
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import * as authActions from '../../../store/auth/action';

class DrawerCustom extends Component {
    onLogout = () => {
        this.props.dispatch(
            authActions.logout(res => {
                this.props.history.push('/login');
            })
        );
    };

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
                <NavItems classes={this.props.classes}/>

                <List className={classes.listLogout}>
                    <div>
                        <ListItem button onClick={this.onLogout}>
                            <ListItemIcon>
                                <ExitIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Logout"/>
                        </ListItem>
                    </div>
                </List>
            </Drawer>
        );
    }
}

export default DrawerCustom;