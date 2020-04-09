import React, {Component} from 'react';
import ProcessDataService from '../service/ProcessDataService';


class ListProcessComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            process: [],
            message: null
        }
        this.refreshProcess = this.refreshProcess.bind(this)
        this.updateProcessClicked = this.updateProcessClicked.bind(this)
        this.addProcessClicked = this.addProcessClicked.bind(this)
        this.deleteProcessClicked = this.deleteProcessClicked.bind(this)
    }

    componentDidMount() {
        console.log('did mount - ListProcessComponent')
        this.refreshProcess();
    }
    
    refreshProcess() {
        console.log('refresh process - ListProcessComponent')
        ProcessDataService.retrieveAllProcess().then(
            response => {
                console.log(response + " - ListProcessComponent");
                this.setState({process: response.data})
            }
        )
    }

    updateProcessClicked(id) {
        console.log('update ' + id + ' - ListProcessComponent')
        this.props.history.push(`/process/${id}`)
    }

    addProcessClicked() {
        console.log('add process - ListProcessComponent')
        this.props.history.push(`/process/-1`)
    }

    deleteProcessClicked(id) {
        ProcessDataService.deleteProcess(id).then(
            response => {
                this.setState({message: `Remoção do processo ${id} bem sucedida`})
                this.refreshProcess()
            }
        )
    }

    render() {
        return (
            <div className="container">
                <h2>Processos</h2>
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table"> 
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Descrição</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.process.map(
                                    process => 
                                        <tr key={process.id}>
                                            <td>{process.id}</td>
                                            <td>{process.description}</td>
                                            <td>
                                                <button className="btn btn-warning" onClick={() => this.updateProcessClicked(process.id)}>Visualizar</button>
                                                {/* <button className="btn btn-danger" onClick={() => this.deleteProcessClicked(process.id)}>Apagar</button> */}
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-primary" onClick={this.addProcessClicked}>Novo Processo</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListProcessComponent