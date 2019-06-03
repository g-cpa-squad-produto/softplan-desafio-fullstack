import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { ToastsStore } from 'react-toasts';

import ProcessosService from '../../services/processos.service';
import UsuariosService from '../../services/usuarios.service';
import AtribuicoesService from '../../services/atribuicoes.service';

class ProcessosAtribuicao extends Component {

    constructor(props) {
        super(props);

        const idProcesso = parseInt(props.idProcesso);

        this.state = {
            isProcessoLoaded: false,
            isUsuariosLoaded: false,
            usuarioLogado: UsuariosService.getLoggedUser()
        };

        ProcessosService.findById(idProcesso).then(response => {
            response.json().then(json => {
                this.setState({ processo: json, isProcessoLoaded: true });
            });
        })

        UsuariosService.findAllByPerfil('FINALIZADOR').then(response => {
            response.json().then(json => {
                this.setState({ usuarios: json, usuarioFinalizador: json[0].id, isUsuariosLoaded: true });
            });
        })

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        e.preventDefault();

        const { processo, usuarioLogado, usuarioFinalizador } = this.state;

        const atribuicao = {
            processo: { id: processo.id || null },
            usuarioTriador: { id : usuarioLogado.id },
            usuarioFinalizador: { id: parseInt(usuarioFinalizador) }
        }

        console.log('atribuicao: ', atribuicao);

        AtribuicoesService.save(atribuicao)
            .then(response => {
                if (response.status < 300) {
                    const { from } = { from: { pathname: '/processos' } };
                    this.props.history.push(from);
                } else {
                    response.json()
                        .then(json => json.errors
                            .forEach(function(error) {
                                ToastsStore.warning(error);
                            }));
                }
            })
            .catch(error => {
                console.log(error);
                ToastsStore.error("Erro ao realizar a atribuição.");
            });
    }

    render() {
        const { isProcessoLoaded, processo, isUsuariosLoaded, usuarios, usuarioFinalizador } = this.state;
        if (!isProcessoLoaded || !isUsuariosLoaded) {
            return <h3>Carregando dados...</h3>
        } else {
            return (
                <div>
                    <form name="atribuicoesForm" onSubmit={this.handleSubmit}>
                        <div className="form-row">
                            <div className="form-group col-md-2">
                                <label htmlFor="idProcesso">Processo</label>
                                <input type="text" readOnly className="form-control" id="idProcesso" name="idProcesso" value={processo.id} />
                            </div>
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="sumulaProcesso">Súmula</label>
                                <textarea type="text" rows="5" readOnly className="form-control" id="sumulaProcesso" name="sumulaProcesso" value={processo.sumula} />
                            </div>
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="usuarioFinalizador">Usuário finalizador</label>
                                <select className="form-control" id="usuarioFinalizador" name="usuarioFinalizador" value={usuarioFinalizador} onChange={this.handleChange}>
                                    {usuarios.map(usuario => (
                                        <option key={usuario.id} value={usuario.id}>{usuario.nome}</option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <button type="submit" id="btSalvar" name="btSalvar" className="btn btn-primary">Atribuir</button>&nbsp;
                        <Link to="/processos" className="btn btn-secondary">Cancelar</Link>
                    </form>
                </div>
            );
        }
    }

}

export default ProcessosAtribuicao;