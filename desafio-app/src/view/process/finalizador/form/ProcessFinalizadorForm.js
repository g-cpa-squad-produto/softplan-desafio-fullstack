import React, {Component} from 'react';
import {Button, FormControl, Input, InputLabel, Paper, TextField, Typography, withStyles} from '@material-ui/core';
import {styles} from '../../../dashboard/styles';
import * as processActions from '../../ProcessActions';
import moment from "moment";

class ProcessFinalizadorForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            processoAFinalizar: {
                idProcesso: '',
                parecer: ''
            },
            processoDetail: {
                finalizadores: [],
                responsavel: {}
            },
            finalizadores: [],
        };
    }

    componentWillMount() {
        let id = this.props.match.params.id;

        processActions.findById(id, res => {
            this.setState({
                processoDetail: res.data,
                processoAFinalizar: {...this.state.processoAFinalizar, idProcesso: res.data.id}
            });
        }, error => {
            this.props.notify(error.message);
        });
    }

    handlerChange = (e) => {
        this.setState({processoAFinalizar: {...this.state.processoAFinalizar, [e.target.name]: e.target.value}});
    };

    onSubmit = (e) => {
        e.preventDefault();

        processActions.finalizar(this.state.processoAFinalizar, res => {
            this.props.notify('Processo finalizado com sucesso!');
            this.props.history.push('/processos/finalizadores');
        }, error => {
            this.props.notify(error.message);
        });
    };

    render() {
        const {classes} = this.props;

        return (
            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <Paper elevation={1} className={classes.root + ' ' + classes.paddingMd}>
                    <Typography variant="headline" gutterBottom>
                        Finalizar processo
                    </Typography>

                    <form onSubmit={this.onSubmit}>
                        <TextField label="Descrição" value={this.state.processoDetail.descricao}
                                   InputProps={{
                                       readOnly: true,
                                   }} fullWidth className={classes.marginBottomMd}/>

                        <TextField label="Responsável (criador)" value={this.state.processoDetail.responsavel.nome}
                                   InputProps={{
                                       readOnly: true,
                                   }} fullWidth className={classes.marginBottomMd}/>

                        <TextField label="Finalizadores" value={this.state.processoDetail.finalizadores.map(f => f.nome).join(',')}
                                   InputProps={{
                                       readOnly: true,
                                   }} fullWidth className={classes.marginBottomMd}/>

                        <TextField label="Data cadastro" value={moment(this.state.processoDetail.dataCriacao).format('DD/MM/YYYY HH:mm')}
                                   InputProps={{
                                       readOnly: true,
                                   }} fullWidth className={classes.marginBottomMd}/>

                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <InputLabel htmlFor="parecer">Parecer final</InputLabel>
                            <Input id="parecer" name="parecer" autoFocus
                                   value={this.state.processoAFinalizar.parecer}
                                   onChange={this.handlerChange}/>
                        </FormControl>

                        <Button type="submit" variant="raised" color="primary">Finalizar</Button>
                    </form>
                </Paper>
            </main>
        );
    }
}

export default withStyles(styles)(ProcessFinalizadorForm);