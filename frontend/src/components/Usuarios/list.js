import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { ToastsStore } from 'react-toasts';

import UsuariosService from '../../services/usuarios.service';

class UsuariosList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoaded: false
        }
        this.handleRemove = this.handleRemove.bind(this);
    }

    componentDidMount() {
        UsuariosService.findAll()
            .then(response => response.json())
            .then(json => {
                this.setState({
                    items: json.content,
                    isLoaded: true
                })
            }).catch(error => console.log(error));
    }

    handleRemove(e) {
        const { name } = e.target;
        const id = name;
        UsuariosService.remove(id)
            .then(response => {
                if (response.status === 204) {
                    ToastsStore.success('Usuário removido com sucesso!');
                    this.componentDidMount();
                } else {
                    ToastsStore.warning('Não é possível remover o usuário solicitado.');
                }
            }).catch(error => ToastsStore.error(error));
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
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Perfil</th>
                                <th>Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            {items.map(item => (
                                <tr key={item.id}>
                                    <td>{item.nome}</td>
                                    <td>{item.email}</td>
                                    <td className="text-center">
                                        <span className="badge badge-secondary">{item.perfil}</span>
                                    </td>
                                    <td className="text-center">
                                        <Link to={`/usuarios/edit/${item.id}`} className="btn btn-outline-secondary btn-sm">Editar</Link>&nbsp;
                                        <button onClick={this.handleRemove} name={item.id} className="btn btn-outline-danger btn-sm">Remover</button>
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

export default UsuariosList;