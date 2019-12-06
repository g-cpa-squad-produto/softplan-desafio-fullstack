import React, { Component } from 'react'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import CreateIcon from '@material-ui/icons/Create';
import VisibilityOutlinedIcon from '@material-ui/icons/VisibilityOutlined';
import Typography from '@material-ui/core/Typography';
import { Link } from 'react-router-dom'

import api from './../Api'
import DialogSimples from './DialogSimples'

function BotaoNovo(params) {

    if (localStorage.getItem('gerenciador-processo-online/tipoUsuario') === 'triador' ||
        localStorage.getItem('gerenciador-processo-online/tipoUsuario') === 'administrador'
    ) {
        return (
            <div className="col-xs-12 col-md-6">
                <Link className="btn btn-success" to={'/new-processo/'}>Novo processo</Link>
            </div>)
    } else {
        return <div />
    }
}

class Processos extends Component {

    constructor(props) {
        super(props)
        this.state = {
            processos: [],
            message: null,
            abrirDialog: false,
            usuarioDialogAberto: null
        }
        this.deleteUser = this.deleteUser.bind(this);
        this.realizarParecerPorProcesso = this.realizarParecerPorProcesso.bind(this);
        this.loadProcessos = this.loadProcessos.bind(this);
    }

    componentDidMount() {
        this.loadProcessos();
    }

    loadProcessos() {
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

    abrirDialogDescricao() {
        this.setState({
            abrirDialog: true
        });
    }


    render() {
        return (
            <div>
                <Typography variant="h4" style={style}>Gerenciador de processos</Typography>
                {/*  <Button variant="contained" color="primary" onClick={() => this.addUser()}>
                    Criar processo
                </Button> */}
                <BotaoNovo />
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
                                <TableCell >{row.dataCriacao}</TableCell>
                                <TableCell component="th" scope="row">{row.descricao}</TableCell>
                                {row !== undefined && row.parecer !== null &&
                                    <TableCell component="th" scope="row">Parecer realizado</TableCell>}
                                {row !== undefined && row.parecer === null &&
                                    <TableCell component="th" scope="row">Parecer pendente</TableCell>

                                }
                                {/*                                 <BotoesOperacao row={row} state={this.state} />
 */}

                                {localStorage.getItem('gerenciador-processo-online/tipoUsuario') === 'finalizador' &&
                                    <div>
                                        {row !== undefined && row.parecer === null &&
                                            <Link className="btn btn-success" to={'/parecer/' + row.id}>Realizar parecer</Link>
                                        }
                                    </div>
                                }
                                {row !== undefined && row.parecer !== null &&
                                    <Link className="btn btn-success" to={'/parecer/' + row.id}>Visualizar parecer</Link>

                                }
                                {/*                                 {row.parecer !== null && this.state.abrirDialog === true && row.parecer.usuario !== null
                                 && 
                                    <DialogSimples texto={row.parecer.texto} usuario={row.parecer.usuario.email} callbackParent={(bool) => this.setState({ abrirDialog: bool })} />
                                } */}
                                {/*                                 {row.parecer !== null && this.state.abrirDialog === true && row.parecer.usuario === null && 
                                    <DialogSimples texto={row.parecer.texto} usuario={'O usuário foi removido'} callbackParent={(bool) => this.setState({ abrirDialog: bool })} />
                                } */}
                                {/*                                 {localStorage.getItem('gerenciador-processo-online/tipoUsuario') === 'triador' &&
                                    <div>
                                        {row !== undefined && row.parecer === null &&
                                            <Link className="btn btn-success" to={'/atribuir-usuario/' + row.id}>Atribuir usuário</Link>
                                        }
                                    </div>
                                } */}

                                {/*                                     <Link className="btn btn-warning" to={''} onClick={this.abrirDialogDescricao()}>Atribuir usuário</Link>}
 */}                                {/*   <Button variant="contained" color="primary" align="right" onClick={() => this.realizarParecerPorProcesso(row.id)}><CreateIcon />Atribuir usuário</Button>
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