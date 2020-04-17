import React, { Component } from 'react'
import Header from './header/Header'
import User from './user/User'
import EditUser from './user/EditUser'
import Process from './process/Process'
import EditProcess from './process/EditProcess'
import { Switch, Route } from 'react-router-dom'
import './Main.css'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import { Redirect } from 'react-router-dom'

export default class Main extends Component {

    render() {
        return (
            <div>
                <Header />
                <Container>
                    <Row className="justify-content-md-center">
                        <Col md="10">
                            <div className="content-container">
                                <Switch>
                                    <Route path={`${this.props.match.path}/user`} exact={true} component={User} />
                                    <Route path={`${this.props.match.path}/user/:userId`} exact={true} component={EditUser} />
                                    <Route path={`${this.props.match.path}/process`} exact={true} component={Process} />
                                    <Route path={`${this.props.match.path}/process/:processId`} exact={true} component={EditProcess} />
                                    <Redirect from="*" to="login" />
                                </Switch>
                            </div>
                        </Col>
                    </Row>
                </Container>
            </div>
        )
    }
}