import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {Button, Container, Row, Form, FormGroup, Input, Label, Card, CardHeader, CardBody} from 'reactstrap';
import {ACCESS_TOKEN} from '../utils/constants';
import {login} from "../utils/api";

class Login extends Component {

    state = {
        isLoading: false,
        error: null
    };

    handleSubmit = (event) => {
        event.preventDefault();
        this.setState({isLoading: true});
        const loginData = {
            email: this.refs.email.value,
            password: this.refs.password.value
        };
        login(loginData).then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            this.props.onLogin();
        }).catch(error => {
            if(error.status === 401) {
                alert('Email/senha incorretos. Tente novamente.');
            } else {
                alert(error.message || 'Desculpe! Alguma coisa está errada. Tente novamente!');
            }
        });
        this.setState({isLoading: false});
    }

    render() {

        if (this.state.isLoading) {
            return <div className="text-center m-4">
                <p>Efetuando Login...</p>
            </div>
        }

        return (
            <Container>
                <Row className="justify-content-center">
                    <Card className="col-4 p-0">
                        <CardHeader>
                            <h2>Login</h2>
                        </CardHeader>
                        <CardBody>
                            <Form onSubmit={this.handleSubmit}>
                                <FormGroup>
                                    <Label for="email">Email</Label>
                                    <input type="email" ref="email" id="email" required className="form-control"/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="password">Senha</Label>
                                    <input type="password" ref="password" id="password" required
                                           className="form-control"/>
                                </FormGroup>
                                <FormGroup>
                                    <Button color="primary" type="submit">Login</Button>
                                </FormGroup>
                            </Form>
                        </CardBody>
                    </Card>
                </Row>
            </Container>
        );

    }
}

export default withRouter(Login);
