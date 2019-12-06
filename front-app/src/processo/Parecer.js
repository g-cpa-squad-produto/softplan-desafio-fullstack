import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Box from '@material-ui/core/Box';
import { Link } from 'react-router-dom'


import api from './../Api'


class Parecer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            processo: {},
            texto: '',
            errors: {}

        }
        this.saveParecer = this.saveParecer.bind(this);
    }

    handleValidation() {
        let errors = {};
        let formIsValid = true;

        if (!this.state.texto) {
            formIsValid = false;
            errors["texto"] = "Não pode ser vazio";
        }

        this.setState({ errors: errors });
        return formIsValid;
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });


    componentDidMount() {
        api.loadProcessoById(this.props.match.params.idProcesso)
            .then((res) => {
                this.setState({ processo: res.data })
            })

    }

    saveParecer = (e) => {
        e.preventDefault();
        if (this.handleValidation()) {
            let parecer = {
                texto: this.state.texto,
                idProcesso: this.state.processo.id,
                idUsuario: localStorage.getItem('gerenciador-processo-online/idUsuario')
            };
            api.saveParecer(parecer).then(
                (res) => {
                    this.setState({
                        message: 'Parecer criado com sucesso!',
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
                <Typography variant="h4" style={style}>Crie seu parecer</Typography>
                <form>
                    <div>
                        <TextField
                            id="filled-name"
                            label="Código do processo"
                            value={this.state.processo.codigo}
                            defaultValue="Buscando o código..."
                            margin="normal"
                            variant="filled"
                        />
                    </div>
                    {this.state.processo.parecer === null &&
                        <div>
                                                {console.log('parecer'+this.state.processo.parecer)}

                            <TextField
                                id="outlined-multiline-static"
                                value={this.state.texto}
                                onChange={this.onChange}
                                name="texto"
                                label="Digite aqui o texto do parecer"
                                multiline
                                rows="4"
                                style={{ width: '100%' }}
                                margin="normal"
                                variant="outlined"
                            />
                            <span style={{ color: "red" }}>{this.state.errors["texto"]}</span>
                            <br />
                            <Box display="flex" justifyContent="center" m={1} p={1} bgcolor="background.paper">
                            <Button justifyContent="center" style={{ width: '70%' }} variant="contained" color="primary" onClick={this.saveParecer}>Salvar</Button>
                        </Box>
                        </div>
                    }

                    {this.state.processo.parecer !== null && 
                    this.state.processo.parecer !== undefined &&
                        <div>
                            <TextField
                                id="filled-parecer"
                                label="Texto do parecer"
                                value={this.state.processo.parecer.texto}
                                defaultValue="Buscando o texto..."
                                margin="normal"
                                variant="filled"
                            />
                            <br />
                            { this.state.processo.parecer.usuario !== null &&
                            <TextField
                            id="filled-usuario"
                            label="Usuário que realizou o parecer"
                            value={this.state.processo.parecer.usuario.email}
                            defaultValue="Buscando o nome do usuário do parecer..."
                            margin="normal"
                            variant="filled"
                        />
                            }
                            { this.state.processo.parecer.usuario === null &&
                            <TextField
                            id="filled-usuario"
                            label="Usuário que realizou o parecer"
                            value={'O usuário foi removido'}
                            defaultValue="Buscando o nome do usuário do parecer..."
                            margin="normal"
                            variant="filled"
                        />
                            }
                            
                            <Box display="flex" justifyContent="center" m={1} p={1} bgcolor="background.paper">
                            <Link className="btn btn-success" to={'/processos'}>Voltar para processos</Link>
                        </Box>
                        </div>
                    }

                </form>
            </div>
        );
    }
}

const style = {
    display: 'flex',
    justifyContent: 'center'
}

export default Parecer;