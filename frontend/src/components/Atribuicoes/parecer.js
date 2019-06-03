import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { ToastsStore } from 'react-toasts';

import UsuariosService from '../../services/usuarios.service';
import AtribuicoesService from '../../services/atribuicoes.service';
import PareceresService from '../../services/pareceres.service';

class AtribuicoesParecer extends Component {

    constructor(props) {
        super(props);

        const idAtribuicao = parseInt(props.idAtribuicao);

        this.state = {
            isLoaded: false,
            idAtribuicao: idAtribuicao,
            usuarioLogado: UsuariosService.getLoggedUser(),
            textoParecer: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        const { idAtribuicao } = this.state;

        AtribuicoesService.findById(idAtribuicao).then(response => {
            response.json().then(json => {
                this.setState({ atribuicao: json, isLoaded: true });
            })
        });
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        e.preventDefault();

        const { atribuicao, usuarioLogado, textoParecer } = this.state;

        const parecer = {
            processo: { id: atribuicao.processo.id || null },
            atribuicao: { id: atribuicao.id || null },
            usuarioParecer: { id : usuarioLogado.id },
            textoParecer: textoParecer || null
        }

        console.log('parecer: ', parecer);

        PareceresService.save(parecer)
            .then(response => {
                if (response.status < 300) {
                    const { from } = { from: { pathname: '/atribuicoes' } };
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
                ToastsStore.error("Erro ao registrar o parecer.");
            });
    }

    render() {
        const { isLoaded, atribuicao, textoParecer } = this.state;
        if (!isLoaded) {
            return <h3>Carregando dados...</h3>
        } else {
            return (
                <div>
                    <form name="pareceresForm" onSubmit={this.handleSubmit}>
                        <div className="form-row">
                            <div className="form-group col-md-2">
                                <label htmlFor="idProcesso">Processo</label>
                                <input type="text" readOnly className="form-control" id="idProcesso" name="idProcesso" value={atribuicao.processo.id} />
                            </div>
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="sumulaProcesso">Súmula</label>
                                <textarea type="text" rows="5" readOnly className="form-control" id="sumulaProcesso" name="sumulaProcesso" value={atribuicao.processo.sumula} />
                            </div>
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="textoParecer">Parecer</label>
                                <textarea type="text" rows="5" className="form-control" id="textoParecer" name="textoParecer" value={textoParecer} onChange={this.handleChange} placeholder="Informe o parecer.." />
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

export default AtribuicoesParecer;