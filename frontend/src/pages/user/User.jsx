import React, { Component } from 'react'
import './User.css'

import { getAllUsers, deleteUser } from '../../services/service'

export default class User extends Component {

    constructor(props) {
        super(props);

        this.state = {
            listOfUsers: [],
            showEditForm: true
        }

        this.getAllUsers()
    }

    getAllUsers = () => {
        getAllUsers().then(
            response => {
                console.log("response.data", response.data);
                this.setState({ listOfUsers: response.data.content || [] })
            }, error => {
                console.log(error.response)
            })
    }

    deleteUser = (id) => {
        deleteUser(id).then(
            response => {
                console.log("response.data", response.data);
                this.getAllUsers()
            }, error => {
                console.log(error.response)
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
                            <button type="button" onClick={() => this.openEditUserForm(0)} className="btn btn-outline-primary float-right">Novo usuário</button>
                        </div>
                    </div>
                    <table className="table table-hover margin-top-25">
                        <thead>
                            <tr>
                                {/* <th scope="col">ID</th> */}
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
                                    {/* <th scope="row">{user.id}</th> */}
                                    <td>{user.username}</td>
                                    <td>{user.name}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.role}</td>
                                    <td>
                                        <button type="button" onClick={() => this.openEditUserForm(user.id)} className="btn btn-secondary btn-sm">editar</button>
                                        <button type="button" onClick={() => this.deleteUser(user.id)} className="btn btn-secondary btn-sm ml-1">excluir</button>
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