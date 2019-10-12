import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import {Button, CustomInput, Container, Form, FormGroup, Input, Label, Card, CardHeader, CardBody} from 'reactstrap';
import {getInfoAboutProcess, saveProcess, saveOpinion} from "../../utils/processFunctions";
import {getUsersFinalizing} from "../../utils/userFunctions";

class ProcessEdit extends Component {

    emptyItem = {
        code: '',
        description: '',
        opinions: []
    };

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            item: this.emptyItem,
            usersInitials: [],
            currentUser: this.props.currentUser,
            redirect: false,
            opinionText: "",
            editing: props.match.params.id !== 'new',
            messageResult: "success"
        };
    }

    componentDidMount() {
        if (this.state.editing) {
            let idProcess = this.props.match.params.id;
            getInfoAboutProcess(idProcess, this);
        }
        getUsersFinalizing(this);
    }

    verifyCheck = (id) => {
        let founded = false;
        if (this.state.item.opinions) {
            this.state.item.opinions.map(opinion => {
                if (opinion.userSystem.id && opinion.userSystem.id === parseInt(id)) {
                    founded = true;
                }
            });
        }
        return founded;
    }

    handleCheckboxChange = (event) => {
        const target = event.target;
        const value = target.value;
        let {item} = this.state;
        if (!this.verifyCheck(value)) {
            item.opinions.push({userSystem: {id: parseInt(value)}});
        } else {
            item.opinions = item.opinions.filter(o => o.userSystem.id !== parseInt(value));
        }
        this.setState({item});
    }

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        if (name === 'opinion') {
            this.setState({opinionText: value});
        } else {
            let item = {...this.state.item};
            item[name] = value;
            this.setState({item});
        }
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const {item} = this.state;
        saveProcess(item,this);
    }

    handleSubmitOpinion = async (event) => {
        event.preventDefault();
        const idProcess = this.props.match.params.id;

        let theOpinion = this.state.item.opinions.filter(opinion => opinion.userSystem.id === this.state.currentUser.id);

        if(!theOpinion || !Array.isArray(theOpinion)){
            this.setState({redirect: "/process?edit=error"});
            return;
        }

        let opinionToSave = theOpinion[0];
        opinionToSave.text = this.state.opinionText;

        saveOpinion(opinionToSave, idProcess, this);
    }

    render() {
        const {isLoading, item, usersInitials, currentUser, opinionText} = this.state;

        if (!currentUser || !currentUser.type) {
            return <Redirect to="/"/>
        }

        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
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
                className="ml-4"/>);
        });

        if (currentUser && currentUser.type === 'FINALIZADOR') {
            return (
                <div>
                    {this.state.redirect && <Redirect to={this.state.redirect}/>}
                    <Container>
                        {title}
                        <br/>
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
                                <br/>
                                <FormGroup>
                                    <Label for="opinion">Parecer</Label>
                                    <Input type="textarea" required rows={10} name="opinion" id="opinion"
                                           value={opinionText || ''}
                                           onChange={this.handleChange}/>
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
                {this.state.redirect && <Redirect to={this.state.redirect}/>}
                <Container>
                    {title}
                    <div>
                        <Form onSubmit={this.handleSubmit} className="border-bottom pb-4 mb-4">
                            <FormGroup>
                                <Label for="code">Código</Label>
                                <Input type="text" required name="code" id="code" value={item.code || ''}
                                       onChange={this.handleChange} autoComplete="code"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="description">Descrição</Label>
                                <Input type="textarea" required rows={10} name="description" id="description"
                                       value={item.description || ''}
                                       onChange={this.handleChange}/>
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
