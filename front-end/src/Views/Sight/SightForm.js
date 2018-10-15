import React, {Component} from 'react';
import Form from '../../Components/Form';
import Row from '../../Components/Row';
import Input from '../../Components/Input';
import {getUrl, messageAlert} from '../../Methods';
import Http from '../../Http';
import Alert from '../../Components/Alert';
import Container from '../../Components/Container';
import Loading from '../../Components/Loading'
import TextArea from "../../Components/TextArea";

class SightForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            title: 'INSERIR UM PARECER',
            sight: {
                title: '',
                description: '',
                process: ''
            },
            alerts: [],
            loading: true
        };

        this.save = this.save.bind(this);
        this.change = this.change.bind(this);
    }

    async componentDidMount() {
        let { id } = this.props.match.params;

        this.setState({
            sight: {
                process: getUrl() + "process/" + id,
            },
            loading: false
        });
    }

    change(field, value) {
        let { sight } = this.state;
        sight[field] = value;
        this.setState({
            sight: sight
        })
    }

    save(e) {
        e.preventDefault();
        let alerts = [];

        if (this.state.sight.title.length <= 0) alerts.push('Preencha o campo título');
        if (this.state.sight.description.length <= 0) alerts.push('Preencha o campo descrição');

        if (alerts.length <= 0) {
            new Http().post('/sight', this.state.sight).send(async () => {
                await messageAlert('Parecer inserido com sucesso', 'success');
                await this.props.history.push('/processo');
            })
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
                    <Form title={title} col="col-sm-12" icon="fa fa-tag" urlCancel="/process" onSubmit={this.save}>
                        {alerts}
                        <Row>
                            <Input name={'title'} value={this.state.sight.title} change={this.change} label={'Título'} col={12} />
                        </Row>
                        <Row>
                            <TextArea
                                col={'12'}
                                name={'description'}
                                label={'Descrição'}
                                value={this.state.sight.description}
                                change={this.change} />
                        </Row>
                    </Form>
                </Row>
            </Container>
        );
    }
}

export default SightForm;