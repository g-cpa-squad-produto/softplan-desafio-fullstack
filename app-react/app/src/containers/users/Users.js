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
import DeleteIcon from '@material-ui/icons/Delete';
import Edit from '@material-ui/icons/Edit';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/user/user';
import UsersDialog from './UsersDialog';
import AuthService from '../../services/AuthService';

const styles = {
    card: {
        minWidth: 500,
        maxWidth: 1000
    },
    grid: {
        minWidth: 500
    }
};

class Users extends Component {

    state = {
        modalOpened: false
    };

    user = null;

    handleClickOpen = () => {
        this.props.resetEditUser();
        this.setState({modalOpened: true});
    };

    onHandleDelete = item => () => {
        this.props.deleteUser(item.id, () => {
            this.props.fetchUsers();
        });
    };

    onCloseDialog = () => {
        this.setState({modalOpened: false});
        this.props.fetchUsers();
    };

    onCancelDialog = () => {
        this.setState({modalOpened: false});
    };

    onClickEdit = item => () => {
        this.props.fetchUser(item.id, () => {
            this.setState({modalOpened: true});
        });
    };

    componentDidMount() {
        this.props.fetchUsers();
        this.user = JSON.parse(AuthService.getUser());
    }

    render() {
        const {classes} = this.props;

        return (
            <div>
                <UsersDialog opened={this.state.modalOpened}
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
                                    <Button variant="contained" color="primary"
                                            onClick={this.handleClickOpen.bind(this)}
                                            className={classes.button}>
                                        Novo usuário
                                    </Button>
                                    <Table className={classes.table}>
                                        <TableHead>
                                            <TableRow>
                                                <TableCell>Nome</TableCell>
                                                <TableCell>E-mail</TableCell>
                                                <TableCell>Perfil</TableCell>
                                                <TableCell></TableCell>
                                            </TableRow>
                                        </TableHead>
                                        <TableBody>
                                            {this.props.users.map(row => {
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

    renderRow(user) {
        return (
            <TableRow key={user.id}>
                <TableCell component="th" scope="row">
                    {user.name}
                </TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{user.role}</TableCell>
                {user.id === _.get(this.user, 'id') ? null :
                    (<TableCell>
                        <IconButton onClick={this.onClickEdit(user).bind(this)} aria-label="Add an alarm">
                            <Edit/>
                        </IconButton>
                        <IconButton onClick={this.onHandleDelete(user).bind(this)} aria-label="Add an alarm">
                            <DeleteIcon/>
                        </IconButton>
                    </TableCell>)
                }

            </TableRow>
        );
    }
}

function mapStateToProps({user}) {
    if (!user || !user.user) {
        return {};
    }
    return {
        isFetching: user.user.isFetching,
        users: user.user.users || []
    };
}

export default connect(mapStateToProps, actions)(withStyles(styles)(Users));