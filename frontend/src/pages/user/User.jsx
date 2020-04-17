import React, { Component } from 'react'
import { getAllUsers, deleteUser } from '../../services/service'

import './User.css'

export default class User extends Component {

    state = {
        listOfUsers: [],
        showEditForm: true
    }

    componentDidMount() {
        this.getAllUsers()
    }

    getAllUsers = () => {
        getAllUsers().then(response => {
            this.setState({ listOfUsers: response.data.content || [] })
        })
    }

    deleteUser = (id) => {
        deleteUser(id).then(() => {
            this.getAllUsers()
        })
    }

    openEditUserForm = (id) => {
        this.props.history.push(`/api/user/${id}`)
    }

    render() {
        return (
            <div >
                <div className="padding-container">
                    <div className="row">
                        <div className="col">
                            <h2>Lista de usuários</h2>
                        </div>
                        <div className="col">
                            <button type="button" onClick={() => this.openEditUserForm(0)} className="btn btn-secondary float-right">Novo usuário</button>
                        </div>
                    </div>
                    <table className="table table-hover margin-top-25">
                        <thead>
                            <tr>
                                <th scope="col">Usuário</th>
                                <th scope="col">Nome</th>
                                <th scope="col">Sobrenome</th>
                                <th scope="col">Permissão</th>
                                <th scope="col">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.listOfUsers.map((user) => 
                                <tr key={user.id}>
                                    <td>{user.username}</td>
                                    <td>{user.name}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.role}</td>
                                    <td>
                                        <button type="button" onClick={() => this.openEditUserForm(user.id)} className="btn btn-outline-secondary btn-sm">editar</button>
                                        <button type="button" onClick={() => this.deleteUser(user.id)} className="btn btn-outline-secondary btn-sm ml-1">excluir</button>
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