import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Box from '@material-ui/core/Box';


import api from './Api'


class Parecer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            processo: {},
            texto: ''
        }
        this.saveParecer = this.saveParecer.bind(this);
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
        let parecer = { texto: this.state.texto };
        api.saveParecer(parecer).then(
            (res) => {
                this.setState({
                    message: 'Parecer criado com sucesso!',
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
                    <Box display="flex" justifyContent="center" m={1} p={1} bgcolor="background.paper">
                        <Button justifyContent="center" style={{ width: '70%' }} variant="contained" color="primary" onClick={this.saveParecer}>Salvar</Button>
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

export default Parecer;