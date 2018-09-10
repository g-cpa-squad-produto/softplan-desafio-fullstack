import React, {Component} from 'react';
import {TableBody, TableCell, TableHead, TableRow, Button, Tooltip, withStyles} from '@material-ui/core';
import TableCustom from '../../../../components/table/TableCustom';
import LinkDetail from '../../../../components/link/detail/LinkDetail';
import {Link} from 'react-router-dom';
import EyeIcon from '@material-ui/icons/RemoveRedEye';
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';
import moment from 'moment';
import {styles} from '../../../dashboard/styles';
import * as processActions from '../../ProcessActions';

class ProcessList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            processos: []
        };
    }

    componentWillMount() {
        processActions.findAll('/processos', res => {
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
                                <LinkDetail to={'/processos/' + processo.id} icon={<EyeIcon color='action'/>}/>
                                <LinkDetail to={'/processos/' + processo.id + '?action=remove'} icon={<DeleteIcon color='action'/>}/>
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

                <Link to="/processos/cadastrar">
                    <Tooltip title="Adicionar">
                        <Button variant="fab" color="primary" aria-label="Add" className={classes.marginMd + ' ' + classes.buttonAdd}>
                            <AddIcon />
                        </Button>
                    </Tooltip>
                </Link>
            </main>
        );
    }
}

export default withStyles(styles)(ProcessList);