import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';


import Main from './main';
import UsuariosList from './pages/usuario/index';
import UsuariosDados from './pages/usuario/dados';
import UsuariosCadastro from './pages/usuario/cadastro';

const Routes = () => (
    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={Main} />
            <Route exact path="/usuarios" component={UsuariosList} />
            <Route path="/usuario/:id" component={UsuariosDados} />
            <Route path="/usuario" component={UsuariosCadastro} />
        </Switch>
    </BrowserRouter>
)

export default Routes