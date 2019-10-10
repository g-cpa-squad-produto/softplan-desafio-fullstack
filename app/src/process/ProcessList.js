import React, {Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import {Link, Redirect} from 'react-router-dom';
import {getProcessList, deleteProcess} from "../utils/api";

class ProcessList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            process: [],
            isLoading: true,
            currentUser: this.props.currentUser
        }
    }

    componentDidMount() {
        if(this.state.currentUser){
            this.setState({isLoading: true});
            let userId = this.state.currentUser.id;
            getProcessList(userId)
                .then(data => this.setState({process: data, isLoading: false}));
        }
    }

    remove = async (id) => {
        if(window.confirm("Deseja realmente apagar este processo?")){
            deleteProcess(id)
                .then(() => {
                    let updatedProcess = [...this.state.process].filter(i => i.id !== id);
                    this.setState({process: updatedProcess});
                });
        }
    }

    render() {
        const {process, isLoading, currentUser} = this.state;
        if(!currentUser || !currentUser.type){
            return <Redirect to="/" />
        }

        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }

        const processList = Array.isArray(process) && process.map(process => {
            return (<tr key={process.id}>
                <td>{process.code}</td>
                <td style={{whiteSpace: 'nowrap'}}>{process.creator.name}</td>
                <td>{process.description}</td>
                <td>
                    {
                        currentUser.type &&
                        (currentUser.type === 'ADMIN' || currentUser.type === 'TRIADOR') &&
                        <ButtonGroup>
                            <Button size="sm" color="primary"><Link className="text-white" tag={Link} to={"/process/" + process.id}>Editar</Link></Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(process.id)}>Remover</Button>
                        </ButtonGroup>
                    }
                    {
                        currentUser.type &&
                        currentUser.type === 'FINALIZADOR' &&
                        <ButtonGroup>
                            <Button size="sm" color="info"><Link className="text-white" tag={Link} to={"/process/" + process.id}>Escrever parecer</Link></Button>
                        </ButtonGroup>
                    }
                </td>
            </tr>);
        });

        return (
            <div>
                <Container fluid>
                    { currentUser.type &&
                    (currentUser.type === 'ADMIN' || currentUser.type === 'TRIADOR') &&
                        <div className="float-right">
                            <Button color="success">
                                <Link className="text-white" tag={Link} to="/process/new">Novo processo</Link>
                            </Button>
                        </div>
                    }
                    <h3>Processos</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="5%">Código</th>
                            <th width="20%">Criador</th>
                            <th width="20%">Descrição</th>
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
