import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table, Alert} from 'reactstrap';
import {Link, Redirect} from 'react-router-dom';
import queryString from "query-string";
import {removeProcess, getProcessesByUser} from "../../utils/processFunctions";

class ProcessList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            process: [],
            isLoading: true,
            currentUser: this.props.currentUser,
            params: queryString.parse(this.props.location.search),
            opinions: []
        };
    }

    componentDidMount() {
        if (this.state.currentUser) {
            this.setState({isLoading: true});
            let userId = this.state.currentUser.id;
            getProcessesByUser(userId, this);
        }
    }

    remove = async (id) => {
        if (window.confirm("Deseja realmente apagar este processo?")) {
            removeProcess(id, this);
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

            let description = process.description;
            let sizeDescription = 200;
            if (description.length > sizeDescription) {
                description = description.substring(0, sizeDescription) + "...";
            }

            const opinions = process.opinions;
            let countSentOpinions = 0;
            opinions.map(opinion => {
                if (opinion.text && opinion.text.length > 0) {
                    countSentOpinions++;
                }
            });

            return (<tr key={process.id}>
                <td>{process.code}</td>
                <td style={{whiteSpace: 'nowrap'}}>{process.creator.name}</td>
                <td>{
                    Array.isArray(opinions) &&
                    <ul>
                        {
                            opinions.map(opinion => {
                                return (<li key={opinion.userSystem.id}>{opinion.userSystem.name}</li>)
                            })
                        }
                    </ul>
                }</td>
                <td>{description}</td>
                <td>{countSentOpinions}</td>
                <td>
                    {
                        currentUser.type &&
                        (currentUser.type === 'ADMIN' || currentUser.type === 'TRIADOR') &&
                        <ButtonGroup>
                            <Button size="sm" color="primary"><Link className="text-white" tag={Link}
                                                                    to={"/process/" + process.id}>Editar</Link></Button>
                            <Button size="sm" color="danger"
                                    onClick={() => this.remove(process.id)}>Remover</Button>
                            {countSentOpinions > 0 &&
                            <Button size="sm" color="info">
                                <Link className="text-white" tag={Link}
                                      to={"/opinions/process/" + process.id}>Pareceres</Link>
                            </Button>
                            }
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
                            <th width="20%">Pareceres</th>
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
