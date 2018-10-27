import React, {Component} from 'react';
import { PageHeader, ButtonToolbar } from "react-bootstrap";
import { Link, withRouter } from "react-router-dom";

class UsuarioList extends Component {

    handleExcluirUsuario(codigo){
        // Remove o usuário
        this.props.excluirUsuario(codigo);
    }

    handleVisualizarUsuario(codigo) {
        this.props.history.push("/usuarios/"+codigo);
    }

    listarUsuarios() {
        // Se a lista contem elementos, cria as linhas na tabela
        if(this.props.usuarios.length > 0) {
            return this.props.usuarios.map(usuario => {
                return(
                    <tr key={usuario.codigo}>
                        <td>{usuario.codigo}</td>
                        <td><Link to={"/usuarios/"+usuario.codigo}>{usuario.email}</Link></td>
                        <td>{usuario.nome}</td>
                        <td>
                            <ButtonToolbar>
                                <button type="button" className="btn btn-sm btn-success" onClick={() => {this.handleVisualizarUsuario(usuario.codigo)}}>Visualizar</button>
                                {!usuario.administrador 
                                    ? <button type="button" className="btn btn-sm btn-danger" onClick={() => {this.handleExcluirUsuario(usuario.codigo)}}>Remover</button>
                                    : '' 
                                }
                            </ButtonToolbar>
                        </td>
                    </tr>
                )
            });
        } else {
            // Se não mostra que não existir usuários
            return (
                <tr>
                    <td colSpan='4'>Nenhum Usuário Cadastrado</td>
                </tr>
            );
        }
    }

    render(){
        const listaUsuarios = this.listarUsuarios();
        return(
            <div className="usuarios">
                <PageHeader>Usuários</PageHeader>
                <table className="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Código</th>
                            <th scope="col">E-mail</th>
                            <th scope="col">Nome Completo</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {listaUsuarios}
                    </tbody>
                </table>
            </div>
        );
    };
}

export default withRouter(UsuarioList);