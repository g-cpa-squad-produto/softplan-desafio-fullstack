import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';

class UserEdit extends Component {

    emptyItem = {
        name: '',
        email: '',
        type: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const user = await (await fetch(`/api/users/${this.props.match.params.id}`)).json();
            this.setState({item: user});
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

        await fetch(endpoint, {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/users');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Editar Usuário' : 'Adicionar Usuário'}</h2>;

        return (
            <div>
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
                            <Input type="select" defaultValue={item.type} name="type" id="type" onChange={this.handleChange}>
                                <option>Triador</option>
                                <option>Finalizador</option>
                            </Input>
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
