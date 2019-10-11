import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table, Alert} from 'reactstrap';
import {Link, Redirect} from 'react-router-dom';
import {manipulateData} from "../../utils/api";
import queryString from "query-string";

class ProcessList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            process: [],
            isLoading: true,
            currentUser: this.props.currentUser,
            params: queryString.parse(this.props.location.search)
        };
    }

    componentDidMount() {
        if (this.state.currentUser) {
            this.setState({isLoading: true});
            let userId = this.state.currentUser.id;
            let data = {
                url: '/api/processes/user/' + userId,
                method: 'GET'
            };
            manipulateData(data).then(data => {
                let response = data;
                response.map(process => {
                    console.log(process);
                });
                return this.setState({process: data, isLoading: false})
            });
        }
    }

    getOpinion = (idProcess) => {
        let pareceres = [];
        let dataOpinion = {
            url: "/api/processes/" + idProcess + "/opinions",
            method: "GET"
        };
        manipulateData(dataOpinion).then(data => pareceres = data);
        return [
            {text: idProcess},
            {text: "teste2"},
            {text: "teste3"},
            {text: "teste4"}
        ];
    };

    remove = async (id) => {
        if (window.confirm("Deseja realmente apagar este processo?")) {
            let data = {
                url: '/api/processes/' + id,
                method: 'DELETE'
            }
            manipulateData(data).then(() => {
                let updatedProcess = [...this.state.process].filter(i => i.id !== id);
                this.setState({process: updatedProcess});
            });
        }
    };

    render() {
        const {process, isLoading, currentUser, params} = this.state;

        if (!currentUser || !currentUser.type) {
            return <Redirect to="/"/>
        }

        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }

        const processList = Array.isArray(process) && process.map(process => {
            const usuarios = process.userSystems;
            const pareceres = this.getOpinion(process.id);

            return (<tr key={process.id}>
                <td>{process.code}</td>
                <td style={{whiteSpace: 'nowrap'}}>{process.creator.name}</td>
                <td>{Array.isArray(usuarios) && usuarios.map(user => {
                    return (<div key={user.id}>{user.name}</div>);
                })}</td>
                <td>{process.description}</td>
                <td>
                    {pareceres && Array.isArray(pareceres) && pareceres.map((parecer,index) => {
                        return (
                            <div key={index}>
                                {parecer.text}
                            </div>
                        )
                    })}
                </td>
                <td>
                    {
                        currentUser.type &&
                        (currentUser.type === 'ADMIN' || currentUser.type === 'TRIADOR') &&
                        <ButtonGroup>
                            <Button size="sm" color="primary"><Link className="text-white" tag={Link}
                                                                    to={"/process/" + process.id}>Editar</Link></Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(process.id)}>Remover</Button>
                        </ButtonGroup>
                    }
                    {
                        currentUser.type &&
                        currentUser.type === 'FINALIZADOR' &&
                        <ButtonGroup>
                            <Button size="sm" color="info"><Link className="text-white" tag={Link}
                                                                 to={"/process/" + process.id}>Escrever
                                parecer</Link></Button>
                        </ButtonGroup>
                    }
                </td>
            </tr>);
        });

        return (
            <div>
                <Container fluid>
                    {
                        params && params.edit && params.edit === 'success' &&
                        <Alert color="success">Alteração registrada com sucesso!</Alert>
                    }
                    {
                        params && params.edit && params.edit === 'error' &&
                        <Alert color="danger">Problemas ao alterar registro!</Alert>
                    }
                    {currentUser.type &&
                    (currentUser.type === 'ADMIN' || currentUser.type === 'TRIADOR') &&
                    <div className="float-right">
                        <Button color="success">
                            <Link className="text-white" tag={Link} to="/process/new">Novo processo</Link>
                        </Button>
                    </div>
                    }
                    <h3>Processos</h3>
                    <Table responsive className="mt-4" style={{fontSize: 13}}>
                        <thead>
                        <tr>
                            <th width="5%">Código</th>
                            <th width="20%">Criador</th>
                            <th width="20%">Usuários</th>
                            <th width="20%">Descrição</th>
                            <th width="20%">Parecer</th>
                            <th width="10%">Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {processList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )

    }

}

export default ProcessList;
