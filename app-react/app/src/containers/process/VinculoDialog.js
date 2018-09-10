import _ from 'lodash';
import React, {Component} from 'react';
import {
    Dialog, DialogTitle,
    DialogContent,
    List,
    DialogActions,
    Button,
    ListItem,
    ListItemText,
    Checkbox
} from '@material-ui/core';
import {withStyles} from '@material-ui/core/styles';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/process/process-vinculos';
import processList from "../../redux/modules/process/process-list";

const styles = {
    root: {
        minWidth: 400
    },
    formControl: {},
    error: {
        color: 'red'
    }
};

class VinculoDialog extends Component {

    state = {
        selecteds: []
    };

    handleSave = () => {
        this.props.save(this.state.selecteds, this.props.process)
            .then(() => {
                this.props.onClose();
            });
    };

    handleToggle = value => () => {
        const {selecteds} = this.state;
        const current = _.findIndex(selecteds, {id: value.id});
        const newSelecteds = [...selecteds];

        if (current === -1) {
            newSelecteds.push(value);
        } else {
            newSelecteds.splice(current, 1);
        }

        this.setState({
            selecteds: newSelecteds,
        });
    };

    onEnter() {
        this.props.fetchVinculaveis(this.props.process.id);
        this.setState({selecteds: []});
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
                <DialogTitle id="alert-dialog-title">{'Vinculando processos'}</DialogTitle>
                <DialogContent>
                    <div>
                        {!this.props.vinculaveis.length ? <span>Não exitem usuários para vincular</span> : null}
                    </div>
                    <div>
                        {this.props.error ? <span style={styles.error}>{this.props.error}</span> : null}
                    </div>
                    <List>
                        {this.props.vinculaveis.map(item => {
                            return this.renderItem(item)
                        })}
                    </List>
                </DialogContent>
                {this.props.loading ?
                    <span>Loading...</span> :
                    (<DialogActions>
                        <Button
                            onClick={this.props.onCancel}
                            color="primary">
                            Cancelar
                        </Button>
                        <Button
                            onClick={this.handleSave}
                            disabled={!this.state.selecteds.length}
                            color="primary"
                            autoFocus>
                            Salvar
                        </Button>
                    </DialogActions>)
                }
            </Dialog>
        );
    }

    renderItem(vinculavel) {
        return (
            <ListItem key={vinculavel.id}
                      role={undefined}
                      dense
                      button
                      onClick={this.handleToggle(vinculavel)}>
                <ListItemText
                    primary={vinculavel.name}/>
                <Checkbox
                    disableRipple
                    tabIndex={-1}
                    checked={_.includes(this.state.selecteds, vinculavel)}
                />
            </ListItem>
        );
    }
}

function mapStateToProps({process}) {
    const error = _.get(process, 'processVinculo.error');
    return {
        loading: _.get(process, 'processVinculo.isFetching'),
        vinculaveis: _.get(process, 'processVinculo.vinculaveis') || [],
        error: error ? mapError(error) : null,
        process: _.get(process, 'processList.selected')
    };
}

function mapError({response}) {
    return _.get(response, 'data.message') || 'Ocorreu um erro ao salvar o vínculo';
}

export default connect(mapStateToProps, actions)(withStyles(styles)(VinculoDialog));