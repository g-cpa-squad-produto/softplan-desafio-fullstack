import React, { Component } from 'react';
import { Link, BrowserRouter as Router, Route } from 'react-router-dom'

import { List, ListItem, ListItemIcon, ListItemText } from '@material-ui/core'

import { Home } from '../home/Home'
import { UsuarioList } from '../usuario/UsuarioList';

export class SideMenu extends Component {

    render() {
        return (
            <Router>
                <div>
                    <ListItem button component={Link} to="/">
                        <ListItemText inset primary="Home" />
                    </ListItem>
                    <ListItem button component={Link} to="/usuarios">
                        <ListItemText inset primary="Usuários" />
                    </ListItem>
                    {/* <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/usuarios">Usuario</Link>
                        </li>
                    </ul> */}

                    <hr />

                    <Route exact path="/" component={Home} />
                    <Route path="/usuarios" component={UsuarioList} />
                </div>
            </Router>
        )
    }

}