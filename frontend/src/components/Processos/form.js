import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { ToastsStore } from 'react-toasts';

import UsuariosService from '../../services/usuarios.service';
import ProcessosService from '../../services/processos.service';

class ProcessosForm extends Component {

    constructor(props) {
        super(props);

        this.state = {
            sumula: '',
            usuarioAbertura: UsuariosService.getLoggedUser()
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        e.preventDefault();

        const { sumula, usuarioAbertura } = this.state;

        const processo = {
            sumula: sumula || null,
            usuarioAbertura: { id: usuarioAbertura.id }
        }

        console.log('processo: ', processo);

        ProcessosService.save(processo)
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
                ToastsStore.warning("Falha ao salvar o processo.");
            });
    }

    render() {
        const { sumula, usuarioAbertura } = this.state;
        return (
            <div>
                <form name="processosForm" onSubmit={this.handleSubmit}>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="nome">Usuário de abertura</label>
                            <input type="text" readOnly className="form-control" id="nomeUsuarioAbertura" name="nomeUsuarioAbertura" value={usuarioAbertura.nome} />
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="senha">Súmula</label>
                            <textarea type="text" rows="5" className="form-control" id="sumula" name="sumula" value={sumula} onChange={this.handleChange} placeholder="Informe a súmula do processo.." />
                        </div>
                    </div>
                    <button type="submit" id="btSalvar" name="btSalvar" className="btn btn-primary">Salvar</button>&nbsp;
                    <Link to="/processos" className="btn btn-secondary">Cancelar</Link>
                </form>
            </div>
        );
    }

}

export default ProcessosForm;