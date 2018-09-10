import React, {Component} from 'react';
import {
    FormControl,
    Input,
    InputLabel,
    Paper,
    Typography,
    TextField,
    MenuItem,
    withStyles,
    Button
} from '@material-ui/core';
import {styles} from '../../dashboard/styles';
import * as userActions from '../UserActions';

class UserForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            usuario: {
                nome: '',
                username: '',
                password: '',
                role: ''
            },
            roles: [],
        };
    }

    componentWillMount() {
        userActions.findAll('/usuarios/roles', res => {
            this.setState({roles: res.data});
        }, error => {
            this.props.notify(error.message);
        });
    }

    handlerChange = (e) => {
        if (e.target.name === 'roles') {
            let rolesValue = this.state.roles.find(r => r.id === e.target.value);
            this.setState({
                usuario: {...this.state.usuario, role: rolesValue.id}
            });
        } else {
            this.setState({usuario: {...this.state.usuario, [e.target.name]: e.target.value}});
        }
    };

    onSubmit = (e) => {
        e.preventDefault();

        userActions.create(this.state.usuario, res => {
            this.props.notify('Usuário cadastrado com sucesso!');
            this.props.history.push('/usuarios');
        }, error => {
            this.props.notify(error.message);
        });
    };

    render() {
        const {classes} = this.props;

        return (
            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <Paper elevation={1} className={classes.root + ' ' + classes.paddingMd}>
                    <Typography variant="headline" gutterBottom>
                        Cadastrar usuário
                    </Typography>

                    <form onSubmit={this.onSubmit}>
                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <InputLabel htmlFor="nome">Nome</InputLabel>
                            <Input id="nome" name="nome" autoFocus
                                   value={this.state.usuario.nome}
                                   onChange={this.handlerChange}/>
                        </FormControl>

                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <InputLabel htmlFor="username">Username</InputLabel>
                            <Input id="username" name="username"
                                   value={this.state.usuario.username}
                                   onChange={this.handlerChange}/>
                        </FormControl>

                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <InputLabel htmlFor="password">Password</InputLabel>
                            <Input id="password" name="password" type="password"
                                   value={this.state.usuario.password}
                                   onChange={this.handlerChange}/>
                        </FormControl>

                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <TextField id="select-roles" select label="Permissões"
                                       value={this.state.usuario.role} onChange={this.handlerChange} name="roles"
                                       SelectProps={{
                                           MenuProps: {
                                               className: classes.menu,
                                           },
                                       }} required>
                                {this.state.roles.map(r => (
                                    <MenuItem key={r.nome} value={r.id}>
                                        {r.nome}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </FormControl>

                        <Button type="submit" variant="raised" color="primary">Enviar</Button>
                    </form>
                </Paper>
            </main>
        );
    }
}

export default withStyles(styles)(UserForm);