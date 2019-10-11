import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import {Button, CustomInput, Container, Form, FormGroup, Input, Label, Card, CardHeader, CardBody} from 'reactstrap';
import {manipulateData} from "../../utils/api";

class ProcessEdit extends Component {

    emptyItem = {
        name: '',
        description: '',
        opinion: '',
        userSystems: []
    };

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            item: this.emptyItem,
            usersInitials: [],
            currentUser: this.props.currentUser,
            redirect: false,
            opinions: [],
            opinionText: "",
            editando: props.match.params.id !== 'new',
            messageResult: "success"
        };
    }

    componentDidMount() {
        if (this.state.editando) {
            let idProcess = this.props.match.params.id;

            //PEGANDO INFORMAÇÕES DO PRCESSO
            let data = {
                url: "/api/processes/" + idProcess,
                method: "GET"
            };
            manipulateData(data).then(data => this.setState({item: data}));

            //PEGANDO PARECERES SOBRE ESTE PROCESSO
            let dataOpinion = {
                url: "/api/processes/" + idProcess + "/opinions",
                method: "GET"
            };
            manipulateData(dataOpinion).then(data => {
                let opinionText = "";
                data.map(opinion => {
                    if(this.state.currentUser.type === 'FINALIZADOR'){
                        let idUsuario = opinion.userSystemProcessId.userSystem.id;
                        if(idUsuario === this.state.currentUser.id) {
                            opinionText = opinion.text;
                        }
                    }
                    return null;
                });
                this.setState({
                    opinions: data,
                    opinionText: opinionText
                })
            });
        }

        //PEGANDO USUÁRIOS FINALIZADORES FINALIZADORES
        let data = {
            url: "/api/users/finalizadores",
            method: "GET"
        };
        manipulateData(data).then(data => this.setState({usersInitials: data, isLoading: false}));

    }

    verifyCheck = (id) => {
        let founded = false;
        if (this.state.item.userSystems) {
            this.state.item.userSystems.map(user => {
                if (founded) return null;
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
        let item = {...this.state.item};
        if (!this.verifyCheck(value)) {
            item.userSystems.push({id: value});
        } else {
            item.userSystems = item.userSystems.filter(u => u.id != value);
        }
        this.setState({item});
    }

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        if(name === 'opinion'){
            this.setState({opinionText: value});
        }else{
            let item = {...this.state.item};
            item[name] = value;
            this.setState({item});
        }
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const {item} = this.state;

        if (!item.creator) item.creator = {};

        let endpoint = '/api/processes';
        if (item && item.id) {
            endpoint += "/" + item.id;
        }

        if (item.type) {
            item.type = item.type.toUpperCase();
        }

        if(!this.state.editando) {
            item.creator.id = this.state.currentUser.id;
        }

        let now = new Date();
        item.creationAt = now;

        let data = {
            url: endpoint,
            method: (item.id) ? 'PUT' : 'POST',
            body: JSON.stringify(item)
        }

        manipulateData(data)
            .then(data => { console.log(data) })
            .catch(error => this.setState({messageResult: "error"}))
            .finally(() => {
                this.setState({redirect: "/process?edit="+this.state.messageResult});
            });
    }

    handleSubmitOpinion = async (event) => {
        event.preventDefault();
        const idProcess = this.props.match.params.id;
        const data = {
            url: '/api/processes/' + idProcess + '/opinions',
            method: 'POST',
            body: JSON.stringify({
                text: this.state.opinionText,
                createdAt: new Date(),
                idProcess: idProcess,
                idUser: this.state.currentUser.id
            })
        }
        manipulateData(data)
            .then(data => { console.log(data) })
            .catch(error => this.setState({messageResult: "error"}))
            .finally(() => {
                this.setState({redirect: "/process?edit="+this.state.messageResult});
            });
    }

    render() {
        const {isLoading, item, usersInitials, currentUser, opinions, opinionText} = this.state;

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

        const pareceres = opinions.map((opinion,index) => {
            return(
                <Card key={index} className="mt-4">
                    <CardHeader>Parecer de {opinion.userSystemProcessId.userSystem.name}</CardHeader>
                    <CardBody>{opinion.text || ''}</CardBody>
                </Card>
            );
        })

        if (currentUser && currentUser.type === 'FINALIZADOR') {
            return (
                <div>
                    {this.state.redirect && <Redirect rerender={true} to={this.state.redirect}/>}
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
                {this.state.redirect && <Redirect rerender={true} to={this.state.redirect}/>}
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
                        <div className="mb-4">{pareceres}</div>
                    </div>
                </Container>
            </div>
        );

    }
}

export default withRouter(ProcessEdit);
