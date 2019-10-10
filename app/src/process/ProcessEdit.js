import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import {Button, CustomInput, Container, Form, FormGroup, Input, Label, Card, CardHeader, CardBody } from 'reactstrap';
import {getProcess, getFinalizadores} from "../utils/api";

class ProcessEdit extends Component {

    emptyItem = {
        name: '',
        description: '',
        opinion: '',
        users: []
    };

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            item: this.emptyItem,
            usersInitials: [],
            currentUser: this.props.currentUser
        };
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            getProcess(this.props.match.params.id).then(data => this.setState({item: data, isLoading: false}));
        }
        getFinalizadores().then(data => this.setState({usersInitials: data, isLoading: false}));
    }

    verifyCheck = (id) => {
        let founded = false;
        if(this.state.item.userSystems) {
            this.state.item.userSystems.map(user => {
                if (founded) return;
                if (user.id && user.id == id) {
                    founded = true;
                }
            });
        }
        return founded;
    }

    handleCheckboxChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        if(!this.verifyCheck(value)){
            item.userSystems.push({id:value});
        }else{
            let updatedUsers = item.userSystems.filter(u => u.id != value);
            item.userSystems = updatedUsers;
        }
        this.setState({item});
    }

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const {item} = this.state;

        if(!item.creator) item.creator = {};

        let endpoint = '/api/processes';
        if (item && item.id) {
            endpoint += "/"+item.id;
        }

        if(item.type){
            item.type = item.type.toUpperCase();
        }

        item.creator.id = 1;
        let now = new Date();
        item.creationAt = now;

        await fetch(endpoint, {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/process');
    }

    handleSubmitOpinion = async (event) => {
        event.preventDefault();
        const idProcess = this.props.match.params.id;
        const data = {
            text: this.state.item.opinion,
            createdAt: new Date(),
            idProcess: idProcess,
            idUser: 3
        }

        let endpoint = '/api/processes/'+idProcess+'/opinion';
        await fetch(endpoint, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        });

        this.props.history.push('/process');
    }

    render() {
        const {item, usersInitials, currentUser} = this.state;
        if(!currentUser || !currentUser.type){
            return <Redirect to="/" />
        }

        const title = <h2>{item.id ? 'Editar Processo' : 'Adicionar Processo'}</h2>;

        const usersCheck = Array.isArray(usersInitials) && usersInitials.map(user => {
            return (<CustomInput
                key={user.id}
                type="checkbox"
                name="users[]"
                id={user.id}
                label={user.name}
                value={user.id}
                checked={this.verifyCheck(user.id)}
                onChange={this.handleCheckboxChange}
                className="ml-4" />);
        });

        if(currentUser && currentUser.type === 'FINALIZADOR'){
            return (
                <div>
                    <Container>
                        {title}
                        <br />
                        <div>
                            <Form onSubmit={this.handleSubmitOpinion}>
                                <Card className="mb-3">
                                    <CardHeader>Código</CardHeader>
                                    <CardBody>{item.code || ''}</CardBody>
                                </Card>
                                <Card className="mb-3">
                                    <CardHeader>Descrição</CardHeader>
                                    <CardBody>{item.description || ''}</CardBody>
                                </Card>
                                <br />
                                <FormGroup>
                                    <Label for="opinion">Parecer</Label>
                                    <Input type="textarea" required rows={10} name="opinion" id="opinion" value={item.opinion || ''}
                                           onChange={this.handleChange} />
                                </FormGroup>
                                <FormGroup>
                                    <Button color="primary" type="submit">Salvar</Button>{' '}
                                    <Link className="btn btn-secondary" to="/process">Cancelar</Link>
                                </FormGroup>
                            </Form>
                        </div>
                    </Container>
                </div>
            );
        }

        return (
            <div>
                <Container>
                    {title}
                    <div>
                        <Form onSubmit={this.handleSubmit}>
                            <FormGroup>
                                <Label for="code">Código</Label>
                                <Input type="text" required name="code" id="code" value={item.code || ''}
                                       onChange={this.handleChange} autoComplete="code"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="description">Descrição</Label>
                                <Input type="textarea" required rows={10} name="description" id="description" value={item.description || ''}
                                       onChange={this.handleChange} />
                            </FormGroup>
                            <FormGroup>
                                <Label>Usuários</Label>
                                <div className="row">
                                    {usersCheck}
                                </div>
                            </FormGroup><br/>
                            <FormGroup>
                                <Button color="primary" type="submit">Salvar</Button>{' '}
                                <Link className="btn btn-secondary" to="/process">Cancelar</Link>
                            </FormGroup>
                        </Form>
                    </div>
                </Container>
            </div>
        );

    }
}

export default withRouter(ProcessEdit);
