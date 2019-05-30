import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { UsuarioService } from '../../services/usuario.service'
import { ApplicationContext } from '../../components/application-context';

class AdminPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            user: ApplicationContext.getUser(),
            items: [],
            isLoaded: false
        }
    }

    componentDidMount() {
        UsuarioService.getAll()
            .then(res => res.json())
            .then(json => {
                this.setState({
                    items: json.content,
                    isLoaded: true
                })
            }).catch(error => console.log(error));
    }

    render() {
        var { user, items, isLoaded } = this.state;

        if (!isLoaded) {
            return <div>Loading...</div>
        } else {
            return(
                <div className="card">
                    <div className="card-header">
                        <strong>Painel do Administrador</strong>
                    </div>
                    <div className="card-body">
                        <table className="table table-sm table-bordered table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Login</th>
                                    <th>E-mail</th>
                                    <th>Perfil</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items.map(item => (
                                    <tr key={item.id}>
                                        <td>{item.nome}</td>
                                        <td>{item.loginAcesso}</td>
                                        <td>{item.email}</td>
                                        <td><span className="badge badge-secondary">{item.perfil}</span></td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    }
    
}

export { AdminPage };