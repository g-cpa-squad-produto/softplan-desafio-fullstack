import React, { Component } from 'react'
import { Route, Router, Redirect, hashHistory } from 'react-router'

import Usuario from './acesso/usuario'
import Home from './components/Home'

export class Routes extends Component {
    render(){
        return(
        <Router history={hashHistory}>
            <Route path='/' component={Home}/>
            <Route path='/usuario' component={Usuario}/>
            <Redirect from='*' to='/'/>
        </Router>
        )
    }
};


export default Routes;