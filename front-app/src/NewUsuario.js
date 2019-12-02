import React, { Component } from 'react'
import TextField from '@material-ui/core/TextField';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';

import { makeStyles } from '@material-ui/core/styles';
import Select from '@material-ui/core/Select';

import api from './Api'
import { Redirect } from 'react-router-dom'
import FormControl from '@material-ui/core/FormControl';


const tipo = {
    'administrador': 'Administrador',
    'triador': 'Triador',
    'finalizador': 'Finalizador'
}


const useStyles = makeStyles(theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 500,
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
      }, 
      selectEmpty: {
        marginTop: theme.spacing(2),
      },
      button: {
        margin: theme.spacing(1),
      }
}));



const Form = () => {
    const classes = useStyles();
    const inputLabel = React.useRef('');
    const [tipo, setTipo] = React.useState(null);
    const [nome, setNome] = React.useState(null);
    const [email, setEmail] = React.useState(null);
    const [senha, setSenha] = React.useState(null);

    const handleChange = event => {
        setTipo(event.target.value);
    };
    const handleChangeNome = event => {
        setNome(event.target.value);
    };
    const handleChangeEmail = event => {
        setEmail(event.target.value);
    };
    const handleChangeSenha = event => {
        setSenha(event.target.value);
    };
    let nomeRef = React.createRef();
    let emailRef = React.createRef();
    let senhaRef = React.createRef();

   const saveUsuario = event =>{
    console.log(nomeRef);

        const newUsuario = {
            nome: nome,
            email: email,
            senha: senha,
            tipo: tipo
        }
        console.log(newUsuario)
        
        api.saveUsuario(newUsuario).then(
            (res) => {
                this.setState({
                    redirect: '/Usuario/' + this.refs.genre.value
                })
            }

        )
        
    }

    return <form className={classes.container} noValidate autoComplete="off">
        <TextField
            id="standard-nome"
            label="Nome"
            value={nome}
            onChange={handleChangeNome}
            inputRef={nomeRef}
            className={classes.textField}
            margin="normal"
        />
        <TextField
            id="standard-email"
            label="Email"
            value={email}
            onChange={handleChangeEmail}
            inputRef={emailRef}
            className={classes.textField}
            margin="normal"
        />
        <TextField
            id="standard-password-input"
            label="Senha"
            value={senha}
            onChange={handleChangeSenha}
            inputRef={senhaRef}
            className={classes.textField}
            type="password"
            autoComplete="current-password"
            margin="normal"
        />
        <FormControl className={classes.formControl}>
            <InputLabel id="demo-simple-select-label">Tipo do usuário</InputLabel>
            <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={tipo}
                onChange={handleChange}
            >
                <MenuItem value={'administrador'}>Administrador</MenuItem>
                <MenuItem value={'triador'}>Triador</MenuItem>
                <MenuItem value={'finalizador'}>Finalizador</MenuItem>
            </Select>
        </FormControl>
        <Button
        variant="contained"
        color="primary"
        size="small"
        type="button" onClick={saveUsuario}
        className={classes.button}
        startIcon={<SaveIcon />}
      >
        Save
      </Button>
    </form>

}

class NewUsuario extends Component {

    constructor(props) {
        super(props)

        this.state = {
            isLoading: false,
            redirect: false
        }
        this.saveUsuario = this.saveUsuario.bind(this)
    }

    componentDidMount() {
        this.setState({ isLoading: true })
    }

    saveUsuario() {
        const newUsuario = {
            id: this.props.match.params.id,
            nome: this.refs.nome.value,
            email: this.refs.email.value,
            senha: this.refs.senha.value,
            tipo: this.refs.tipo.value
        }
        api.saveUsuario(newUsuario).then(
            (res) => {
                this.setState({
                    redirect: '/Usuario/' + this.refs.genre.value
                })
            }

        )
    }

    render() {
        return (
            <section id="intro" className="intro-section">
                {this.state.redirect &&
                    <Redirect to={this.state.redirect} />
                }
                <h1>Nova série </h1>
                <div>
                    <Form />
                </div>
            </section>
        )
    }

}

export default NewUsuario