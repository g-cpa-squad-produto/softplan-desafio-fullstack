import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Box from '@material-ui/core/Box';


import api from './../Api'


class NewProcesso extends Component {

    constructor(props) {
        super(props);
        this.state = {
            codigo: '',
            descricao: '',
            message: null,
            redirect: false,
            errors: {}
        }
        this.saveProcesso = this.saveProcesso.bind(this);
    }


    handleValidation() {
        let errors = {};
        let formIsValid = true;

        //Codigo
        if (!this.state.codigo) {
            formIsValid = false;
            errors["codigo"] = "Não pode ser vazio";
        }
        if (!this.state.descricao) {
            formIsValid = false;
            errors["descricao"] = "Não pode ser vazio";
        }

        this.setState({ errors: errors });
        return formIsValid;
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    saveProcesso = (e) => {
        e.preventDefault();
        if (this.handleValidation()) {
            let processo = {
                codigo: this.state.codigo,
                descricao: this.state.descricao
            };
            api.saveProcesso(processo).then(
                (res) => {
                    this.setState({
                        message: 'Processo criado com sucesso!',
                        redirect: '/processos'
                    })
                }

            )
        }

    }

    render() {
        return (
            <div>
                {this.state.redirect &&
                    <Redirect to={this.state.redirect} />
                }
                <Typography variant="h4" style={style}>Criação de processo</Typography>
                <form noValidate>
                    <TextField placeholder="Código do processo"
                        required={true}
                        fullWidth
                        margin="normal"
                        name="codigo"
                        type="number"
                        value={this.state.codigo}
                        onChange={this.onChange} />
                    <span style={{ color: "red" }}>{this.state.errors["codigo"]}</span>
                    <br />
                    <TextField placeholder="Descrição"
                        required
                        fullWidth
                        margin="normal"
                        name="descricao"
                        value={this.state.descricao}
                        onChange={this.onChange} />
                    <span style={{ color: "red" }}>{this.state.errors["descricao"]}</span>
                    <br />
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