import React, {Component} from 'react';
import Container from '../../Components/Container';
import Row from '../../Components/Row';
import CardSimple from '../../Components/CardSimple';
import Http from '../../Http';
import {Link} from 'react-router-dom';
import Loading from '../../Components/Loading'
import Col from "../../Components/Col";
import Input from "../../Components/Input";
import {messageAlert} from "../../Methods";

class UserList extends Component {
    constructor() {
        super();
        this.state = {
            users: [],
            userFilter: [],
            search: "",
            loading: true
        };

        this.mount = this.mount.bind(this);
        this.search = this.search.bind(this);
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        new Http().get('/user').send((res) => {
            this.setState({
                users: res.data,
                userFilter: res.data,
                loading: false
            });
        });
    }

    remove(user) {
        if (user) {
            new Http().delete('/user/' + user.id).send(async () => {
                let users = this.state.users;
                this.setState({
                    users: users.filter(e => e !== user),
                    userFilter: users.filter(e => e !== user),
                });
                await messageAlert('Usuário excluído com sucesso', 'success');
            });
        }
    }

    search(field, value) {
        let valueOld = value;
        value = value.trim().toLowerCase();

        let users = this.state.userFilter.filter((user) => {

            let hasName = user.name.toLowerCase().includes(value);
            let hasEmail = user.email.toLowerCase().includes(value);

            return hasEmail || hasName;
        });

        this.setState({
            users: users,
            search: valueOld
        })
    }

    mount() {
        if (this.state.users.length > 0) {
            return this.state.users.map((user, key) => {
                return <tr key={key}>
                    <td>{user.name}</td>
                    <td>{user.email}</td>
                    <td>
                        <div className="table-data-feature">
                            <Link to={'/usuario/editar/' + user.id} className="item" data-toggle="tooltip" data-placement="top" title="Editar" data-original-title="Edit">
                                <i className="zmdi zmdi-edit"></i>
                            </Link>
                            <button className="item" onClick={() => {this.remove(user)}} data-toggle="tooltip" data-placement="top" title="Excluir" data-original-title="Remove">
                                <i className="zmdi zmdi-delete"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            });
        }

        return false;
    }

    render() {
        const content = <Row class={"ml-2"}>
            <Col col={12}>
                <Input label={"Pesquisa"} value={this.state.search} change={this.search} />
            </Col>
        </Row>;

        return (
            <Container>
                {this.state.loading ?
                    <Row class={'justify-content-center'}>
                        <Loading />
                    </Row> : null}
                <Row style={{display: this.state.loading ? 'none' : '' }}>
                    <CardSimple content={content} title={'LISTA DE USUÁRIOS'} col="col-md-12" icon={'fa fa-tags'} urlNew={'/usuario/cadastro'}>
                        <table className="table">
                            <thead>
                                <tr>
                                    <td>Nome</td>
                                    <td>E-mail</td>
                                    <td />
                                </tr>
                            </thead>
                            <tbody>
                                {this.mount()}
                            </tbody>
                        </table>
                    </CardSimple>
                </Row>
            </Container>
        );
    }
}

export default UserList;