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
import {validateField, validateForm} from '../../utils/validation-utils';
import * as actions from '../../redux/modules/parecer/parecer-dialog';

const styles = {
    root: {
        minWidth: 400
    },
    formControl: {},
    error: {
        color: 'red'
    }
};

const requiredFields = ['parecer'];

const defaultState = {
    parecer: '',
    formValid: false,
    invalids: []
};

class ParecerDialog extends Component {
    state = _.clone(defaultState);

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value}, () => this.validateRequired(prop));
    };

    onEnter() {
        this.setState(defaultState, this.validateFormFields);
    }

    validateRequired(name) {
        const result = validateField(name, this.state, requiredFields);
        this.setState(result);
    }

    validateFormFields() {
        const result = validateForm(this.state, requiredFields);
        this.setState(result);
    }

    handleSave() {
        const parecer = {
            ...this.props.parecer,
            parecer: this.state.parecer
        };
        this.props.save(parecer, () => {
            this.props.onClose();
        });
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
                <DialogTitle id="alert-dialog-title">{'Cadastrando parecer'}</DialogTitle>
                <DialogContent>
                    <div>
                        {this.props.error ? <span style={styles.error}>{this.props.error}</span> : null}
                    </div>
                    <form name="parecerForm" className={classes.root} autoComplete="off">
                        <div>
                            <FormControl fullWidth className={classes.formControl}>
                                <TextField
                                    id="parecer"
                                    label="Parecer"
                                    onChange={this.handleChange('parecer')}
                                    value={this.state.parecer}
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

function mapStateToProps({parecer}) {
    const error = _.get(parecer, 'parecerDialog.error');
    return {
        loading: _.get(parecer, 'parecerDialog.loading'),
        error: error ? mapError(error) : null,
        parecer: _.get(parecer, 'parecerList.selected')
    };
}

function mapError({response}) {
    return _.get(response, 'data.message') || 'Ocorreu um erro ao salvar o parecer';
}

export default connect(mapStateToProps, actions)(withStyles(styles)(ParecerDialog));