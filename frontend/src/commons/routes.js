import React from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import { ToastsContainer, ToastsContainerPosition, ToastsStore } from 'react-toasts';

// commons
import { canRoute } from './auth';

// containers
import HomeContainer from '../containers/Home/home';
import LoginContainer from '../containers/Login/login';
import PageNotFoundContainer from '../containers/PageNotFound/page-not-found';

import UsuariosListContainer from '../containers/Usuarios/list';
import UsuariosFormContainer from '../containers/Usuarios/form';

import ProcessosListContainer from '../containers/Processos/list';
import ProcessosFormContainer from '../containers/Processos/form';
import ProcessosAtribuicaoContainer from '../containers/Processos/atribuicao';

import AtribuicoesListContainer from '../containers/Atribuicoes/list';
import AtribuicoesParecerContainer from '../containers/Atribuicoes/parecer';

// components
import Header from '../components/Header/header';
import Footer from '../components/Footer/footer';

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route { ... rest } render={props => (
        canRoute(rest) ? (
            <Component { ... props} />
        ) : (
            <Redirect to={{ pathname: '/login', state: { from: props.location} }} />
        )
    )} />
)

const Routes = () => (
    <BrowserRouter>
        <ToastsContainer position={ToastsContainerPosition.TOP_RIGHT} store={ToastsStore}/>
        <Header />
        <Switch>
            <Route exact path="/" component={HomeContainer} />
            <Route path="/login" component={LoginContainer} />
            <PrivateRoute exact path="/usuarios" perfil="ADMINISTRADOR" component={UsuariosListContainer} />
            <PrivateRoute exact path="/usuarios/new" perfil="ADMINISTRADOR" component={UsuariosFormContainer} />
            <PrivateRoute exact path="/usuarios/edit/:id" perfil="ADMINISTRADOR" component={UsuariosFormContainer} />
            <PrivateRoute exact path="/processos" perfil="TRIADOR" component={ProcessosListContainer} />
            <PrivateRoute exact path="/processos/new" perfil="TRIADOR" component={ProcessosFormContainer} />
            <PrivateRoute exact path="/processos/:id/atribuir" perfil="TRIADOR" component={ProcessosAtribuicaoContainer} />
            <PrivateRoute exact path="/atribuicoes" perfil="FINALIZADOR" component={AtribuicoesListContainer} />
            <PrivateRoute exact path="/atribuicoes/:id/parecer" perfil="FINALIZADOR" component={AtribuicoesParecerContainer} />
            <Route path="*" component={PageNotFoundContainer} />
        </Switch>
        <Footer />
    </BrowserRouter>
);

export default Routes;