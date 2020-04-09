import React, {Component} from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
// import ListReviewComponent from "./ListReviewComponent";
import ProcessDataService from '../service/ProcessDataService';

class ProcessComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            description: '',
            users: '',
            reviews: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
        this.exitClicked = this.exitClicked.bind(this)
    }

    componentDidMount() {
        console.log('did mount ProcessComponent '+this.state.id)

        // eslint-disable-next-line
        if (this.state.id == -1) {
            return
        }

        ProcessDataService.retrieveProcessById(this.state.id).then(
            response => this.setState(
                {
                    description: response.data.description,
                    // users: response.data.users,
                    // reviews: response.data.reviews
                    // TODO: apresentar/capturar tipo list. Dados abaixo para teste    
                    users: ['Jake', 'Ian'],
                    reviews: ['Good', 'Bad']            
                }
            )
        )
        console.log(this.state.users.values)
    }

    exitClicked(){
        console.log('exit clicked - ProcessComponent')
        this.props.history.push('/process')
    }
    
    onSubmit(values){  
        console.log('submit ' + values + ' - ProcessComponent')

        let process = {
            id: this.state.id,
            description: values.description,
            users: values.users.values,
            reviews: values.reviews
        }

        if(this.state.id === -1){
            ProcessDataService.createProcess(process)
                .then(() => this.props.history.push('/process'))
        } else {
            ProcessDataService.updateProcess(this.state.id, process)
                .then(() => this.props.history.push('/process'))
        }
    }

    validate(values) {
        console.log('validate - ProcessComponent')
        let errors = {}

        if(!values.description){
            errors.description = 'Informe uma descrição'
        } 

        console.log(errors)
        return errors
    }

    render() {
        let { id, description, users, reviews } = this.state

        return (
            <div>
                <h2>Detalhes do Processo</h2>
                <hr/>
                <div className="container">
                    <Formik 
                        initialValues={{ id, description, users, reviews }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="description" component="div" className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Id:</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset><fieldset className="form-group">
                                        <label>Descrição:</label>
                                        <Field className="form-control" type="text" name="description" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Pareceristas:</label>
                                        <Field className="form-control" name="users" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Novo Parecer:</label>
                                        <Field className="form-control" name="reviews" />
                                    </fieldset>
                                    {/* TODO: apresentar dados tipo list */}
                                    {/* <fieldset className="form-group">
                                        <ListReviewComponent/>
                                    </fieldset> */}
                                    <button className="btn btn-success" type="submit">Salvar</button>
                                    <button className="btn btn-default" onClick={this.exitClicked}>Voltar</button>
                                </Form>
                            )
                        }
                    </Formik>
                </div> 
            </div>
        )
    }
}

export default ProcessComponent