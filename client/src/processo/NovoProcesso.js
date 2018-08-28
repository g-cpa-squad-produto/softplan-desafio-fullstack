import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Divider, Card, CardContent, Typography, TextField, Button, Grid, InputLabel, Select, Chip, MenuItem, Input, ListItemText, Checkbox } from '@material-ui/core'

import { UsuarioAPI } from '../usuario/UsuarioAPI';
import { ProcessoAPI } from './ProcessoAPI';

const usuarioAPI = new UsuarioAPI()
const processoAPI = new ProcessoAPI()

export class NovoProcesso extends Component {

    constructor(props) {
        super(props)
        this.state = {
            descricaoProcesso: undefined,
            processosUsuario: [{
                processo: undefined,
                usuario: undefined
            }],
            usuariosSelecionados: [],
            usuariosDisponiveis: []
        }

        this.onSave = this.onSave.bind(this)
    }

    onChange = field => event => {
        this.setState({
            [field]: event.target.value
        })
    }

    componentDidMount() {
        usuarioAPI.getAllUsuariosAtivos()
            .then(data => this.setState({ usuariosDisponiveis: data }))
    }

    onSave() {
        let processosUsuario = []
        this.state.usuariosSelecionados.forEach(u => {
            let processoUsuario = {
                usuario: u
            }
            processosUsuario.push(processoUsuario)
        })
        let processo = {
            descricaoProcesso: this.state.descricaoProcesso,
            processosUsuario: processosUsuario
        }
        processoAPI.saveNovoProcesso(processo)
            .then(data => {
                this.props.history.push('/processos')
            })
    }

    render() {
        return (
            <div>
                <h2>Cadastrar Novo Processo </h2>
                <Divider />
                <Card>
                    <CardContent>
                        <Typography>
                            Novo Processo
                        </Typography>

                        <TextField
                            id='descricaoProcesso'
                            label='Descrição do Processo'
                            margin='normal'
                            required
                            onChange={this.onChange('descricaoProcesso')}
                            value={this.state.descricaoProcesso}
                        />

                        <br />

                        <InputLabel htmlFor="select-multiple-chip">Usuarios</InputLabel>
                        <br />
                        <Select
                            multiple
                            value={this.state.usuariosSelecionados}
                            onChange={this.onChange('usuariosSelecionados')}
                            input={<Input id="select-multiple-chip" />}
                            renderValue={selected => selected.map(u => u.nomeUsuario).join(', ')}>

                            {this.state.usuariosDisponiveis.map(usuario => (
                                <MenuItem
                                    key={usuario.isnUsuario}
                                    value={usuario}>

                                    <Checkbox checked={this.state.usuariosSelecionados.indexOf(usuario) > -1} />
                                    <ListItemText primary={usuario.nomeUsuario} />
                                </MenuItem>
                            ))}

                        </Select>

                        <Divider />

                        <br />

                        <Grid container spacing={8}>
                            <Grid item xs={4}>
                                <Button variant="contained" color="secondary" component={Link} to='/processos'>
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
            </div >
        )
    }

}