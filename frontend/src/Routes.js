import React, { Component } from 'react'
import { Switch, Route, Redirect } from 'react-router'

import Usuario from './acesso/usuario'
import Processo from './processo/processo'
import Home from './components/Home'


export class Routes extends Component {
    render(){
        return(
        <Switch>
            <Route exact path='/' component={Home} />
            <Route path='/usuario' component={Usuario} />
            <Route path='/processo' component={Processo} />
            <Redirect from='*' to='/' />
        </Switch>
        )
    }
};


export default Routes;