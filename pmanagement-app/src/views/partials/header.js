import React from "react";
import { Container, Row, Col } from "shards-react";

import logo from '../../logo.svg';
import { Link } from 'react-router-dom';

const HeaderLayout = ({ contained }) => (
    <header className="main-header">
        <Container fluid={contained}>
            <Row >
                <span>
                    <Link to="/">
                        <img src={logo} className="App-logo" alt="logo" height="50" />
                    </Link>
                </span>
                <span>
                    <Link to="/">Home</Link>
                </span>
                <span>
                    <Link to="/users/list">Users</Link>
                </span>
                <span>
                    <Link to="/logout">Logout</Link>
                </span>
            </Row>
        </Container>
    </header>
);

export default HeaderLayout;