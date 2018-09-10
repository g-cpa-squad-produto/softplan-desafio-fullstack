import _ from 'lodash';
import React, {Component} from 'react';
import {
    Dialog, DialogTitle,
    DialogContent,
    TextField,
    DialogActions,
    Button,
    FormControl
} from '@material-ui/core';
import {withStyles} from '@material-ui/core/styles';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/process/process-dialog';
import {validateField, validateForm} from '../../utils/validation-utils';

const styles = {
    root: {
        minWidth: 400
    },
    formControl: {},
    error: {
        color: 'red'
    }
};

const requiredFields = ['name', 'description'];

class ProcessDialog extends Component {
    state = {
        id: null,
        name: '',
        description: '',
        formValid: false,
        invalids: []
    };

    handleSave = () => {
        this.props.save(this.state, () => {
            this.props.onClose();
        });
    };

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value}, () => this.validateRequired(prop));
    };

    onEnter() {
        this.setState({
            id: null,
            name: '',
            description: '',
            formValid: false,
            invalids: []
        }, this.validateFormFields);
    }

    validateRequired(name) {
        const result = validateField(name, this.state, requiredFields);
        this.setState(result);
    }

    validateFormFields() {
        const result = validateForm(this.state, requiredFields);
        this.setState(result);
    }

    render() {
        const {classes} = this.props;
        return (<Dialog
                open={this.props.opened}
                onClose={this.props.onClose}
                onEnter={this.onEnter.bind(this)}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
                className={classes.root}
            >
                <DialogTitle id="alert-dialog-title">{'Cadastrando processos'}</DialogTitle>
                <DialogContent>
                    <div>
                        {this.props.error ? <span style={styles.error}>{this.props.error}</span> : null}
                    </div>
                    <form name="processForm" className={classes.root} autoComplete="off">
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
                                    id="description"
                                    label="Descrição"
                                    onChange={this.handleChange('description')}
                                    value={this.state.description}
                                    margin="normal"
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
                        <Button onClick={this.handleSave.bind(this)} disabled={!this.state.formValid} color="primary"
                                autoFocus>
                            Salvar
                        </Button>
                    </DialogActions>)
                }
            </Dialog>
        );
    }
}

function mapStateToProps({process}) {
    const error = _.get(process, 'processDialog.error');
    return {
        loading: _.get(process, 'processDialog.loading'),
        error: error ? mapError(error) : null
    };
}

function mapError({response}) {
    return _.get(response, 'data.message') || 'Ocorreu um erro ao salvar o processo';
}

export default connect(mapStateToProps, actions)(withStyles(styles)(ProcessDialog));