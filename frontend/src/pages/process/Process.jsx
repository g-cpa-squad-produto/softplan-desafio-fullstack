import React, { Component } from 'react'
import './Process.css'
import { getAllProcesses, getProcessesByUser, deleteProcess, acceptProcess, denyProcess } from '../../services/service'

export default class Process extends Component {

    state = {
        listOfProcesses: [],
        showEditForm: true,
        role: ''
    }

    componentDidMount() {
        const role = JSON.parse(sessionStorage.session_data).role
        this.setState({ role })
        this.getAllProcesses(role)
    }

    getAllProcesses = (role = this.state.role) => {
        const resource = role === 'TRIADOR' ? getAllProcesses : getProcessesByUser
        resource().then(response => {
            this.setState({ listOfProcesses: response.data.content || [] })
        })
    }

    deleteProcess = (id) => {
        deleteProcess(id).then(() => {
            this.getAllProcesses()
        })
    }

    acceptProcess = (id) => {
        acceptProcess(id).then(() => {
            this.getAllProcesses()
        })
    }

    denyProcess = (id) => {
        denyProcess(id).then(() => {
            this.getAllProcesses()
        })
    }

    openEditUserForm = (id) => {
        this.props.history.push(`/api/process/${id}`)
    }

    formatStatus = (status) => {
        switch(status) {
            case 'PENDING':
                return 'Pendente'
            case 'APPROVED':
                return 'Aprovado'
            case 'DENIED':
                return 'Negado'
            default:
                return 'Desconhecido'
        }
    }

    render() {
        return (
            <div >
                <div className="padding-container">
                    <div className="row">
                        <div className="col">
                            <h2>Lista de processos</h2>
                        </div>
                        <div className={this.state.role === 'TRIADOR' ? 'col' : 'hidden'}>
                            <button type="button" onClick={() => this.openEditUserForm(0)}
                                className="btn btn-secondary float-right">Novo processo</button>
                        </div>
                    </div>
                    <table className="table table-hover margin-top-25">
                        <thead>
                            <tr>
                                <th scope="col">Título</th>
                                <th scope="col">Responsável</th>
                                <th scope="col">Parecer</th>
                                <th scope="col">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.listOfProcesses.map((process) =>
                                <tr key={process.id}>
                                    <td>{process.title}</td>
                                    <td>{process.assign ? process.assign.username : 'Não atribuido'}</td>
                                    <td>{this.formatStatus(process.status)}</td>

                                    <td className={this.state.role === 'TRIADOR' ? '' : 'hidden'}>
                                        <button type="button" onClick={() => this.openEditUserForm(process.id)}
                                            className="btn btn-outline-secondary btn-sm">editar</button>
                                        <button type="button" onClick={() => this.deleteProcess(process.id)}
                                            className="btn btn-outline-secondary btn-sm ml-1">excluir</button>
                                    </td>

                                    <td className={this.state.role === 'FINALIZADOR' ? '' : 'hidden'}>
                                        <button type="button" onClick={() => this.acceptProcess(process.id)}
                                            className="btn btn-outline-secondary btn-sm">aprovar</button>
                                        <button type="button" onClick={() => this.denyProcess(process.id)}
                                            className="btn btn-outline-secondary btn-sm ml-1">negar</button>
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}