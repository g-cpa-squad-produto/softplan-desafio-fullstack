import React, {Component} from 'react';
import {Alert, Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link, Redirect } from 'react-router-dom';
import {manipulateData} from "../../utils/api";
import queryString from "query-string";

class UserList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            isLoading: true,
            currentUser: this.props.currentUser,
            params: queryString.parse(this.props.location.search)
        }
    }

    componentDidMount() {
        this.setState({isLoading: true});
        let data = {
            url: '/api/users',
            method: "GET"
        }
        manipulateData(data).then(data => this.setState({users: data, isLoading: false}));
    }

    remove = async (id) => {
        if (window.confirm("Deseja realmente apagar o usuário "+id+" :")) {
            let data = {
                url: '/api/users/' + id,
                method: 'DELETE'
            }
            manipulateData(data).then(() => {
                let updatedUsers = [...this.state.users].filter(i => i.id !== id);
                this.setState({users: updatedUsers});
            });
        }
    };

    render() {
        const {users, isLoading, currentUser, params} = this.state;

        if(!currentUser || !currentUser.type || currentUser.type !== 'ADMIN'){
            return <Redirect rerender={true} to="/" />
        }

        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }

        const userList = Array.isArray(users) && users.map(user => {
            if(user.type === 'ADMIN') return null;
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
                {
                    params && params.edit && params.edit === 'success' &&
                    <Alert color="success">Alteração registrada com sucesso!</Alert>
                }
                {
                    params && params.edit && params.edit === 'error' &&
                    <Alert color="danger">Problemas ao alterar registro!</Alert>
                }
                <Container fluid>
                    <div className="float-right">
                        <Button color="success">
                            <Link className="text-white" tag={Link} to="/users/new">Novo Usuário</Link>
                        </Button>
                    </div>
                    <h3>Usuários</h3>
                    <Table responsive className="mt-4">
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
