import React, {Component} from 'react'
import api from '../../services/api'
import {Link} from 'react-router-dom'

import './style.css';

export default class UsuariosList extends Component {
    state = {
        usuarios: []
    }

    componentDidMount() {
        this.loadUsuarios();
    }

    loadUsuarios = async () => {
        const response = await api.get('/usuarios');
        this.setState({ usuarios: response.data});
    }

    render() {
        const {usuarios} = this.state
        return (
            <div className="usuarios-list" >
                <a href="/usuario" className="btn-cadastrar">Cadastrar</a>
                {usuarios.map(usuarios => (
                    <article key={usuarios.usuarioId}>
                        {/* <a href="" className="btn-excluir">X</a> */}
                        <strong>{usuarios.nome}</strong>
                        <p>{usuarios.email}</p>
                        <Link to={`/usuario/${usuarios.usuarioId}`}>Editar</Link>
                    </article>
                ))}
            </div>
        )
    }
}