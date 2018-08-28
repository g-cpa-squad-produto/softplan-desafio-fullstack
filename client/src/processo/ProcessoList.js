import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Divider, Button } from '@material-ui/core'
import { ProcessoGridComponent } from './ProcessoGridComponent'

import { ProcessoAPI } from './ProcessoAPI'

const processoService = new ProcessoAPI();

export class ProcessoList extends Component {

    constructor(props) {
        super(props)
        this.state = {
            processos: []
        }
    }

    componentDidMount() {
        processoService.getAllProcessos()
            .then(data => this.setState({ processos: data }))
    }

    render() {
        return (
            <div>
                <h2>Processos </h2>
                <Divider />

                <ProcessoGridComponent processos={this.state.processos} />

                <br />
                <Button variant="contained" color="primary" component={Link} to='/processo/novo'>
                    Novo Processo
                </Button>
            </div>
        )
    }

}