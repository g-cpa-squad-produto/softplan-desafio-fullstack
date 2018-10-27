import React, {Component} from 'react';
import {Logo} from '../App';

export default class Home extends Component {
    render(){
        return (
            <div className="Home">
                <div className="lander">
                    <h1>{Logo}</h1>
                    <p>
                        O {Logo} é uma ferramenta desenvolvida com o intuito de demonstrar
                        uma aplicação <strong>React</strong>, utilizando uma API Rest desenvolvida em Java.
                        <br/> 
                        Permite o <strong>cadastro de usuários</strong> e um fluxo para <strong>criação de processos</strong>,
                        onde poderão ser adicionadas informações adicionais a eles.
                    </p>
                </div>
            </div>
        );
    }
}