import React, {Component} from 'react';
import {
    FormControl,
    Input,
    InputLabel,
    Paper,
    Typography,
    TextField,
    MenuItem,
    withStyles,
    Button
} from '@material-ui/core';
import {styles} from '../../../dashboard/styles';
import * as processActions from '../../ProcessActions';

class ProcessForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            processo: {
                descricao: '',
                finalizador: ''
            },
            finalizadores: [],
        };
    }

    componentWillMount() {
        processActions.findAll('/usuarios?role=ROLE_FINALIZADOR', res => {
            this.setState({finalizadores: res.data});
        }, error => {
            this.props.notify(error.message);
        });
    }

    handlerChange = (e) => {
        if (e.target.name === 'finalizadores') {
            let finalizadoresValue = this.state.finalizadores.find(f => f.id === e.target.value);
            this.setState({
                processo: {...this.state.processo, finalizador: finalizadoresValue.id}
            });
        } else {
            this.setState({processo: {...this.state.processo, [e.target.name]: e.target.value}});
        }
    };

    onSubmit = (e) => {
        e.preventDefault();

        processActions.create(this.state.processo, res => {
            this.props.notify('Processo cadastrado com sucesso!');
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
                <Paper elevation={1} className={classes.root + ' ' + classes.paddingMd}>
                    <Typography variant="headline" gutterBottom>
                        Cadastrar processo
                    </Typography>

                    <form onSubmit={this.onSubmit}>
                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <InputLabel htmlFor="descricao">Descrição</InputLabel>
                            <Input id="descricao" name="descricao" autoFocus
                                   value={this.state.processo.descricao}
                                   onChange={this.handlerChange}/>
                        </FormControl>

                        <FormControl required fullWidth className={classes.marginBottomMd}>
                            <TextField id="select-finalizador" select label="Finalizador"
                                       value={this.state.processo.finalizador} onChange={this.handlerChange} name="finalizadores"
                                       SelectProps={{
                                           MenuProps: {
                                               className: classes.menu,
                                           },
                                       }} required>
                                {this.state.finalizadores.map(r => (
                                    <MenuItem key={'f' + r.id} value={r.id}>
                                        {r.nome}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </FormControl>

                        <Button type="submit" variant="raised" color="primary">Enviar</Button>
                    </form>
                </Paper>
            </main>
        );
    }
}

export default withStyles(styles)(ProcessForm);