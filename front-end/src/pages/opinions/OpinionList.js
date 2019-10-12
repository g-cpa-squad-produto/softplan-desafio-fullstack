import React, {Component} from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import { Container, Card, CardHeader, CardBody} from 'reactstrap';
import {getInfoAboutProcess} from "../../utils/processFunctions";

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
            getInfoAboutProcess(idProcess, this);
        }
    }

    render() {
        const {isLoading, item, currentUser} = this.state;

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
                    <div className="clearfix"/>
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
