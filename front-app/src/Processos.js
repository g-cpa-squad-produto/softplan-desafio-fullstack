import React, { Component } from 'react'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import CreateIcon from '@material-ui/icons/Create';
import DeleteIcon from '@material-ui/icons/Delete';
import Typography from '@material-ui/core/Typography';
import { Link } from 'react-router-dom'

import api from './Api'


class Processos extends Component {

    constructor(props) {
        super(props)
        this.state = {
            processos: [],
            message: null
        }
        this.deleteUser = this.deleteUser.bind(this);
        this.realizarParecerPorProcesso = this.realizarParecerPorProcesso.bind(this);
        this.addUser = this.addUser.bind(this);
        this.loadProcessos = this.loadProcessos.bind(this);
    }

    componentDidMount() {
        this.loadProcessos();
    }

    loadProcessos() {

        /*  ApiService.fetchUsers()
             .then((res) => {
                 this.setState({users: res.data.result})
             }); */
        api.loadProcessos().then((res) => {
            this.setState({
                processos: res.data,
            });
        })
    }

    deleteUser(userId) {
        /*         ApiService.deleteUser(userId)
                   .then(res => {
                       this.setState({message : 'User deleted successfully.'});
                       this.setState({users: this.state.users.filter(user => user.id !== userId)});
                   }) */
    }

    realizarParecerPorProcesso(id) {
        /*         window.localStorage.setItem("userId", id);
                this.props.history.push('/edit-user'); */
    }

    addUser() {
        /*  window.localStorage.removeItem("userId");
         this.props.history.push('/add-user'); */
    }

    render() {
        return (
            <div>
                <Typography variant="h4" style={style}>Gerenciador de processos</Typography>
                {/*  <Button variant="contained" color="primary" onClick={() => this.addUser()}>
                    Criar processo
                </Button> */}
                <div className="col-xs-12 col-md-6">
                    {/* Criar lógica para aparecer somente quando não tiver parecer, se tiver mostra opção para ver o texto */}
                    <Link className="btn btn-success" to={'/new-processo/'}>Novo processo</Link>
                </div>

                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Código</TableCell>
                            <TableCell>Data</TableCell>
                            <TableCell>Descrição</TableCell>
                            <TableCell>Situação do parecer</TableCell>
                            <TableCell>Operações</TableCell>

                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.processos.map(row => (
                            <TableRow key={row.id}>
                                <TableCell component="th" scope="row">
                                    {row.codigo}
                                </TableCell>
                                <TableCell >{row.data}</TableCell>
                                <TableCell component="th" scope="row">{row.descricao}</TableCell>
                                {
                                    JSON.stringify(row.parecer.id) !== undefined &&
                                    <TableCell component="th" scope="row">Parecer realizado</TableCell>

                                }
                                {
                                    JSON.stringify(row.parecer.id) === undefined &&
                                    <TableCell component="th" scope="row">Parecer pendente</TableCell>

                                }

                                {

                                    JSON.stringify(row.parecer.id) === undefined &&
                                    <div className="col-xs-12 col-md-12">
                                        {/* Criar lógica para aparecer somente quando não tiver parecer, se tiver mostra opção para ver o texto */}
                                        <Link className="btn btn-success" to={'/realizar-parecer/' + row.id}>Realizar parecer</Link>
                                        {/* Sumir essa opção quando tiver um parecer*/}
                                        <Link className="btn btn-warning" to={''}>Atribuir usuário</Link>
                                    </div>
                                }



                                {/*   <Button variant="contained" color="primary" align="right" onClick={() => this.realizarParecerPorProcesso(row.id)}><CreateIcon />Atribuir usuário</Button>
                                <Button variant="contained" color="primary" align="right" onClick={() => this.deleteUser(row.id)}><DeleteIcon />Realizar parecer</Button> */}
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>

            </div>
        );
    }

}

const style = {
    display: 'flex',
    justifyContent: 'center'
}

export default Processos;