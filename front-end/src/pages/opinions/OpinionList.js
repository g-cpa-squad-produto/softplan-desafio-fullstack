import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import {Button, CustomInput, Container, Form, FormGroup, Input, Label, Card, CardHeader, CardBody} from 'reactstrap';
import {manipulateData} from "../../utils/api";

class OpinionList extends Component {

    emptyItem = {
        name: '',
        description: '',
        opinions: []
    };

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            item: this.emptyItem,
            usersInitials: [],
            currentUser: this.props.currentUser,
            redirect: false,
            opinionText: "",
            editing: props.match.params.id !== 'new',
            messageResult: "success"
        };
    }

    componentDidMount() {
        if (this.state.editing) {
            let idProcess = this.props.match.params.id;

            //PEGANDO INFORMAÇÕES DO PRCESSO
            let data = {
                url: "/processes/" + idProcess,
                method: "GET"
            };
            manipulateData(data).then(data => {
                data.opinions.map(opinion => {
                    //CASO FINZALIZADOR, COLOCAR PARECER DELE NO TEXTAREA
                    if (this.state.currentUser.type === 'FINALIZADOR') {
                        let idUsuario = opinion.userSystem.id;
                        if (idUsuario === this.state.currentUser.id) {
                            return this.setState({opinionText: opinion.text})
                        }
                    }
                    return null;
                });
                let opinions = data.opinions;
                return this.setState({item: data})
            });

        }

        //PEGANDO USUÁRIOS FINALIZADORES PARA MOSTRAR OS CHECKBOXES
        let data = {
            url: "/users/finalizadores",
            method: "GET"
        };
        manipulateData(data).then(data => this.setState({usersInitials: data, isLoading: false}));

    }

    render() {
        const {isLoading, item, currentUser } = this.state;

        if (!currentUser || !currentUser.type) {
            return <Redirect to="/"/>
        }

        if (isLoading) {
            return <div className="text-center m-4">
                <p>Carregando...</p>
            </div>
        }

        const pareceres = item.opinions.map((opinion, index) => {
            if (!opinion.text || opinion.text === "") return "";
            return (
                <Card key={index} className="mt-4">
                    <CardHeader>Parecer de {opinion.userSystem.name}</CardHeader>
                    <CardBody>{opinion.text}</CardBody>
                </Card>
            );
        });

        return (
            <div>
                {this.state.redirect && <Redirect to={this.state.redirect}/>}
                <Container>
                    <h2 className="float-left">Pareceres do processo {item.code || ''}</h2>
                    <Link className="btn btn-secondary float-right" to="/process">Voltar</Link>
                    <div className="clearfix" />
                    <div className="mt-3">
                        <p>{item.description || ''}</p>
                        <div className="border-top mt-5 pt-3">
                            <div className="mb-4">{pareceres}</div>
                        </div>
                    </div>
                </Container>
            </div>
        );
    }
}

export default withRouter(OpinionList);
