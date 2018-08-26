import React, { Component } from 'react'

import { TextField, Card, CardContent, Input, Typography, Divider, Button } from '@material-ui/core'

import { UsuarioFormComponent } from './UsuarioFormComponent'

import { UsuarioAPI } from './UsuarioAPI'

const usuarioService = new UsuarioAPI()

export class NovoUsuarioComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nomeUsuario: '',
            sobrenomeUsuario: '',
            senhaUsuario: ''
        }
        this.onSave = this.onSave.bind(this);
    }

    onChange = field => event => {
        this.setState({
            [field]: event.target.value
        })
    }

    onSave(event) {
        event.preventDefault();
        let usuario = {
            nomeUsuario: this.state.nomeUsuario,
            sobrenomeUsuario: this.state.sobrenomeUsuario,
            senhaUsuario: this.state.senhaUsuario
        }

        console.log(usuario)
        usuarioService.saveNovoUsuario(usuario)
            .then(data => {
                console.log(data)
            })
    }


    render() {
        return (
            <div>
                <div align='center'>
                    <h2>Cadastrar Novo Usuário </h2>
                </div>
                <Divider />
                {/* <UsuarioFormComponent usuario={this.state.usuario} /> */}
                <Card>
                    <CardContent>
                        <Typography>
                            Novo Usuário
                        </Typography>

                        <TextField
                            id='nome'
                            label='Nome do Usuário'
                            margin='normal'
                            onChange={this.onChange('nomeUsuario')}
                            value={this.state.nomeUsuario}
                        />

                        <br />

                        <TextField
                            id='sobrenome'
                            label='Sobrenome do Usuário'
                            margin='normal'
                            onChange={this.onChange('sobrenomeUsuario')}
                            value={this.state.sobrenomeUsuario}
                        />

                        <br />

                        <TextField
                            id="senha"
                            label="Senha"
                            type="password"
                            autoComplete="current-password"
                            onChange={this.onChange('senhaUsuario')}
                            value={this.state.senha}
                            margin="normal"
                        />

                        <Divider />

                        <br />

                        <Button variant="contained" color="primary" onClick={this.onSave}>
                            Salvar
                        </Button>

                    </CardContent>
                </Card>
            </div>
        )
    }
}