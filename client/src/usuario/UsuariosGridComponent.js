import React, { Component } from 'react'
import { Table, TableHead, TableBody, TableCell, TableRow, Button } from '@material-ui/core'
import { AddCircle, Edit, Search } from '@material-ui/icons'


const usuarioAtivo = (ativo) => {
    if (ativo)
        return "Ativo"
    return "Não Ativo"
}


export class UsuarioGridComponent extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Nome</TableCell>
                            <TableCell>Sobrenome</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell># </TableCell>
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {this.props.usuarios.map(usuario => {
                            return (
                                <TableRow key={usuario.isnUsuario}>
                                    <TableCell component='th' scope='row'>
                                        {usuario.nomeUsuario}
                                    </TableCell>
                                    <TableCell>
                                        {usuario.sobrenomeUsuario}
                                    </TableCell>
                                    <TableCell>
                                        {usuarioAtivo(usuario.usuarioAtivo)}
                                    </TableCell>
                                    <TableCell>
                                        <Button variant="contained" size="small" color="primary">
                                            Visualizar
                                        </Button>
                                        <Button variant="contained" size="small" color="primary">
                                            Editar
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            )
                        })}
                    </TableBody>
                </Table>
            </div>
        )
    }

}
