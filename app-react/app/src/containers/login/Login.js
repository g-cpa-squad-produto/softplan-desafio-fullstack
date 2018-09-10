import {withStyles} from '@material-ui/core/styles';
import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/login';

const styles = {
    card: {
        maxWidth: 345,
    },
    actions: {
        justifyContent: 'flex-end'
    },
    error: {
        color: 'red'
    }
};

class Login extends Component {
    state = {
        username: '',
        password: ''
    };

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value});
    };

    onSubmit() {
        const data = {
            username: this.state.username,
            password: this.state.password
        };
        this.props.login(data, this.onLogin.bind(this));
    }

    onLogin(opts) {
        switch (opts.user.role) {
            case 'ROLE_ADMIN':
                return this.props.history.push('/users');
            case 'ROLE_TRIADOR':
                return this.props.history.push('/process');
            case 'ROLE_FINALIZADOR':
                return this.props.history.push('/parecer');
        }
    }

    render() {
        const {classes} = this.props;

        return (
            <form>
                <Grid
                    container
                    spacing={0}
                    direction="column"
                    alignItems="center"
                    justify="center"
                    style={{minHeight: '50vh'}}
                >
                    <Grid item xs={3}>
                        <Card className={classes.card}>
                            <CardContent>
                                <Typography gutterBottom variant="headline" component="h2">
                                    Challenge
                                </Typography>
                                {this.props.error ? <span style={styles.error}>{this.props.error}</span> : null}
                                <TextField
                                    id="username"
                                    label="E-mail"
                                    name="username"
                                    className={classes.textField}
                                    value={this.state.name}
                                    fullWidth
                                    onChange={this.handleChange('username')}
                                    margin="normal"
                                />
                                <TextField
                                    id="password"
                                    label="Senha"
                                    type={'password'}
                                    name="password"
                                    fullWidth
                                    className={classes.textField}
                                    value={this.state.name}
                                    onChange={this.handleChange('password')}
                                    margin="normal"
                                />
                            </CardContent>
                            <CardActions classes={{root: classes.actions}}>
                                {this.props.isFetching ?
                                    <span>Loading...</span>
                                    :
                                    <Button variant="contained" color="primary" onClick={this.onSubmit.bind(this)}
                                            className={classes.button}>
                                        Login
                                    </Button>}
                            </CardActions>
                        </Card>
                    </Grid>
                </Grid>
            </form>
        );
    }
}

const mapStateToProps = ({login}) => {
    return {
        isFetching: login.isFetching,
        error: login.error ? mapError(login.error) : null,
        tokens: login.tokens
    };
};

const mapError = ({response}) => {
    if (!response) {
        return null;
    }
    if (response.status === 401 || response.status === 400) {
        return 'Usuário ou senha inválidos';
    }
    return 'Ocorreu um erro interno no servidor';
};

export default connect(mapStateToProps, actions)(withStyles(styles)(Login));