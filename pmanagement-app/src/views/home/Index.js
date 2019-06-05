import React, { Component } from 'react';
import { createBrowserHistory } from 'history';
import { Navbar, NavItem } from 'react-materialize';
import { Redirect, Router, Route } from 'react-router';
import { UserList } from '../user/List';
import Cookies from 'universal-cookie';

import logo from '../../logo.svg';

export class IndexHome extends Component {
    constructor(props) {
        super(props);

        this.state = props.state;
        this.cookies = new Cookies();
    }

    onClick(event) {
        event.preventDefault();
    }

    render() {
        const { authenticated } = this.cookies.get('authenticated');

        if (authenticated) {
            return (
                <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo" />
                        <p>
                            Success!
                        </p>
                    </header>
                    <React.Fragment>
                        <div className="align-center">
                            {this.props.authenticated}
                        </div>
                        <Navbar alignLinks="right">
                            <NavItem onClick={(event) => this.onClick(event)}>
                                Logout
                            </NavItem>
                        </Navbar>
                        <Router history={this.props.history}>
                            <div>
                                <Route path="/users/list" component={UserList} />
                            </div>
                        </Router>
                    </React.Fragment>
                </div>
            );
        } else {
            return <Redirect to="/login" />
        }
    }
}