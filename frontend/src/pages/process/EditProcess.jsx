import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { Row, Col } from 'react-bootstrap'
import './Process.css'

import { getProcessById, saveProcess, getFinUsers } from '../../services/service'

export default class EditProcess extends Component {

    state = {
        title: '',
        description: '',
        status: 'PENDING',
        assign: '',
        listOfUsers: []
    }

    componentDidMount() {
        this.currentId = this.props.match.params.processId;
        if(parseInt(this.currentId)) {
            this.getProcessById(this.currentId)
        }
        this.getFinUsers()
    }

    getFinUsers = () => {
        getFinUsers().then(response => {
            this.setState({listOfUsers: response.data || []})
        })
    }

    getProcessById = (processId) => {
        getProcessById(processId).then(response => {
            const data = response.data;
            this.setState({
                id: data.id,
                title: data.title,
                description: data.description,
                status: data.status,
                assign: data.assign ? data.assign.id : ''
            })
        })
    }

    backToProcessList = () => {
        this.props.history.push('/api/process/')
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value })
    }

    saveProcess = () => {
        const params = {
            id: this.state.id,
            title: this.state.title,
            description: this.state.description,
            status: this.state.status,
            assign: this.state.assign !== '' ? { id: this.state.assign }: null
        }
        saveProcess(params).then(() => {
            this.backToProcessList()
        })
    }

    render() {
        return (
            <div >
                <div className="padding-container">
                    <div className="row">
                        <div className="col">
                            <h2>{!parseInt(this.currentId) ? 'Novo processo' :  'Editando processo'}</h2>
                        </div>
                    </div>

                    <Form >
                        <Form.Group>
                            <Form.Label>Título</Form.Label>
                            <Form.Control type="text" name="title" onChange={this.onChange} value={this.state.title}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Descrição</Form.Label>
                            <Form.Control type="text" name="description" onChange={this.onChange} value={this.state.description}/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Responsável</Form.Label>
                            <Form.Control as="select" name="assign" value={this.state.assign} onChange={this.onChange}>
                                <option value="">Sem responsável</option>
                                {this.state.listOfUsers.map((user) =>
                                    <option key={user.id} value={user.id}>{user.username}</option>
                                )}
                            </Form.Control>
                        </Form.Group>

                        <Row className="mt-5">
                            <Col>
                                <Button variant="secondary" onClick={this.backToProcessList}>
                                    Cancelar
                                </Button>
                            </Col>
                            <Col>
                                <Button variant="primary" onClick={this.saveProcess} className="float-right">
                                    Salvar
                                </Button>
                            </Col>
                        </Row>
                    </Form>
                </div>
            </div>
        )
    }
}