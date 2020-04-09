import React, { Component } from "react";
import UserDataService from "../service/UserDataService";

const USERNAME = 'username'

class ListUsersComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            users: [],
            message: null
        }
        this.refreshUsers = this.refreshUsers.bind(this)
        this.deleteUserClicked = this.deleteUserClicked.bind(this)
        this.updateUserClicked = this.updateUserClicked.bind(this)
        this.addUserClicked = this.addUserClicked.bind(this)
        // this.exitClicked = this.exitClicked.bind(this)
    }

    componentDidMount() {
        this.refreshUsers();
    }

    // exitClicked(){
    //     // console.log('exit clicked')
    //     this.props.history.push('/')
    // }
    
    refreshUsers() {
        UserDataService.retrieveAllUsers(USERNAME).then(
            response => {
                console.log(response);
                this.setState({users: response.data})
            }
        )
    }

    deleteUserClicked(id, name) {
        UserDataService.deleteUser(USERNAME, id).then(
            response => {
                this.setState({message: `Remoção do usuário ${name} bem sucedida`})
                this.refreshUsers()
            }
        )
    }

    updateUserClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/users/${id}`)
    }

    addUserClicked() {
        this.props.history.push(`/users/-1`)
    }

    render() {
        console.log('render')
        return (
            <div className="container">
                <h2>Usuários</h2> 
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table"> 
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome de Usuário</th>
                                <th>Nome</th>
                                <th>Privilégio</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map(
                                    user => 
                                        <tr key={user.id}>
                                            <td>{user.id}</td>
                                            <td>{user.username}</td>
                                            <td>{user.name}</td>
                                            <td>{user.role}</td>
                                            <td>
                                                <button className="btn btn-warning" onClick={() => this.updateUserClicked(user.id)}>Visualizar</button>
                                                <button className="btn btn-danger" onClick={() => this.deleteUserClicked(user.id, user.name)}>Apagar</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-primary" onClick={this.addUserClicked}>Novo Usuário</button>
                        {/* <button className="btn btn-default" onClick={this.exitClicked}>Sair</button> */}
                    </div>
                </div>
            </div>
        )
    }
}

export default ListUsersComponent
