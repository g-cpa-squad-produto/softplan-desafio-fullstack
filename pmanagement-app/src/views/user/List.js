import React, { Component } from 'react';
import api from '../../services/api';
import MainLayout from '../partials/main';
import { Container, Row, Col, Card, CardHeader, CardBody } from "shards-react";
import PageTitle from '../partials/pageTitle';
import { Link } from 'react-router-dom';

export default class UserList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users: []
        };

        api.get('/api/users/all')
            .then(res => {
                const users = res.data;
                this.setState({ users });
            });
    }

    onClick(event) {
        event.preventDefault();
    }

    render() {
        return (
            <div className="app align-center">
                <MainLayout>
                    <Container fluid className="main-content-container">
                        <Row noGutters className="page-header">
                            <PageTitle title="List" subtitle="Users" />
                        </Row>
                        <Row>
                            <Col>
                                <Card small>
                                    <CardHeader className="border-bottom">
                                        <Row>
                                            <h3 className="pull-left">Users</h3>
                                            <Link className="pull-right btn btn-primary" to="/users/form">Form</Link>
                                        </Row>
                                    </CardHeader>
                                    <CardBody>
                                        <table className="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">
                                                        #
                                                    </th>
                                                    <th scope="col">
                                                        Name
                                                    </th>
                                                    <th scope="col">
                                                        Username
                                                    </th>
                                                    <th scope="col">
                                                        Mail
                                                    </th>
                                                    <th scope="col">
                                                        Status
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {this.state.users.map(user => (
                                                    <tr>
                                                        <td>{user.id}</td>
                                                        <td>{user.name}</td>
                                                        <td>{user.username}</td>
                                                        <td>{user.email}</td>
                                                        <td>{user.status ? 'Enabled' : 'Disabled'}</td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>
                                    </CardBody>
                                </Card>
                            </Col>
                        </Row>
                    </Container>
                </MainLayout>
            </div>
        );
    }
}