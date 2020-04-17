import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { Row, Col } from 'react-bootstrap'
import './User.css'

import { getAnUserById, saveUser } from '../../services/service'

export default class EditUser extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            name: '',
            lastName: '',
            role: 'ADMIN'
        }

        this.currentId = 0;
    }

    componentDidMount() {
        this.currentId = this.props.match.params.userId;
        if(parseInt(this.currentId)) {
            this.getUser(this.currentId)
        }
      }

    getUser = (userId) => {
        getAnUserById(userId).then(response => {
            this.setState(response.data)
        })
    }

    backToUserList = () => {
        this.props.history.push('/api/user/')
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value })
    }

    saveUser = () => {
        saveUser(this.state).then(() => {
            this.backToUserList()
        })
    }

    render() {
        return (
            <div >
                <div className="padding-container">
                    <div className="row">
                        <div className="col">
                            <h2>{!parseInt(this.currentId) ? 'Novo usuário' :  'Editando usuário'}</h2>
                        </div>
                    </div>

                    <Form >
                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Usuário</Form.Label>
                                    <Form.Control type="text" name="username" onChange={this.onChange} value={this.state.username}/>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Senha</Form.Label>
                                    <Form.Control type="password" name="password" onChange={this.onChange} value={this.state.password}/>
                                </Form.Group>
                            </Col>
                        </Row>

                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Nome</Form.Label>
                                    <Form.Control type="text" name="name" onChange={this.onChange} value={this.state.name}/>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Sobrenome</Form.Label>
                                    <Form.Control type="text" name="lastName" onChange={this.onChange} value={this.state.lastName}/>
                                </Form.Group>
                            </Col>
                        </Row>

                        <Form.Group>
                            <Form.Label>Permissão</Form.Label>
                            <Form.Control as="select" name="role" value={this.state.role} onChange={this.onChange}>
                                <option value="ADMIN">ADMIN</option>
                                <option value="TRIADOR">TRIADOR</option>
                                <option value="FINALIZADOR">FINALIZADOR</option>
                            </Form.Control>
                        </Form.Group>

                        <Row className="mt-5">
                            <Col>
                                <Button variant="secondary" onClick={this.backToUserList}>
                                    Cancelar
                                </Button>
                            </Col>
                            <Col>
                                <Button variant="primary" onClick={this.saveUser} className="float-right">
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