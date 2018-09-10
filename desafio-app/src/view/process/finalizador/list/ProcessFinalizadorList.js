import React, {Component} from 'react';
import {TableBody, TableCell, TableHead, TableRow, withStyles} from '@material-ui/core';
import TableCustom from '../../../../components/table/TableCustom';
import LinkDetail from '../../../../components/link/detail/LinkDetail';
import CheckIcon from '@material-ui/icons/Check';
import moment from 'moment';
import {styles} from '../../../dashboard/styles';
import * as processActions from '../../ProcessActions';

class ProcessFinalizadorList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            processos: []
        };
    }

    componentWillMount() {
        processActions.findAll('/processos/finalizadores', res => {
            this.setState({
                processos: res.data
            });
        }, error => {
            this.props.notify(error);
        });
    }

    getTableHead = () => {
        return (
            <TableHead>
                <TableRow>
                    <TableCell>Descrição</TableCell>
                    <TableCell>Responsável</TableCell>
                    <TableCell>Finalizadores</TableCell>
                    <TableCell>Parecer Final</TableCell>
                    <TableCell>Data cadastro</TableCell>
                    <TableCell>Ações</TableCell>
                </TableRow>
            </TableHead>
        );
    };

    getTableBody = () => {
        return (
            <TableBody>
                {this.state.processos.map(processo => {
                    return (
                        <TableRow key={processo.id}>
                            <TableCell component="th" scope="row">{processo.descricao}</TableCell>
                            <TableCell>{processo.responsavel.nome}</TableCell>
                            <TableCell>{processo.finalizadores.map(f => f.nome).join(',')}</TableCell>
                            <TableCell>{processo.parecer || '-'}</TableCell>
                            <TableCell>{moment(processo.dataCriacao).format('DD/MM/YYYY HH:mm')}</TableCell>
                            <TableCell>
                                <LinkDetail to={'/processos/finalizadores/' + processo.id} icon={<CheckIcon color='action'/>}/>
                            </TableCell>
                        </TableRow>
                    );
                })}
            </TableBody>
        );
    };

    render() {
        const {classes} = this.props;

        return (
            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <TableCustom title="Processos" tableHead={this.getTableHead()} tableBody={this.getTableBody()}/>
            </main>
        );
    }
}

export default withStyles(styles)(ProcessFinalizadorList);