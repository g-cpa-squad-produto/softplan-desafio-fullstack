import React, { Component } from 'react'

import { TextField, Card, CardContent, Input, Typography } from '@material-ui/core'

export class UsuarioFormComponent extends Component {

    constructor(props) {
        super(props);
        this.onChange = this.onChange.bind(this)
    }

    onChange = field => event => {
        this.setState({
            [this.props.usuario.nomeUsuario]: event.target.value,
            [this.props.usuario.sobrenomeUsuario]: event.target.value
        })
    }

    render() {
        return (
            <div>

            </div>
        )
    }

}