import React, {Component} from 'react';
import { PageHeader, ButtonToolbar } from "react-bootstrap";
import { Link, withRouter } from "react-router-dom";

class ProcessoList extends Component {

    handleVisualizarProcesso(codigo) {
        this.props.history.push("/processos/"+codigo);
    }

    listarProcessos() {
        // Se a lista contem elementos, cria as linhas na tabela
        if(this.props.processos.length > 0) {
            return this.props.processos.map(processo => {
                return(
                    <tr key={processo.codigo}>
                        <td>{processo.codigo}</td>
                        <td><Link to={"/processos/"+processo.codigo}>{processo.titulo}</Link></td>
                        <td>{processo.dataCriacao}</td>
                        <td>
                            <ButtonToolbar>
                                <button type="button" className="btn btn-sm btn-success" onClick={() => {this.handleVisualizarProcesso(processo.codigo)}}>Visualizar</button>
                            </ButtonToolbar>
                        </td>
                    </tr>
                )
            });
        } else {
            // Se não mostra que não existem processos
            return (
                <tr>
                    <td colSpan='4'>Não Existem Processos</td>
                </tr>
            );
        }
    }

    render(){
        const listaProcessos = this.listarProcessos();
        return(
            <div>
                <PageHeader>Processos</PageHeader>
                <table className="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Código</th>
                            <th scope="col">Título do Processo</th>
                            <th scope="col">Data de Criação</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {listaProcessos}
                    </tbody>
                </table>
            </div>
        );
    };
}

export default withRouter(ProcessoList);