import React, { Component } from 'react'
import { Table, TableHead, TableBody, TableCell, TableRow, List } from '@material-ui/core'

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
                                </TableRow>
                            )
                        })}
                    </TableBody>
                </Table>
            </div>
        )
    }

}
