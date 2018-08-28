import React, { Component } from 'react';
import { Link, BrowserRouter as Router, Route } from 'react-router-dom'
import {
    ListItem,
    ListItemIcon,
    ListItemText,
    IconButton,
    AppBar,
    Divider,
    Toolbar,
    Typography,
    Drawer,
    withStyles,
} from '@material-ui/core'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import MenuIcon from '@material-ui/icons/Menu';

import { Home as HomeIcon, People, Assignment } from '@material-ui/icons'

import classNames from 'classnames';

import { Home } from '../home/Home'
import { UsuarioList } from '../usuario/UsuarioList';
import { NovoUsuarioComponent } from '../usuario/NovoUsuario'
import { ProcessoList } from '../processo/ProcessoList'
import { NovoProcesso } from '../processo/NovoProcesso';

const drawerWidth = 240;

const styles = theme => ({
    root: {
        flexGrow: 1,
        zIndex: 1,
        overflow: 'hidden',
        position: 'relative',
        display: 'flex',
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    appBarShift: {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginLeft: 12,
        marginRight: 36,
    },
    hide: {
        display: 'none',
    },
    drawerPaper: {
        position: 'relative',
        whiteSpace: 'nowrap',
        width: drawerWidth,
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    drawerPaperClose: {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing.unit * 7,
        [theme.breakpoints.up('sm')]: {
            width: theme.spacing.unit * 9,
        },
    },
    toolbar: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: '0 8px',
        ...theme.mixins.toolbar,
    },
    content: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.default,
        padding: 50,
    },
});

class SideMenu extends Component {

    state = {
        open: false,
    };

    handleDrawerOpen = () => {
        this.setState({ open: true });
    };

    handleDrawerClose = () => {
        this.setState({ open: false });
    };

    render() {
        const { classes, theme } = this.props;
        return (
            <Router>
                <div className={classes.root}>
                    <AppBar
                        position="absolute"
                        className={classNames(classes.appBar, this.state.open && classes.appBarShift)}>
                        <Toolbar disableGutters={!this.state.open}>
                            <IconButton
                                color="inherit"
                                aria-label="Open drawer"
                                onClick={this.handleDrawerOpen}
                                className={classNames(classes.menuButton, this.state.open && classes.hide)}>
                                <MenuIcon />
                            </IconButton>
                            <Typography variant="title" color="inherit" noWrap>
                                Desafio Fullstack
                            </Typography>
                        </Toolbar>
                    </AppBar>
                    <Drawer
                        variant="permanent"
                        classes={{
                            paper: classNames(classes.drawerPaper, !this.state.open && classes.drawerPaperClose),
                        }}
                        open={this.state.open}>

                        <div className={classes.toolbar}>
                            <IconButton onClick={this.handleDrawerClose}>
                                {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
                            </IconButton>
                        </div>

                        <Divider />
                        <ListItem button component={Link} to="/">
                            <ListItemIcon>
                                <HomeIcon />
                            </ListItemIcon>
                            <ListItemText inset primary="Home" />
                        </ListItem>
                        <ListItem button component={Link} to="/usuarios">
                            <ListItemIcon>
                                <People />
                            </ListItemIcon>
                            <ListItemText inset primary="Usuários" />
                        </ListItem>
                        <ListItem button component={Link} to="/processos">
                            <ListItemIcon>
                                <Assignment />
                            </ListItemIcon>
                            <ListItemText inset primary="Processos" />
                        </ListItem>

                    </Drawer>

                    <main className={classes.content}>
                        <div className={classes.toolbar} />
                        <Route exact path="/" component={Home} />


                        <Route path="/usuarios" component={UsuarioList} />
                        <Route path="/usuario/novo" component={NovoUsuarioComponent} />

                        <Route path="/processos" component={ProcessoList} />
                        <Route path="/processo/novo" component={NovoProcesso} />
                    </main>

                </div>
            </Router>
        )
    }

}

export default withStyles(styles, { withTheme: true })(SideMenu);
