import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavBar from './AppNavbar';
import {Link} from 'react-router-dom';

class UserList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            isLoading: true
        }
    }

    componentDidMount() {
        this.setState({isLoading: true});
        fetch('/api/users')
            .then(response => response.json())
            .then(data => this.setState({users: data, isLoading: false}));
    }

    remove = async (id) => {
        if(window.confirm("Deseja realmente apagar o usuário "+id+" :")) {
            await fetch(`/api/users/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(() => {
                let updatedUsers = [...this.state.users].filter(i => i.id !== id);
                this.setState({users: updatedUsers});
            })
        }
    }

    render() {
        const {users, isLoading} = this.state;
        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }

        const userList = users.map(user => {
            return (<tr key={user.id}>
                <td>{user.id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{user.name}</td>
                <td>{user.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary"><Link className="text-white" tag={Link} to={"/users/" + user.id}>Editar</Link></Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(user.id)}>Remover</Button>
                    </ButtonGroup>
                </td>
            </tr>);
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success">
                            <Link className="text-white" tag={Link} to="/users/new">Novo Usuário</Link>
                        </Button>
                    </div>
                    <h3>Usuários</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">#</th>
                            <th width="40%">Nome</th>
                            <th width="40%">Email</th>
                            <th width="10%">Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {userList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )

    }

}

export default UserList;
