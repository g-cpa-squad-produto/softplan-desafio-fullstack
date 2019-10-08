import React, {Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavBar from './AppNavbar';
import {Link} from 'react-router-dom';
import queryString from 'query-string';

class ProcessList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            process: [],
            isLoading: true
        }
    }

    componentDidMount() {
        this.setState({isLoading: true});
        let userId = 1;
        let params = queryString.parse(this.props.location.search);
        if(params && params.user){
            userId = params.user;
        }

        fetch('/api/processes/user/'+userId)
            .then(response => response.json())
            .then(data => this.setState({process: data, isLoading: false}));
    }

    remove = async (id) => {
        if(window.confirm("Deseja realmente apagar este processo?")){
            await fetch(`/api/processes/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(() => {
                let updatedProcess = [...this.state.process].filter(i => i.id !== id);
                this.setState({process: updatedProcess});
            })
        }
    }

    render() {
        const {process, isLoading} = this.state;
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
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/process/" + process.id}>Editar</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(process.id)}>Remover</Button>
                        <Button size="sm" color="secondary" tag={Link} to={"/process/users/" + process.id}>Atribuir Usuários</Button>
                    </ButtonGroup>
                </td>
            </tr>);
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/process/new">Novo processo</Button>
                    </div>
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
