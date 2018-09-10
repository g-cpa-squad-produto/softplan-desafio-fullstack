import _ from 'lodash';
import React, {Component} from 'react';
import {
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    Button,
    Card,
    CardContent,
    Grid,
    IconButton
} from '@material-ui/core';
import {withStyles} from '@material-ui/core/styles';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/process/process-list';
import ProcessDialog from './ProcessDialog';
import VinculoDialog from './VinculoDialog';
import SupervisorAccount from '@material-ui/icons/SupervisorAccount';

const styles = {
    card: {
        minWidth: 500,
        maxWidth: 1000
    },
    grid: {
        minWidth: 500
    }
};

class Process extends Component {
    state = {
        modalOpened: false,
        modalVinculoOpened: false
    };
    handleClickOpen = () => {
        this.setState({modalOpened: true});
    };
    onCloseDialog = () => {
        this.setState({modalOpened: false});
        this.props.fetchProcesses();
    };
    onCancelDialog = () => {
        this.setState({modalOpened: false});
    };
    handleClickOpenVinculo = (process) => {
        this.props.selectProcess(process);
        this.setState({modalVinculoOpened: true});
    };
    onCloseDialogVinculo = () => {
        this.setState({modalVinculoOpened: false});
    };
    onCancelDialogVinculo = () => {
        this.setState({modalVinculoOpened: false});
    };

    componentDidMount() {
        this.props.fetchProcesses();
    }

    render() {
        const {classes} = this.props;

        return (
            <div>
                <ProcessDialog opened={this.state.modalOpened}
                               onClose={this.onCloseDialog}
                               onCancel={this.onCancelDialog}/>
                <VinculoDialog opened={this.state.modalVinculoOpened}
                               onClose={this.onCloseDialogVinculo}
                               onCancel={this.onCancelDialogVinculo}/>
                <Grid container spacing={0} direction="column" align="center" style={{minHeight: '100vh'}}>
                    <Grid
                        item
                        xs={12}>
                        {this.props.isFetching ?
                            <span>Loading..</span> :
                            (<Card className={classes.card}>
                                <CardContent>
                                    <Button variant="contained" color="primary"
                                            onClick={this.handleClickOpen.bind(this)}
                                            className={classes.button}>
                                        Novo Processo
                                    </Button>
                                    <Table className={classes.table}>
                                        <TableHead>
                                            <TableRow>
                                                <TableCell>Nome</TableCell>
                                                <TableCell>Descrição</TableCell>
                                                <TableCell></TableCell>
                                            </TableRow>
                                        </TableHead>
                                        <TableBody>
                                            {this.props.processes.map(row => {
                                                return this.renderRow(row)
                                            })}
                                        </TableBody>
                                    </Table>
                                </CardContent>
                            </Card>)
                        }
                    </Grid>
                </Grid>
            </div>
        );
    }

    renderRow(process) {
        return (
            <TableRow key={process.id}>
                <TableCell component="th" scope="row">
                    {process.name}
                </TableCell>
                <TableCell>{process.description}</TableCell>
                <TableCell>
                    <IconButton onClick={() => this.handleClickOpenVinculo(process)} aria-label="Vincular usuários">
                        <SupervisorAccount/>
                    </IconButton>
                </TableCell>
            </TableRow>
        );
    }
}

function mapStateToProps({process}) {
    return {
        loading: _.get(process, 'processList.isFetching'),
        processes: _.get(process, 'processList.processes') || []
    };
}

export default connect(mapStateToProps, actions)(withStyles(styles)(Process));