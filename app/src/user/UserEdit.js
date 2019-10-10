import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {manipulateData} from "../utils/api";

class UserEdit extends Component {

    emptyItem = {
        name: '',
        email: '',
        type: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
            currentUser: this.props.currentUser
        };
    }

    componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            let data = {
                url: "/api/users/" + this.props.match.params.id,
                method: 'GET'
            };
            manipulateData(data).then(data => this.setState({item: data, isLoading: false}));
        }
    }

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const {item} = this.state;

        let endpoint = '/api/users';
        if (item && item.id) {
            endpoint += "/" + item.id;
        }

        if (item.type) {
            item.type = item.type.toUpperCase();
        }

        let data = {
            url: endpoint,
            method: (item.id) ? 'PUT' : 'POST',
            body: JSON.stringify(item)
        }

        manipulateData(data);
        this.setState({redirect: "/users"});
    }

    render() {
        const {item, currentUser} = this.state;

        if (!currentUser || !currentUser.type || currentUser.type !== 'ADMIN') {
            return <Redirect to="/"/>
        }

        const title = <h2>{item.id ? 'Editar Usuário' : 'Adicionar Usuário'}</h2>;

        return (
            <div>
                {this.state.redirect && <Redirect rerender={true} to={this.state.redirect}/>}
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Nome</Label>
                            <Input type="text" name="name" id="name" value={item.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="email">Email</Label>
                            <Input type="email" name="email" id="email" value={item.email || ''}
                                   onChange={this.handleChange} autoComplete="email"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="type">Tipo</Label>
                            <select name="type" id="type" className="form-control" onChange={this.handleChange}>
                                <option selected={item.type === 'TRIADOR'?'selected':''}>TRIADOR</option>
                                <option selected={item.type === 'FINALIZADOR'?'selected':''}>FINALIZADOR</option>
                            </select>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Salvar</Button>{' '}
                            <Link className="btn btn-secondary" to="/users">Cancelar</Link>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        );
    }
}

export default withRouter(UserEdit);
