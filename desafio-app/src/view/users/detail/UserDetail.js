import React, {Component} from 'react';
import {Paper, Typography, Button, withStyles} from '@material-ui/core';
import {styles} from '../../dashboard/styles';
import moment from 'moment';
import {Link} from "react-router-dom";
import queryString from 'query-string';
import * as userActions from '../UserActions';

class UserDetail extends Component {
    constructor(props) {
        super(props);

        this.state = {
            usuario: {
                roles: []
            }
        };
    }

    componentWillMount() {
        let id = this.props.match.params.id;

        userActions.findById(id, res => {
            this.setState({
                usuario: res.data
            });
        }, error => {
            this.props.notify(error);
        });
    }

    isActionRemove = () => {
        const values = queryString.parse(this.props.location.search);
        return values.action === 'remove';
    };

    getRemoveButton = (className) => {
        return this.isActionRemove() ?
            <Button color="secondary" className={className} onClick={this.handleRemove}>Excluir</Button> :
            null;
    };

    handleRemove = () => {
        let id = this.props.match.params.id;

        userActions.remove(id, res => {
            this.props.notify('Usuário removido com sucesso!');
            this.props.history.push('/usuarios');
        }, error => {
            this.props.notify(error.message);
        });
    };

    render() {
        const {classes} = this.props;

        return (
            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <Paper elevation={1} className={classes.root}>
                    <Typography variant="headline" gutterBottom className={classes.title}>
                        Detalhes do usuário
                    </Typography>

                    <div className={classes.paddingMd}>
                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Nome:</strong> {this.state.usuario.nome}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Username:</strong> {this.state.usuario.username}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Permissões:</strong> {this.state.usuario.roles.map(r => r.nome).join(',')}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Data
                                cadastro:</strong> {moment(this.state.usuario.dataCriacao).format('DD/MM/YYYY HH:mm')}
                        </Typography>
                    </div>

                    <Link to="/usuarios" className={classes.noLinkDecoration}>
                        <Button color="primary" className={classes.marginMd}>
                            Voltar
                        </Button>
                    </Link>

                    {this.getRemoveButton(classes.marginMd)}
                </Paper>
            </main>
        );
    }
}

export default withStyles(styles)(UserDetail);