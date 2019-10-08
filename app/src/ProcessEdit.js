import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';

class ProcessEdit extends Component {

    emptyItem = {
        name: '',
        description: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const process = await (await fetch(`/api/processes/${this.props.match.params.id}`)).json();
            this.setState({item: process});
        }
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
        item.creationTime = now;

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

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Editar Processo' : 'Adicionar Processo'}</h2>;

        return (
            <div>
                <AppNavbar/>
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="code">Código</Label>
                            <Input type="text" name="code" id="code" value={item.code || ''}
                                   onChange={this.handleChange} autoComplete="code"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="name">Nome</Label>
                            <Input type="text" name="name" id="name" value={item.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="description">Descrição</Label>
                            <Input type="textarea" name="description" id="description" value={item.description || ''}
                                   onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Salvar</Button>{' '}
                            <Button color="secondary" tag={Link} to="/users">Cancelar</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        );
    }
}

export default withRouter(ProcessEdit);
