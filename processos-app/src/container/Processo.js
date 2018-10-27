import React, {Component} from 'react';
import { Button } from "react-bootstrap";
import ProcessoList from '../ui/processo/ProcessoList';
import {urlProcessos} from '../ui/webservices/ProcessosAPI';
import {Alert} from '../ui/alert/Alert';
import axios from 'axios';

export default class Processo extends Component {
    
    constructor(props){
        super(props);

        this.state = {
            processos : [],
            erros: []
        };
    }

    componentDidMount(){
        this.selecionarProcessos();
    }

    selecionarProcessos(){
        axios.get(urlProcessos).then(response => {
            this.setState({processos: response.data})
        }).catch((error) => {
            this.setState({processos: []})
        });
    }

    handleCriarProcesso(event){
        event.preventDefault();
        this.props.history.push("/processos/novo");
    }
    
    render(){
        return(
            <div className="container container-component">
                <Alert erros={this.state.erros} />
                <ProcessoList processos={this.state.processos} />
                <Button type="button" bsStyle="success" onClick={this.handleCriarProcesso.bind(this)}>Criar Processo</Button>
            </div>
        );
    }
}