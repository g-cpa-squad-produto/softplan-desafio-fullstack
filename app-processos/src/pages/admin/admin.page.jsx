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
            });
    }

    render() {
        var { user, items, isLoaded } = this.state;

        if (!isLoaded) {
            return <div>Loading...</div>
        } else {
            return(
                <div className="AdminPage">
                    <strong>Usuário Logado: {user.nome} </strong>
                    ( <Link to="/login">Logout</Link> )

                    <h2>Painel do Administrador</h2>
                    <table className="table table-sm table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Login</th>
                                <th>Perfil</th>
                            </tr>
                        </thead>
                        <tbody>
                        {items.map(item => (
                            <tr key={item.id}>
                                <td>
                                    {item.nome}
                                </td>
                                <td>
                                    {item.email}
                                </td>
                                <td>
                                    {item.loginAcesso}
                                </td>
                                <td>
                                    <span className="badge badge-secondary">{item.perfil}</span>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            );
        }
    }
    
}

export { AdminPage };