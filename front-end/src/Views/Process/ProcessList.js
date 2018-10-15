import React, {Component} from 'react';
import Container from '../../Components/Container';
import Row from '../../Components/Row';
import CardSimple from '../../Components/CardSimple';
import Http from '../../Http';
import {Link} from 'react-router-dom';
import Loading from '../../Components/Loading'
import Col from "../../Components/Col";
import Input from "../../Components/Input";
import {hasPermission} from "../../Methods";
import Can from "../../Components/Can";

class ProcessList extends Component {
    constructor() {
        super();
        this.state = {
            processList: [],
            processFilter: [],
            search: "",
            loading: true
        };

        this.mount = this.mount.bind(this);
        this.search = this.search.bind(this);
    }

    componentDidMount() {
        const url = hasPermission("ROLE_PROCESS_GRANT_ALL") ? "/process" : "/process/user";
        new Http().get(url).send((res) => {
            this.setState({
                processList: res.data,
                processFilter: res.data,
                loading: false
            });
        });
    }

    search(field, value) {
        let valueOld = value;
        value = value.trim().toLowerCase();

        let processList = this.state.processFilter.filter((process) => {
            return process.name.toLowerCase().includes(value);
        });

        this.setState({
            processList: processList,
            search: valueOld
        })
    }

    mount() {
        if (this.state.processList.length > 0) {
            return this.state.processList.map((process, key) => {
                return <tr key={key}>
                    <td>{process.name}</td>
                    <td>
                        <div className="table-data-feature">
                            <Can roles={'ROLE_PROCESS_GRANT_ALL'}>
                                <Link to={'/processo/editar/' + process.id} className="item" data-toggle="tooltip" data-placement="top" title="Editar" data-original-title="Edit">
                                    <i className="zmdi zmdi-edit"></i>
                                </Link>
                            </Can>
                            <Can roles={'ROLE_SIGHT_GRANT_ALL'}>
                                <Link to={'/parecer/processo/' + process.id} className="item" data-toggle="tooltip" data-placement="top" title="Parecer" data-original-title="Parecer">
                                    <i className="zmdi zmdi-eye"></i>
                                </Link>
                            </Can>
                        </div>
                    </td>
                </tr>
            });
        }

        return false;
    }

    render() {
        const content = <Row class={"ml-2"}>
            <Col col={12}>
                <Input label={"Pesquisa"} value={this.state.search} change={this.search} />
            </Col>
        </Row>;

        return (
            <Container>
                {this.state.loading ?
                    <Row class={'justify-content-center'}>
                        <Loading />
                    </Row> : null}
                <Row style={{display: this.state.loading ? 'none' : '' }}>
                    <CardSimple content={content} title={'LISTA DE PROCESSOS'} col="col-md-12" icon={'fa fa-tags'} urlNew={'/processo/cadastro'}>
                        <table className="table">
                            <thead>
                                <tr>
                                    <td>Título</td>
                                    <td />
                                </tr>
                            </thead>
                            <tbody>
                                {this.mount()}
                            </tbody>
                        </table>
                    </CardSimple>
                </Row>
            </Container>
        );
    }
}

export default ProcessList;