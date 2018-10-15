import React, { Component } from "react";
import Row from "../Components/Row";
import Container from "../Components/Container";

class NotFound extends Component {

    render() {
        return (
            <Container>
                <Row>
                    <h1>Página não encontrada</h1>
                </Row>
            </Container>
        );
    }
}

export default NotFound;