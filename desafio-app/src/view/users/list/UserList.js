import React, {Component} from 'react';
import {TableBody, TableCell, TableHead, TableRow, Button, Tooltip, withStyles} from '@material-ui/core';
import TableCustom from '../../../components/table/TableCustom';
import LinkDetail from '../../../components/link/detail/LinkDetail';
import {Link} from 'react-router-dom';
import EyeIcon from '@material-ui/icons/RemoveRedEye';
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';
import moment from 'moment';
import {styles} from '../../dashboard/styles';
import * as userActions from '../UserActions';

class UserList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            usuarios: []
        };
    }

    componentWillMount() {
        userActions.findAll('/usuarios', res => {
            this.setState({
                usuarios: res.data
            });
        }, error => {
            this.props.notify(error);
        });
    }

    getTableHead = () => {
        return (
            <TableHead>
                <TableRow>
                    <TableCell>Nome</TableCell>
                    <TableCell>Username</TableCell>
                    <TableCell>Data Cadastro</TableCell>
                    <TableCell>Permissões</TableCell>
                    <TableCell>Ações</TableCell>
                </TableRow>
            </TableHead>
        );
    };

    getTableBody = () => {
        return (
            <TableBody>
                {this.state.usuarios.map(usuario => {
                    return (
                        <TableRow key={usuario.id}>
                            <TableCell component="th" scope="row">{usuario.nome}</TableCell>
                            <TableCell>{usuario.username}</TableCell>
                            <TableCell>{moment(usuario.dataCriacao).format('DD/MM/YYYY HH:mm')}</TableCell>
                            <TableCell>{usuario.roles.map(r => r.nome).join(',')}</TableCell>
                            <TableCell>
                                <LinkDetail to={'/usuarios/' + usuario.id} icon={<EyeIcon color='action'/>}/>
                                <LinkDetail to={'/usuarios/' + usuario.id + '?action=remove'} icon={<DeleteIcon color='action'/>}/>
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
                <TableCustom title="Usuários" tableHead={this.getTableHead()} tableBody={this.getTableBody()}/>

                <Link to="/usuarios/cadastrar">
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

export default withStyles(styles)(UserList);