import React, { Component } from "react";
import { FormGroup, FormControl, ControlLabel, Button, ButtonToolbar } from "react-bootstrap";
import {urlProcessos} from '../ui/webservices/ProcessosAPI';
import {Alert} from '../ui/alert/Alert';
import axios from 'axios';

export default class ProcessoForm extends Component {

    constructor(props){
        super(props);

        this.state= {
            erros: [],
            codigo: null,
            titulo: "",
            descricao: "",
            permissao:[]
        }
    }

    componentDidMount(){
        const { codigo } = this.props.match.params

        if(codigo != null) {
            axios.get(urlProcessos+"/"+codigo).then(response => {
                const processo = response.data;
                this.setState({
                    codigo: processo.codigo,
                    titulo: processo.titulo,
                    descricao: processo.descricao
                });
            }).catch((error) => {
                this.props.history.push("/processos");
            });
        }
    };

    validateForm() {
        return this.state.titulo.length > 0 
        && this.state.titulo.length <= 50
        && this.state.descricao.length > 0 
        && this.state.descricao.length <= 500;
    };

    handleChange = event => {
        event.preventDefault();
        this.setState({
          [event.target.name]: event.target.value
        });
    };

    handleSubmit(event) {
        event.preventDefault();

        const processo = {...this.state};

        axios.post(urlProcessos, processo).then(response => {
            this.props.history.push("/processos");
        }).catch((error, xhr) => {
            if(error.response != null && error.response.data.erros != null){
                this.setState({
                    erros : error.response.data.erros
                });
            } else {
                this.setState({
                    erros : [{mensagem: 'Erro Inesperado'}]
                });
            }
        });
    };

    handleChange(event){
        event.preventDefault(event);
    };

    handleCancel(event) {
        event.preventDefault();
        this.props.history.push("/processos");
    };

    render(){
        return(
            <div className="NovoProcesso container">
                <Alert erros={this.state.erros} />
                <form id="processoForm" onSubmit={this.handleSubmit.bind(this)}>
                    <FormGroup>
                        <ControlLabel>Título</ControlLabel>
                        <FormControl 
                            onChange={this.handleChange}
                            type="text"
                            name="titulo"
                            value={this.state.titulo} 
                            readOnly={this.state.codigo != null ? true : false} 
                            placeholder="Digite o título"/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Descrição</ControlLabel>
                        <FormControl
                            componentClass="textarea"
                            onChange={this.handleChange}
                            name="descricao"
                            value={this.state.descricao} 
                            readOnly={this.state.codigo != null ? true : false} 
                            placeholder="Digite a descrição"/>
                    </FormGroup>
                    <ButtonToolbar>
                        {this.state.codigo == null 
                            ? <Button type="submit" bsStyle="success" disabled={!this.validateForm()}>{this.state.codigo != null ? 'Atualizar' : 'Criar'} Processo</Button>
                            : ''
                        }
                        <Button type="button" bsStyle="default" onClick={this.handleCancel.bind(this)}>Voltar</Button>
                    </ButtonToolbar>
                </form>
            </div>
        )
    };
}