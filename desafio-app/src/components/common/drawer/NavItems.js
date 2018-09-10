import React, {Component} from 'react';
import {List, ListItem, ListItemIcon, ListItemText} from '@material-ui/core';
import DashboardIcon from '@material-ui/icons/Dashboard';
import PeopleIcon from '@material-ui/icons/People';
import AssignmentIcon from '@material-ui/icons/Assignment';
import {Link} from 'react-router-dom';
import AuthorizationComponent from "../../auth/AuthorizationComponent";

class NavItems extends Component {
    render() {
        const { classes } = this.props;

        return (
            <List>
                <Link to='/' className={classes.noLinkDecoration}>
                    <ListItem button>
                        <ListItemIcon>
                            <DashboardIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Dashboard"/>
                    </ListItem>
                </Link>

                <AuthorizationComponent roles={['ROLE_ADMIN']}>
                    <Link to='/usuarios' className={classes.noLinkDecoration}>
                        <ListItem button>
                            <ListItemIcon>
                                <PeopleIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Usuários"/>
                        </ListItem>
                    </Link>
                </AuthorizationComponent>

                <AuthorizationComponent roles={['ROLE_TRIADOR']}>
                    <Link to='/processos' className={classes.noLinkDecoration}>
                        <ListItem button>
                            <ListItemIcon>
                                <AssignmentIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Processos"/>
                        </ListItem>
                    </Link>
                </AuthorizationComponent>

                <AuthorizationComponent roles={['ROLE_FINALIZADOR']}>
                    <Link to='/processos/finalizadores' className={classes.noLinkDecoration}>
                        <ListItem button>
                            <ListItemIcon>
                                <AssignmentIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Processos"/>
                        </ListItem>
                    </Link>
                </AuthorizationComponent>
            </List>
        );
    }
}

export default NavItems;