import React, {Component} from 'react';
import {Paper, Typography, Button, withStyles} from '@material-ui/core';
import {styles} from '../../../dashboard/styles';
import moment from 'moment';
import {Link} from "react-router-dom";
import queryString from 'query-string';
import * as processActions from '../../ProcessActions';

class ProcessDetail extends Component {
    constructor(props) {
        super(props);

        this.state = {
            processo: {
                responsavel: {},
                finalizadores: []
            }
        };
    }

    componentWillMount() {
        let id = this.props.match.params.id;

        processActions.findById(id, res => {
            this.setState({
                processo: res.data
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

        processActions.remove(id, res => {
            this.props.notify('Processo removido com sucesso!');
            this.props.history.push('/processos');
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
                        Detalhes do processo
                    </Typography>

                    <div className={classes.paddingMd}>
                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Descrição:</strong> {this.state.processo.descricao}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Responsável (criador):</strong> {this.state.processo.responsavel.nome}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Finalizadores:</strong> {this.state.processo.finalizadores.map(f => f.nome).join(',')}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Parecer Final:</strong> {this.state.processo.parecer || '-'}
                        </Typography>

                        <Typography gutterBottom noWrap className={classes.marginBottomMd}>
                            <strong>Data
                                cadastro:</strong> {moment(this.state.processo.dataCriacao).format('DD/MM/YYYY HH:mm')}
                        </Typography>
                    </div>

                    <Link to="/processos" className={classes.noLinkDecoration}>
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

export default withStyles(styles)(ProcessDetail);