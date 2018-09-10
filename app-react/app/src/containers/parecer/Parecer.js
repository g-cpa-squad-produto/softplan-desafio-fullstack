import _ from 'lodash';
import React, {Component} from 'react';
import {
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    Card,
    CardContent,
    Grid,
    IconButton
} from '@material-ui/core';
import {withStyles} from '@material-ui/core/styles';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/parecer/parecer-list';
import AuthService from '../../services/AuthService';
import LibraryBooks from '@material-ui/icons/LibraryBooks';
import ParecerDialog from './ParecerDialog';

const styles = {
    card: {
        minWidth: 500,
        maxWidth: 1000
    },
    grid: {
        minWidth: 500
    }
};

class Parecer extends Component {

    state = {
        modalOpened: false
    };

    user = null;

    handleClickOpen = (parecer) => {
        this.props.selectParecer(parecer);
        this.setState({modalOpened: true});
    };
    onCloseDialog = () => {
        this.setState({modalOpened: false});
        this.props.fetchPareceres(this.user.id);
    };
    onCancelDialog = () => {
        this.setState({modalOpened: false});
    };

    componentDidMount() {
        this.user = JSON.parse(AuthService.getUser());
        this.props.fetchPareceres(this.user.id);
    }

    render() {
        const {classes} = this.props;
        return (
            <div>
                <ParecerDialog opened={this.state.modalOpened}
                               onClose={this.onCloseDialog}
                               onCancel={this.onCancelDialog}/>
                <Grid container spacing={0} direction="column" align="center" style={{minHeight: '100vh'}}>
                    <Grid
                        item
                        xs={12}>
                        {this.props.isFetching ?
                            <span>Loading..</span> :
                            (<Card className={classes.card}>
                                <CardContent>
                                    <Table className={classes.table}>
                                        <TableHead>
                                            <TableRow>
                                                <TableCell>Processo</TableCell>
                                                <TableCell>Parecer</TableCell>
                                                <TableCell></TableCell>
                                            </TableRow>
                                        </TableHead>
                                        <TableBody>
                                            {this.props.pareceres.map(row => {
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

    renderRow(parecer) {
        return (
            <TableRow key={parecer.id}>
                <TableCell component="th" scope="row">
                    {parecer.processo.description}
                </TableCell>
                <TableCell>{parecer.parecer || 'Não informado'}</TableCell>
                <TableCell>
                    <IconButton disabled={!!parecer.parecer}
                                onClick={() => this.handleClickOpen(parecer)}
                                aria-label="Criar parecer">
                        <LibraryBooks />
                    </IconButton>
                </TableCell>
            </TableRow>
        );
    }
}

function mapStateToProps({parecer}) {
    return {
        pareceres: _.get(parecer, 'parecerList.pareceres') || [],
        isFetching: _.get(parecer, 'parecerList.isFetching')
    };
}

export default connect(mapStateToProps, actions)(withStyles(styles)(Parecer));