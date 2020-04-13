import React from 'react'

import Card from '../../components/card'
import FormGroup from '../../components/form-group'
import SelectMenu from '../../components/selectMenu'

import { withRouter } from 'react-router-dom'
import * as messages from '../../components/toastr'

import ProcessoService from '../../app/service/processoService'
import LocalStorageService from '../../app/service/localstorageService'

class CadastroProcessos extends React.Component {

    state = {
        id: null,
        parecer: '',
        mes: '',
        ano: '',
        status: '',
        usuario: null,
        atualizando: false
    }

    constructor(){
        super();
        this.service = new ProcessoService();
    }

    componentDidMount(){
        const params = this.props.match.params
       
        if(params.id){
            this.service
                .obterPorId(params.id)
                .then(response => {
                    this.setState( {...response.data, atualizando: true} )
                })
                .catch(erros => {
                    messages.mensagemErro(erros.response.data)
                })
        }
    }

    submit = () => {
        const usuarioLogado = LocalStorageService.obterItem('_usuario_logado')

        const { parecer, mes, ano, } = this.state;
        const processo = { parecer, mes, ano, usuario: usuarioLogado.id };

        try{
            this.service.validar(processo)
        }catch(erro){
            const mensagens = erro.mensagens;
            mensagens.forEach(msg => messages.mensagemErro(msg));
            return false;
        }     

        this.service
            .salvar(processo)
            .then(response => {
                this.props.history.push('/consulta-processos')
                messages.mensagemSucesso('Processo cadastrado com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    atualizar = () => {
        const { parecer, mes, ano, status, usuario, id } = this.state;

        const processo = { parecer, mes, ano, usuario, status, id };
        
        this.service
            .atualizar(processo)
            .then(response => {
                this.props.history.push('/consulta-processos')
                messages.mensagemSucesso('Processo atualizado com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState({ [name] : value })
    }

    render(){
        const meses = this.service.obterListaMeses();

        return (
            <Card title={ this.state.atualizando ? 'Atualização de Processo'  : 'Cadastro de Processo' }>
                <div className="row">
                    <div className="col-md-12">
                        <FormGroup id="inputParecer" label="Descrição: *" >
                            <input id="inputParecer" type="text" 
                                   className="form-control" 
                                   name="parecer"
                                   value={this.state.parecer}
                                   onChange={this.handleChange}  />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <FormGroup id="inputAno" label="Ano: *">
                            <input id="inputAno" 
                                   type="text"
                                   name="ano"
                                   value={this.state.ano}
                                   onChange={this.handleChange} 
                                   className="form-control" />
                        </FormGroup>
                    </div>
                    <div className="col-md-6">
                        <FormGroup id="inputMes" label="Mês: *">
                            <SelectMenu id="inputMes" 
                                        value={this.state.mes}
                                        onChange={this.handleChange}
                                        lista={meses} 
                                        name="mes"
                                        className="form-control" />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    

                    <div className="col-md-12">
                         <FormGroup id="inputStatus" label="Status: ">
                            <input type="text" 
                                   className="form-control" 
                                   name="status"
                                   value={this.state.status}
                                   disabled />
                        </FormGroup>
                    </div>

                   
                </div>
                <div className="row">
                     <div className="col-md-6" >
                        { this.state.atualizando ? 
                            (
                                <button onClick={this.atualizar} 
                                        className="btn btn-success">
                                        <i className="pi pi-refresh"></i> Atualizar
                                </button>
                            ) : (
                                <button onClick={this.submit} 
                                        className="btn btn-success">
                                        <i className="pi pi-save"></i> Salvar
                                </button>
                            )
                        }
                        <button onClick={e => this.props.history.push('/consulta-processos')} 
                                className="btn btn-danger">
                                <i className="pi pi-times"></i>Cancelar
                        </button>
                    </div>
                </div>
            </Card>
        )
    }
}

export default withRouter(CadastroProcessos);