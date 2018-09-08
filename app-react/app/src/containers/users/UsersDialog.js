import _ from 'lodash';
import React, {Component} from 'react';
import {
    Dialog, DialogTitle,
    DialogContent,
    TextField,
    DialogActions,
    Button,
    InputLabel,
    Select,
    MenuItem,
    FormControl
} from '@material-ui/core';
import {withStyles} from '@material-ui/core/styles';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/user/saved-user';
import {Field, reduxForm} from 'redux-form';

const styles = {
    root: {
        minWidth: 400
    },
    formControl: {},
    error: {
        color: 'red'
    }
};

class UsersDialog extends Component {
    state = {
        id: null,
        name: '',
        email: '',
        role: '',
        password: '',
        matchPassword: ''
    };
    handleSave = () => {
        this.props.saveUser(this.state, () => {
            this.props.onClose();
        });
    };
    handleChange = prop => event => {
        this.setState({[prop]: event.target.value});
    };

    onEnter() {
        this.props.resetUser();
        this.setState(_.extend(this.props.user, {password: '', matchPassword: ''}));
    }

    render() {
        const {classes} = this.props;

        return (
            <Dialog
                open={this.props.opened}
                onClose={this.props.onClose}
                onEnter={this.onEnter.bind(this)}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
                className={classes.root}
            >
                <DialogTitle id="alert-dialog-title">{'Cadastrando usuários'}</DialogTitle>
                <DialogContent>
                    <div>
                        {this.props.current}
                    </div>
                    <div>
                        {this.props.error ? <span style={styles.error}>{this.props.error}</span> : null}
                    </div>
                    <form name="userForm" className={classes.root} autoComplete="off">
                        <div>
                            <FormControl fullWidth className={classes.formControl}>
                                <TextField
                                    id="name"
                                    label="Nome"
                                    onChange={this.handleChange('name')}
                                    value={this.state.name}
                                    margin="normal"
                                    required
                                />
                            </FormControl>
                        </div>
                        <div>
                            <FormControl fullWidth className={classes.formControl}>
                                <TextField
                                    id="email"
                                    label="E-mail"
                                    onChange={this.handleChange('email')}
                                    value={this.state.email}
                                    margin="normal"
                                    required
                                />
                            </FormControl>
                        </div>
                        <div>
                            <FormControl fullWidth className={classes.formControl}>
                                <InputLabel required htmlFor="role-simple">Perfil</InputLabel>
                                <Select
                                    value={this.state.role}
                                    onChange={this.handleChange('role')}
                                    inputProps={{
                                        name: 'role',
                                        id: 'role-simple',
                                    }}
                                >
                                    <MenuItem value={'ROLE_ADMIN'}>Admin</MenuItem>
                                    <MenuItem value={'ROLE_TRIADOR'}>Triador</MenuItem>
                                    <MenuItem value={'ROLE_FINALIZADOR'}>Finalizador</MenuItem>
                                </Select>
                            </FormControl>
                        </div>
                        <div>
                            <FormControl fullWidth className={classes.formControl}>
                                <TextField
                                    id="password"
                                    label="Password"
                                    onChange={this.handleChange('password')}
                                    value={this.state.password}
                                    margin="normal"
                                    type={'password'}
                                    required
                                />
                            </FormControl>
                        </div>
                        <div>
                            <FormControl fullWidth className={classes.formControl}>
                                <TextField
                                    id="matchPassword"
                                    label="Repetir senha"
                                    onChange={this.handleChange('matchPassword')}
                                    value={this.state.matchPassword}
                                    margin="normal"
                                    type={'password'}
                                    required
                                />
                            </FormControl>
                        </div>
                    </form>
                </DialogContent>
                {this.props.loading ?
                    <span>Loading...</span> :
                    (<DialogActions>
                        <Button
                            onClick={this.props.onCancel}
                            color="primary">
                            Cancelar
                        </Button>
                        <Button onClick={this.handleSave.bind(this)} color="primary" autoFocus>
                            Salvar
                        </Button>
                    </DialogActions>)
                }
            </Dialog>
        );
    }
}

function mapStateToProps({user}) {
    if (!user || !user.saveUser) {
        return;
    }

    return {
        loading: user.saveUser.loading,
        error: user.saveUser.error ? mapError(user.saveUser.error) : null,
        user: _.get(user, 'user.user')
    };
}

function mapError({response}) {
    return response.data.message || 'Ocorreu um erro ao salvar o usuário';
}

function validate(values) {
    const errors = {};
    const requiredFields = [
        'name',
        'email'
    ];
    requiredFields.forEach(field => {
        if (!values[field]) {
            errors[field] = 'Required';
        }
    });
    return errors;
}

export default connect(mapStateToProps, actions)
    (reduxForm({
        form: 'userForm',
        validate,
    })(withStyles(styles)(UsersDialog)));