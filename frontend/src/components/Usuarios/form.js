import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { ToastsStore } from 'react-toasts';

import UsuariosService from '../../services/usuarios.service';

class UsuariosForm extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: props.id || null,
            nome: '',
            email: '',
            senha: '',
            confirmacaoSenha: '',
            perfil: 'ADMINISTRADOR'
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

        const { id, nome, email, senha, confirmacaoSenha, perfil } = this.state;

        if (senha && senha !== confirmacaoSenha) {
            ToastsStore.warning("A senha e a confirmação estão divergentes.");
            return;
        }

        const usuario = {
            id: id,
            nome: nome || null,
            email: email || null,
            senha: senha || null,
            perfil: perfil || null
        }

        UsuariosService.save(id, usuario)
            .then(response => {
                if (response.status < 300) {
                    const { from } = { from: { pathname: '/usuarios' } };
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
                ToastsStore.warning("Falha ao salvar o usuário.");
            });
    }

    render() {
        const { nome, email, senha, confirmacaoSenha, perfil } = this.state;
        return (
            <div>
                <form name="usuariosForm" onSubmit={this.handleSubmit}>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="nome">Nome</label>
                            <input type="text" className="form-control" id="nome" name="nome" value={nome} onChange={this.handleChange} placeholder="Informe o nome.." />
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="email">Email</label>
                            <input type="email" className="form-control" id="email" name="email" value={email} onChange={this.handleChange} placeholder="Informe o email.." />
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="senha">Senha</label>
                            <input type="password" className="form-control" id="senha" name="senha" value={senha} onChange={this.handleChange} placeholder="Informe a senha.." />
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="confirmacaoSenha">Confirmação de senha</label>
                            <input type="password" className="form-control" id="confirmacaoSenha" name="confirmacaoSenha" value={confirmacaoSenha} onChange={this.handleChange} placeholder="Informe novamente a senha.." />
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="perfil">Perfil</label>
                            <select className="form-control" id="perfil" name="perfil" value={perfil} onChange={this.handleChange}>
                                <option value="ADMINISTRADOR">Administrador</option>
                                <option value="TRIADOR">Triador</option>
                                <option value="FINALIZADOR">Finalizador</option>
                            </select>
                        </div>
                    </div>
                    <button type="submit" id="btSalvar" name="btSalvar" className="btn btn-primary">Salvar</button>&nbsp;
                    <Link to="/usuarios" className="btn btn-secondary">Cancelar</Link>
                </form>
            </div>
        );
    }
}

export default UsuariosForm;