import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { Jumbotron, Container } from 'reactstrap';
import '../App.css';

class Home extends Component {
    render() {
        return (
            <Container>
                <Jumbotron fluid>
                    <Container fluid>
                        <h1 className="display-5">Desafio implementador fullstack</h1>
                        <p className="lead">Este é o desenvolvimento da aplicação para o desafio da vaga de implementador fullstack na Softplan.
                            Aqui são testadas as habilidades e qualidade de código ao transformar requisitos limitados em uma aplicação web.</p>
                    </Container>
                </Jumbotron>
            </Container>
        );
    }
}

export default withRouter(Home);
