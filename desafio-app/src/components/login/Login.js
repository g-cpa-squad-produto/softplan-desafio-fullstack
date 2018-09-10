import React, {Component} from 'react';
import {Paper, Avatar, Typography, FormControl, InputLabel, Input, Button, withStyles} from '@material-ui/core';
import LockIcon from '@material-ui/icons/LockOutlined';
import {styles} from './styles';
import * as authActions from '../../store/auth/action';

class Login extends Component {
    state = {
        username: '',
        password: ''
    };

    componentDidMount() {
        if (this.props.auth.loggedIn)
            this.props.history.push('/');
    }

    handlerChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    };

    onSubmit = (e) => {
        e.preventDefault();

        this.props.dispatch(
            authActions.login(this.state.username, this.state.password, res => {
                this.props.history.push('/');
            }, error => {
                this.props.notify(error.message);
            })
        );
    };

    render() {
        const {classes} = this.props;

        return (
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockIcon/>
                    </Avatar>
                    <Typography variant="headline">Acesso Restrito</Typography>
                    <form className={classes.form} onSubmit={this.onSubmit}>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel htmlFor="username">Username</InputLabel>
                            <Input id="username" name="username" autoFocus
                                   value={this.state.username}
                                   onChange={this.handlerChange}/>
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel htmlFor="password">Password</InputLabel>
                            <Input id="password" name="password" type="password"
                                   value={this.state.password}
                                   onChange={this.handlerChange}/>
                        </FormControl>
                        <Button type="submit" variant="raised" color="primary" className={classes.submit} fullWidth>
                            Entrar
                        </Button>
                    </form>
                </Paper>
            </main>
        );
    }
}

export default withStyles(styles)(Login);