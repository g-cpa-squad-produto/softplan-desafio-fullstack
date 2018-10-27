import React from "react";

import PaginaNaoEncontrada from './container/PaginaNaoEncontrada';

import AppliedRoute from "./components/AppliedRoute";

import { Route, Switch } from "react-router-dom";

import Home from "./container/Home";
import Login from "./container/Login";
import Usuario from "./container/Usuario";
import UsuarioForm from './container/UsuarioForm';
import Processo from "./container/Processo";
import ProcessoForm from "./container/ProcessoForm";

export default ({authProps}) => 
    <Switch>
        <AppliedRoute path="/" exact component={Home} props={authProps} />
        <AppliedRoute path="/login" exact component={Login} props={authProps} />
        <AppliedRoute path="/usuarios" exact component={Usuario} props={authProps} />
        <AppliedRoute path="/usuarios/novo" exact component={UsuarioForm} props={authProps} />
        <AppliedRoute path="/usuarios/:codigo" exact component={UsuarioForm} props={authProps} />
        <AppliedRoute path="/processos" exact component={Processo} props={authProps} />
        <AppliedRoute path="/processos/novo" exact component={ProcessoForm} props={authProps} />
        <AppliedRoute path="/processos/:codigo" exact component={ProcessoForm} props={authProps} />
        { /* Finally, catch all unmatched routes */ }
        <Route component={PaginaNaoEncontrada}/>
    </Switch>;