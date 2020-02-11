import React, { Component } from 'react';
import { Container, Row, Col, Card, CardHeader, CardBody, CardFooter } from "shards-react";
import MainLayout from '../partials/main';
import PageTitle from '../partials/pageTitle';
import { Link } from 'react-router-dom';

export default class UserForm extends Component {

    render() {
        return (
            <div className="app align-center">
                <MainLayout>
                    <Container fluid className="main-content-container">
                        <Row noGutters className="page-header">
                            <PageTitle title="Form" subtitle="Users" />
                        </Row>
                        <Row>
                            <Col>
                                <Card small>
                                    <CardHeader className="border-bottom">
                                        <h3>Users</h3>
                                    </CardHeader>
                                    <CardBody>
                                    </CardBody>
                                    <CardFooter>
                                        <Row>
                                            {/* <Col className="pull-right"> */}
                                                <Link className="btn btn-primary" to="/users/list">Back</Link>
                                                <Link className="btn btn-primary" to="/users/form">Save</Link>
                                            {/* </Col> */}
                                        </Row>
                                    </CardFooter>
                                </Card>
                            </Col>
                        </Row>
                    </Container>
                </MainLayout>
            </div>
        )
    }
}