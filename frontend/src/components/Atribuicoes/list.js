import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Moment from 'react-moment';

import UsuariosService from '../../services/usuarios.service';
import ProcessosAtribuidosService from '../../services/processos-atribuidos.service';

class AtribuicoesList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            usuarioLogado: UsuariosService.getLoggedUser(),
            items: [],
            isLoaded: false
        }
    }

    componentDidMount() {
        const { usuarioLogado } = this.state;
        ProcessosAtribuidosService.findByUsuario(usuarioLogado.id)
            .then(response => response.json())
            .then(json => {
                this.setState({
                    items: json.content,
                    isLoaded: true
                })
            }).catch(error => console.log(error));
    }

    render() {
        var { items, isLoaded } = this.state;

        if (!isLoaded) {
            return <h3>Carregando dados...</h3>
        } else {
            return(
                <div>
                <table className="table table-sm table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Processo</th>
                            <th>Súmula</th>
                            <th>Abertura</th>
                            <th>Atribuição</th>
                            <th>Opções</th>
                        </tr>
                    </thead>
                    <tbody>
                    {items.map(item => (
                        <tr key={item.idAtribuicao}>
                            <td>{item.idProcesso}</td>
                            <td>{item.sumula}</td>
                            <td><Moment format="DD/MM/YYYY HH:mm" date={item.dhAbertura} /></td>
                            <td><Moment format="DD/MM/YYYY HH:mm" date={item.dhAtribuicao} /></td>
                            <td className="text-center">
                                <Link to={`/atribuicoes/${item.idAtribuicao}/parecer`} className="btn btn-outline-secondary btn-sm">Informar parecer</Link>&nbsp;
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <nav aria-label="Navegação de página exemplo">
                        <ul className="pagination">
                            <li className="page-item">
                            <a className="page-link" href="/" aria-label="Anterior">
                                <span aria-hidden="true">&laquo;</span>
                                <span className="sr-only">Anterior</span>
                            </a>
                            </li>
                            <li className="page-item"><a className="page-link" href="/">1</a></li>
                            <li className="page-item"><a className="page-link" href="/">2</a></li>
                            <li className="page-item"><a className="page-link" href="/">3</a></li>
                            <li className="page-item">
                            <a className="page-link" href="/" aria-label="Próximo">
                                <span aria-hidden="true">&raquo;</span>
                                <span className="sr-only">Próximo</span>
                            </a>
                            </li>
                        </ul>
                    </nav>                    
                </div>
            );
        }
    }

}

export default AtribuicoesList;