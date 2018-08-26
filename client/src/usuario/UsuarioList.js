import React, { Component } from 'react'
import { Divider } from '@material-ui/core'

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
            this.state.usuarios = data
        })
    }

    render() {

        return (
            <div>
                <h2>Usuario List </h2>
                <Divider />
                <UsuarioGridComponent usuarios={this.state.usuarios} />
            </div>
        )
    }

}