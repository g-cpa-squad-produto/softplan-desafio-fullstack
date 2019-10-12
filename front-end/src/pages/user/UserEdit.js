import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {getUser,saveUser} from "../../utils/userFunctions";

class UserEdit extends Component {

    emptyItem = {
        name: '',
        email: '',
        password: '',
        type: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            item: this.emptyItem,
            currentUser: this.props.currentUser,
            messageResult: "success"
        };
    }

    componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            getUser(this);
        }else{
            this.setState({isLoading: false});
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
        saveUser(item, this);
    }

    render() {
        const {item, currentUser, isLoading} = this.state;

        if (!currentUser || !currentUser.type || currentUser.type !== 'ADMIN') {
            return <Redirect to="/"/>
        }

        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }

        const title = <h2>{item.id ? 'Editar Usuário' : 'Adicionar Usuário'}</h2>;

        return (
            <div>
                {this.state.redirect && <Redirect to={this.state.redirect}/>}
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Nome</Label>
                            <Input type="text" name="name" required id="name" value={item.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="email">Email</Label>
                            <Input type="email" name="email" required id="email" value={item.email || ''}
                                   onChange={this.handleChange} autoComplete="email"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Senha</Label>
                            <Input type="password" name="password" id="password"
                                   onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="type">Tipo</Label>
                            <select value={item.type} name="type" id="type" required className="form-control" onChange={this.handleChange}>
                                <option></option>
                                <option>ADMIN</option>
                                <option>TRIADOR</option>
                                <option>FINALIZADOR</option>
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
