import React, {Component} from 'react';
import Form from '../../Components/Form';
import Row from '../../Components/Row';
import Input from '../../Components/Input';
import {messageAlert} from '../../Methods';
import Http from '../../Http';
import Alert from '../../Components/Alert';
import Container from '../../Components/Container';
import Loading from '../../Components/Loading'
import SelectMultiple from "../../Components/SelectMultiple";

class ProcessForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            title: 'CADASTRAR PROCESSO',
            process: {
                name: '',
                users: []
            },
            usersOptions: [],
            alerts: [],
            loading: true
        };

        this.save = this.save.bind(this);
        this.getUsers = this.getUsers.bind(this);
        this.change = this.change.bind(this);
    }

    async componentDidMount() {
        let { id } = this.props.match.params;

        await this.getUsers();

        if (id) {
            new Http().get('/process/' + id).send((res) => {
                let process = res.data;

                if (process.users.length > 0) {
                    process['users'] = process.users.map((user) => {
                        return user.id;
                    });
                }

                this.setState({
                    title: 'EDITAR PROCESSO',
                    process: process,
                    loading: false
                });
            })
        } else {
            this.setState({
                loading: false
            });
        }
    }

    getUsers() {
        new Http().get('/user/profile/6').send((res) => {
            this.setState({
                usersOptions: res.data
            });
        });
    }

    change(field, value) {
        let { process } = this.state;
        process[field] = value;
        this.setState({
            process: process
        })
    }

    changeSelect = (value) => {
        let { process } = this.state;
        process['users'] = value;
        this.setState({
            process: process
        });
    };

    save(e) {
        e.preventDefault();

        let { id } = this.props.match.params;
        let alerts = [];

        let process = this.state.process;

        process.users = process.users.map(user => {
            return user.value
        });

        if (this.state.process.name.length <= 0) alerts.push('Preencha o campo título');

        if (alerts.length <= 0) {
            if (id) {
                new Http().put('/process/' + id, process).send(async () => {
                    await messageAlert('Processo editado com sucesso', 'success');
                    await this.props.history.push('/processo');
                })
            } else {
                new Http().post('/process', process).send(async () => {
                    await messageAlert('Processo inserido com sucesso', 'success');
                    await this.props.history.push('/processo');
                })
            }
        } else {
            this.setState({ alerts: alerts })
        }
    }

    render() {
        let { title } = this.state;
        let alerts = this.state.alerts.map((alert, key) => <Alert key={key} message={alert} color="alert-danger" />);

        return (
            <Container>
                {this.state.loading ?
                    <Row class={'justify-content-center'}>
                        <Loading />
                    </Row> : null}
                <Row style={{display: this.state.loading ? 'none' : '' }}>
                    <Form title={title} col="col-sm-12" icon="fa fa-tag" urlCancel="/processo" onSubmit={this.save}>
                        {alerts}
                        <Row>
                            <Input name={'name'} value={this.state.process.name} change={this.change} label={'Título'} col={6} />
                            <SelectMultiple
                                name={'users'}
                                description={'name'}
                                label={'Usuários para parecer'}
                                options={this.state.usersOptions}
                                value={this.state.process.users}
                                change={this.changeSelect}
                                placeholder={'Usuários'}
                                col={'6'}
                                multi={true} />
                        </Row>
                    </Form>
                </Row>
            </Container>
        );
    }
}

export default ProcessForm;