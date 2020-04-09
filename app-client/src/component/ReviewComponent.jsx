import React, {Component} from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ReviewDataService from '../service/ReviewDataService';

class ReviewComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            text: '',
            reviewer: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
        this.exitClicked = this.exitClicked.bind(this)
    }

    componentDidMount() {
        console.log('did mount review ' + this.state.id + " - ReviewComponent")

        // eslint-disable-next-line
        if (this.state.id == -1) {
            return
        }

        ReviewDataService.retrieveReviewById(this.state.id).then(
            response => this.setState(
                {
                    text: response.data.text,
                    reviewer: response.data.reviewer
                    // reviewer: 'Jake' // TODO: corrigir relacionamento. Carregar valor correto
                }
            )
        )
    }

    exitClicked(){
        console.log('exit clicked - ReviewComponent')
        this.props.history.push('/review')
        // window.history.back(-1)
    }

    onSubmit(values){
        console.log('submit review  - ReviewComponent')

        let review = {
            id: this.state.id,
            reviewer: values.reviewer, // TODO: corrigir referência user
            text: values.text
            
        }

        if(this.state.id === -1){
            ReviewDataService.createReview(review)
                .then(() => this.props.history.push('/review')) // TODO: navegar para o processo aberto
        } else {
            ReviewDataService.updateReview(this.state.id, review)
                .then(() => this.props.history.push('/review')) // TODO: navegar para o processo aberto
        }

        // console.log(values);   
    }

    validate(values) {
        console.log('validate review - ReviewComponent')

        let errors = {}

        if(!values.reviewer){
            errors.reviewer = 'Informe o parecerista'
        } 
        
        if(!values.text){
            errors.text = 'Informe um parecer'
        } 

        return errors
    }

    render() {
        let { id, reviewer, text } = this.state

        return (
            <div className="container">
                <h2>Parecer</h2>
                <hr/>
                <div className="container">
                    <Formik 
                        initialValues={{ id, reviewer, text }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="reviewer" component="div" className="alert alert-warning" />
                                    <ErrorMessage name="text" component="div" className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Id:</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Parecerista:</label>
                                        <Field className="form-control" type="text" name="reviewer" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Parecer:</label>
                                        <Field className="form-control" type="text" name="text" />
                                    </fieldset>
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

export default ReviewComponent