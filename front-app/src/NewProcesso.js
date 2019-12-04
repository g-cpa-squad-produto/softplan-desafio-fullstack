import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Box from '@material-ui/core/Box';


import api from './Api'


class NewProcesso extends Component {

    constructor(props) {
        super(props);
        this.state = {
            codigo: '',
            descricao: '',
            message: null,
            redirect: false
        }
        this.saveProcesso = this.saveProcesso.bind(this);
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    saveProcesso = (e) => {
        e.preventDefault();
        let processo = {
            codigo: this.state.codigo,
            descricao: this.state.descricao
        };
        console.log('processo' + processo.codigo)


        api.saveProcesso(processo).then(
            (res) => {
                this.setState({
                    message: 'Processo criado com sucesso!',
                    redirect: '/processos'
                })
            }

        )
    }

    render() {
        return (
            <div>
                {this.state.redirect &&
                    <Redirect to={this.state.redirect} />
                }
                <Typography variant="h4" style={style}>Criação de processo</Typography>
                <form>
                    <TextField placeholder="Código do processo"
                        fullWidth
                        margin="normal"
                        name="codigo"
                        value={this.state.codigo}
                        onChange={this.onChange} />

                    <TextField placeholder="Descrição"
                        fullWidth
                        margin="normal"
                        name="descricao"
                        value={this.state.descricao}
                        onChange={this.onChange} />
                    <Box display="flex" justifyContent="center" m={1} p={1} bgcolor="background.paper">
                        <Button justifyContent="center" style={{ width: '70%' }} variant="contained" color="primary" onClick={this.saveProcesso}>Salvar</Button>
                    </Box>
                </form>
            </div>
        );
    }
}

const style = {
    display: 'flex',
    justifyContent: 'center'
}

export default NewProcesso;