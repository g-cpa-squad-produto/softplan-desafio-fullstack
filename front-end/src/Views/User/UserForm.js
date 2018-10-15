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

class userForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            title: 'CADASTRAR USUÁRIO',
            user: {
                name: '',
                email: '',
                username: '',
                password: '',
                profiles: []
            },
            profilesOptions: [],
            alerts: [],
            loading: true
        };

        this.save = this.save.bind(this);
        this.getProfiles = this.getProfiles.bind(this);
        this.change = this.change.bind(this);
    }

    async componentDidMount() {
        let { id } = this.props.match.params;

        await this.getProfiles();

        if (id) {
            new Http().get('/user/' + id).send((res) => {
                let user = res.data;

                if (user.profiles.length > 0) {
                    user['profiles'] =  user.profiles.map((profile) => {
                        return {
                            value: profile.id,
                            label: profile.name
                        }
                    });
                }

                this.setState({
                    title: 'EDITAR USUÁRIO',
                    user: user,
                    loading: false
                });
            })
        } else {
            this.setState({
                loading: false
            });
        }
    }

    getProfiles() {
        new Http().get('/profile').send((res) => {
            this.setState({
                profilesOptions: res.data
            });
        });
    }

    change(field, value) {
        let { user } = this.state;
        user[field] = value;
        this.setState({
            user: user
        })
    }

    changeSelect = (value) => {
        let { user } = this.state;
        user['profiles'] = value;
        this.setState({
            user: user
        });
    };

    save(e) {
        e.preventDefault();
        let { id } = this.props.match.params;
        let alerts = [];

        let user = this.state.user;

        user.profiles = user.profiles.map(profile => {
            return profile.value ? profile.value : profile.id;
        });

        if (this.state.user.name.length <= 0) alerts.push('Preencha o campo nome');
        if (this.state.user.email.length <= 0) alerts.push('Preencha o campo e-mail');
        if (!id && this.state.user.password.length <= 0) alerts.push('Preencha o campo senha');

        if (alerts.length <= 0) {
            if (id) {
                new Http().put('/user/' + id, user).send(async () => {
                    await messageAlert('Usuário editado com sucesso', 'success');
                    await this.props.history.push('/usuario');
                })
            } else {
                new Http().post('/user', user).send(async () => {
                    await messageAlert('Usuário inserido com sucesso', 'success');
                    await this.props.history.push('/usuario');
                })
            }
        } else {
            this.setState({ alerts: alerts })
        }
    }

    render() {
        let { title } = this.state;
        let alerts = this.state.alerts.map((alert, key) => <Alert key={key} message={alert} color="alert-danger" />);
        let { id } = this.props.match.params;

        return (
            <Container>
                {this.state.loading ?
                    <Row class={'justify-content-center'}>
                        <Loading />
                    </Row> : null}
                <Row style={{display: this.state.loading ? 'none' : '' }}>
                    <Form title={title} col="col-sm-12" icon="fa fa-tag" urlCancel="/usuario" onSubmit={this.save}>
                        {alerts}
                        <Row>
                            <Input name={'name'} value={this.state.user.name} change={this.change} label={'Nome'} col={6} />
                        </Row>
                        <Row>
                            <Input name={'email'} value={this.state.user.email} change={this.change} label={'E-mail'} col={6} />
                        </Row>
                        {
                            !id &&
                            <Row>
                                <Input name={'password'} value={this.state.user.password} type={'text'} change={this.change} label={'Senha'} col={6} />
                            </Row>
                        }

                        <Row>
                            <SelectMultiple
                                name={'profiles'}
                                description={'name'}
                                label={'Perfis'}
                                options={this.state.profilesOptions}
                                value={this.state.user.profiles}
                                change={this.changeSelect}
                                placeholder={'Perfis'}
                                col={'6'}
                                multi={true} />
                        </Row>
                    </Form>
                </Row>
            </Container>
        );
    }
}

export default userForm;