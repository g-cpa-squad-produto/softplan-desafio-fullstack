import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Divider, Button } from '@material-ui/core'

import { UsuarioAPI } from './UsuarioAPI'
import { UsuarioGridComponent } from './UsuariosGridComponent'

const usuarioService = new UsuarioAPI();

export class UsuarioList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            usuarios: []
        };
    }

    componentDidMount() {
        usuarioService.getAllUsuarios().then(data => {
            this.setState({ usuarios: data })
        })
    }

    render() {

        return (
            <div>
                <h2>Administração de Usuários </h2>
                <Divider />
                <UsuarioGridComponent usuarios={this.state.usuarios} />
                <Divider />

                <br />
                <div align='left'>
                    <Button variant="contained" color="primary" component={Link} to='/usuario/novo'>
                        Novo Usuário
                    </Button>
                </div>
            </div>
        )
    }

}