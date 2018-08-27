import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { TextField, Card, CardContent, Input, Typography, Divider, Button, Grid } from '@material-ui/core'

import { UsuarioFormComponent } from './UsuarioFormComponent'

import { UsuarioAPI } from './UsuarioAPI'

const usuarioService = new UsuarioAPI()

export class NovoUsuarioComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nomeUsuario: undefined,
            sobrenomeUsuario: undefined,
            senhaUsuario: undefined
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
                this.props.history.push('/usuarios')
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
                            required
                            onChange={this.onChange('nomeUsuario')}
                            value={this.state.nomeUsuario}
                        />

                        <br />

                        <TextField
                            id='sobrenome'
                            label='Sobrenome do Usuário'
                            margin='normal'
                            required
                            onChange={this.onChange('sobrenomeUsuario')}
                            value={this.state.sobrenomeUsuario}
                        />

                        <br />

                        <TextField
                            id="senha"
                            label="Senha"
                            type="password"
                            required
                            autoComplete="current-password"
                            onChange={this.onChange('senhaUsuario')}
                            value={this.state.senha}
                            margin="normal"
                        />

                        <Divider />

                        <br />

                        <Grid container spacing={8}>
                            <Grid item xs={4}>
                                <Button variant="contained" color="secondary" component={Link} to='/usuarios'>
                                    Voltar
                                </Button>
                            </Grid>
                            <Grid item xs={4}>
                                <Button variant="contained" color="primary" onClick={this.onSave}>
                                    Salvar
                                </Button>
                            </Grid>
                        </Grid>

                    </CardContent>
                </Card>
            </div>
        )
    }
}