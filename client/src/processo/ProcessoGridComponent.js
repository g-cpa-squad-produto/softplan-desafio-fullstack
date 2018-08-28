import React, { Component } from 'react'
import { Table, TableHead, TableBody, TableCell, TableRow,Button } from '@material-ui/core'

export class ProcessoGridComponent extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Descrição</TableCell>
                            <TableCell>Data da Publicação</TableCell>
                            <TableCell># </TableCell>
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {this.props.processos.map(processo => {
                            return (
                                <TableRow key={processo.isnProcesso}>
                                    <TableCell component='th' scope='row'>
                                        {processo.descricaoProcesso}
                                    </TableCell>
                                    <TableCell>
                                        {processo.dataPublicaocaoProcesso}
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