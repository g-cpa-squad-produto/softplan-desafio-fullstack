import React from 'react';
import { Link } from 'react-router-dom';

const HomeContainer = () => (
    <div>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Gerenciador de processos</h1>
                <p class="lead">Seja bem vindo ao Gerenciador de processos do desafio fullstack developer!</p>
                <Link classname="btn btn-primary" to="/login">Acessar o sistema</Link>
            </div>
        </div>
    </div>
)

export default HomeContainer;