import React from 'react';
import { Link } from 'react-router-dom';

const HomeContainer = () => (
    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
        <div className="jumbotron jumbotron-fluid">
            <div className="container text-center">
                <h1 className="display-4">Gerenciador de processos</h1>
                <p className="lead">Seja bem vindo ao Gerenciador de processos do desafio fullstack developer!</p>
                <Link className="btn btn-primary btn-lg" to="/login">Acessar o sistema</Link>
            </div>
        </div>
    </div>
)

export default HomeContainer;