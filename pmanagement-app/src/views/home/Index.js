import React, { Component } from 'react';
import MainLayout from '../partials/main';
import { Container, Row } from "shards-react";
import PageTitle from '../partials/pageTitle';

export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = props.state;
    }

    render() {
        return (
            <div className="app align-center">
                <MainLayout>
                    <Container fluid className="main-content-container">
                        <Row noGutters className="page-header">
                            <PageTitle title="Welcome" subtitle="Dashboard" />
                        </Row>
                    </Container>
                </MainLayout>
            </div>
        );
    }
}